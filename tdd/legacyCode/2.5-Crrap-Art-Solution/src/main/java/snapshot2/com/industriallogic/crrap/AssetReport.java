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

package snapshot2.com.industriallogic.crrap;

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
	TreeMap<String, BigDecimal> positions = new TreeMap<String, BigDecimal>();
	HashMap<String, BigDecimal> riskHashtable = new HashMap<String, BigDecimal>();
	private BigDecimal sumAllPositions = new BigDecimal("0.00");
	private HashMap<String, String> assetToGroup = new HashMap<String, String>();

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

			AssetGroup assetGroup = new AssetGroup(currentGroupName, position, weight,
					assetToGroup, riskHashtable);
			assetGroup.writeGroup(reportWriter, positions);
		}
		reportWriter.writeRootTagEnd();
	}

	private void calculate(RecordSet records) {
		for (int row = 0; row < records.getRowCount(); row++) {
			BigDecimal position = new BigDecimal(1);
			BigDecimal risk = new BigDecimal("0.00");
			BigDecimal product = new BigDecimal(1);

			String issue = records.getItem(row, ISSUE_NAME);

			if (records.getItem(row, ISSUE_FAMILY).toUpperCase().startsWith(FUND_NAME_PREFIX)) {
				// pos = quantity * (market-unit price[FUND_ASSESSED_RISK])
				// EquityPosition = (Quantity * Market Price) - Total
				// EquityRisk = Position * Risk Coefficient

				BigDecimal perItem = records.getDecimal(row, MARKET_PRICE).subtract(records.getDecimal(row, FUND_PER_UNIT_COST));
				position = perItem.multiply(quantity(records, row)).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal riskCoefficient = assessor.getRiskCoefficient(records.getItem(row, ISSUE_FAMILY), records
						.getDecimal(row, FUND_ASSESSED_RISK));
				product = riskCoefficient.multiply(position);
			}
			else {
				// pos = (quantity * market) - total price[TOTAL_COST]
				// FundPosition = Quantity * (Market Price - Unit Cost)
				// FundRisk = Position * RiskAssessor.getRiskCoefficient( Family, Risk Ceiling )
				position = quantity(records, row).multiply(records.getDecimal(row, MARKET_PRICE));
				position = position.subtract(records.getDecimal(row, EQUITY_TOTAL_COST)).setScale(2, BigDecimal.ROUND_HALF_UP);
				product = records.getDecimal(row, EQUITY_SIMPLE_RISK_COEFFICIENT).multiply(position);
			}
			risk = product.divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP);

			positions.put(issue, position);
			sumAllPositions = sumAllPositions.add(positions.get(issue));

			String issueGroup = records.getItem(row, ISSUE_GROUP);
			assetToGroup.put(records.getItem(row, ISSUE_NAME), issueGroup);
			BigDecimal value = addGroupTotal(issue, issueGroup);
			groupTotalsMap.put(issueGroup, value.setScale(2));
			riskHashtable.put(issue, risk);
		}
	}

	private BigDecimal addGroupTotal(String issue, String group) {
		BigDecimal value = new BigDecimal("0");
		if (groupTotalsMap.containsKey(group))
			value = value.add(groupTotalsMap.get(group)).setScale(2);
		return value.add(positions.get(issue));
	}

	private BigDecimal quantity(RecordSet records, int row) {
		return records.getDecimal(row, QUANTITY);
	}
}
