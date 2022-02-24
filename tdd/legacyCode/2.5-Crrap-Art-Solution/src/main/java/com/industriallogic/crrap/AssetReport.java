// ***************************************************************************
// Copyright (c) 2020, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.crrap;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import untouchable.RecordSet;
import untouchable.RiskAssessor;

public class AssetReport implements AssetReportConstants {
	private RiskAssessor assessor;
	private TreeMap<String, BigDecimal> groupTotalsMap = new TreeMap<String, BigDecimal>();
	private TreeMap<String, BigDecimal> positions = new TreeMap<String, BigDecimal>();
	private HashMap<String, BigDecimal> riskHashtable = new HashMap<String, BigDecimal>();
	private HashMap<String, String> assetToGroup = new HashMap<String, String>();
	private BigDecimal sumAllPositions = new BigDecimal("0.00");

	public AssetReport(RiskAssessor assessor) {
		this.assessor = assessor;
	}

	public AssetReport() {
		this(RiskAssessor.getInstance());
	}

	public void execute(RecordSet records, PrintWriter writer) {

		calculate(records);
		AssetReportWriter reportWriter = new AssetReportWriter(writer);
		writeReport(reportWriter);
	}

	private void writeReport(AssetReportWriter reportWriter) {
		reportWriter.writeRootTagStart();
		
		// groups in sorted order
		Iterator<String> groupTotalsIterator = groupTotalsMap.keySet().iterator();
		while (groupTotalsIterator.hasNext()) {
			String currentGroupName = groupTotalsIterator.next();
			BigDecimal position = groupTotalsMap.get(currentGroupName);
			BigDecimal product = position.multiply(new BigDecimal(100));
			BigDecimal weight = product.divide(sumAllPositions, 2, BigDecimal.ROUND_HALF_UP);
			
			AssetGroup assetGroup = new AssetGroup(currentGroupName, position, weight);
			reportWriter.writeGroupTagStart();
			reportWriter.writeGroupPosition(assetGroup.position);
			reportWriter.writeGroupWeight(assetGroup.weight);
			reportWriter.writeCloseTag();
			reportWriter.writeGroupName(assetGroup.currentGroupName);
			
			Iterator<String> positionsIterator = positions.keySet().iterator();
			boolean firstOne = true;
			while (positionsIterator.hasNext()) {
				String asset = positionsIterator.next();
				// Output asset only if it belongs in group
				
				if ( assetToGroup.get(asset).equalsIgnoreCase(currentGroupName)) {
					reportWriter.writeAssetPosition(positions.get(asset).toPlainString(), firstOne);
					BigDecimal positionAsset = positions.get(asset);
					BigDecimal weight1 = positionAsset.multiply(new BigDecimal("100.00"))
							.divide(assetGroup.position, 2, BigDecimal.ROUND_HALF_UP)
							.setScale(2);
					String risk = riskHashtable.get(asset).toPlainString();
					reportWriter.writeWeight(weight1);
					reportWriter.writeRisk(risk);
					reportWriter.writeAsset(asset);
					reportWriter.writeAssetTagEnd();
					firstOne = false;
				}
			}
			reportWriter.writeGroupTagEnd();
		}
		reportWriter.writeRootTagEnd();
	}

	private void calculate(RecordSet records) {
		for (int row = 0; row < records.getRowCount(); row++) {
			
			Asset currentAsset = createAssetFor(row, records);
			BigDecimal position = currentAsset.calculatePosition();
			BigDecimal risk = currentAsset.calculateRisk();
			
			positions.put(currentAsset.getIssueName(), position);
			sumAllPositions = sumAllPositions.add(positions.get(currentAsset.getIssueName()));
		
			assetToGroup.put(currentAsset.getIssueName(), currentAsset.getIssueGroup());
			BigDecimal value = addGroupTotal(currentAsset.getIssueName(), currentAsset.getIssueGroup());
			groupTotalsMap.put(currentAsset.getIssueGroup(), value.setScale(2));
			riskHashtable.put(currentAsset.getIssueName(), risk);
		}
	}

	private Asset createAssetFor(int row, RecordSet records) {

		BigDecimal marketPrice = records.getDecimal(row, MARKET_PRICE);
		BigDecimal fundPerUnitCost = records.getDecimal(row, FUND_PER_UNIT_COST);
		BigDecimal equityTotalCost = records.getDecimal(row, EQUITY_TOTAL_COST);
		BigDecimal quantity = records.getDecimal(row, QUANTITY);
		String issueFamily = records.getItem(row, ISSUE_FAMILY);
		String issueGroup = records.getItem(row, ISSUE_GROUP); 
		String issueName = records.getItem(row, ISSUE_NAME);
		BigDecimal fundAssessedRisk = records.getDecimal(row, FUND_ASSESSED_RISK);
		BigDecimal equitySimpleRiskCoefficient = records.getDecimal(row, EQUITY_SIMPLE_RISK_COEFFICIENT);

		Asset currentAsset;
		if (isFundFamily(issueFamily)) {
			currentAsset = new FundAsset(marketPrice, fundPerUnitCost, quantity, issueFamily, fundAssessedRisk, issueName, issueGroup, assessor);
		}
		else {
			currentAsset = new EquityAsset(marketPrice, equityTotalCost, quantity, equitySimpleRiskCoefficient, issueName, issueGroup);
		}
		return currentAsset;
	}

	private boolean isFundFamily(String issueFamily) {
		return issueFamily.startsWith(FUND_NAME_PREFIX);
	}

	private BigDecimal addGroupTotal(String issue, String group) {
		BigDecimal value = new BigDecimal("0");
		if (groupTotalsMap.containsKey(group))
			value = value.add(groupTotalsMap.get(group)).setScale(2);
		return value.add(positions.get(issue));
	}
}
