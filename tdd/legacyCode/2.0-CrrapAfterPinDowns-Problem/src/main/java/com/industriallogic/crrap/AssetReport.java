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

public class AssetReport {

	private RiskAssessor assessor;

	public AssetReport(RiskAssessor assessor) {
		this.assessor = assessor;
	}

	public AssetReport() {
		this(RiskAssessor.getInstance());
	}

	public void execute(RecordSet records, PrintWriter writer) {
		TreeMap<String, BigDecimal> groupTotal;
		
		// Store positions for all assets
		TreeMap<String, BigDecimal> positions;
		BigDecimal allPositions;
		
		// Risks for all assets
		HashMap<String, BigDecimal> m_hmRiskTable;
		HashMap<String, String> assetToGroup;
		groupTotal = new TreeMap<String, BigDecimal>();
		positions = new TreeMap<String, BigDecimal>();
		m_hmRiskTable = new HashMap<String, BigDecimal>();
		allPositions = new BigDecimal("0.00");
		assetToGroup = new HashMap<String, String>();
		for (int row = 0; row < records.getRowCount(); row++) {
			BigDecimal pos = new BigDecimal(1);
			BigDecimal r = new BigDecimal("0.00");
			String issue = new String("");
			issue = records.getItem(row, "ISSUE_NAME");
			if (records.getItem(row, "ISSUE_FAMILY").toUpperCase().startsWith("FUND")) {
				// pos = quantity * (market-unit price[TERM_TWO])
				BigDecimal perItem = records.getDecimal(row, "MARKET_PRICE").subtract(records.getDecimal(row, "TERM_ONE"));
				pos = perItem.multiply(records.getDecimal(row, "QUANTITY")).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal riskCoefficient = assessor.getRiskCoefficient(records.getItem(row, "ISSUE_FAMILY"), records
						.getDecimal(row, "TERM_TWO"));
				BigDecimal product = riskCoefficient.multiply(pos);
				r = product.divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP);
				positions.put(issue, pos);
			}
			else {
				// pos = (quantity * market) - total price[TERM_ONE]
				pos = records.getDecimal(row, "QUANTITY").multiply(records.getDecimal(row, "MARKET_PRICE"));
				pos = pos.subtract(records.getDecimal(row, "TERM_ONE")).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal product = records.getDecimal(row, "TERM_TWO").multiply(pos);
				r = product.divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP);
				positions.put(issue, pos);
			}
			allPositions = allPositions.add(positions.get(issue));
		
			String group = records.getItem(row, "ISSUE_GROUP");
			String name = records.getItem(row, "ISSUE_NAME");
			assetToGroup.put(name, group);
			BigDecimal value = new BigDecimal("0");
			if (groupTotal.containsKey(group))
				value = value.add(groupTotal.get(group)).setScale(2);
			value = value.add(positions.get(issue));
			groupTotal.put(group, value.setScale(2));
			m_hmRiskTable.put(issue, r);
		}
		
		writer.write("<groups>\n");
		// groups in sorted order
		Iterator<String> g = groupTotal.keySet().iterator();
		while (g.hasNext()) {
			String grp = g.next();
			BigDecimal position = groupTotal.get(grp);
			BigDecimal product = position.multiply(new BigDecimal(100));
			BigDecimal weight = product.divide(allPositions, 2, BigDecimal.ROUND_HALF_UP);
			writer.write("\t<group position='" + position.toPlainString());
			writer.write("' weight='" + weight);
			writer.write("'>\n");
			writer.write("\t\t" + grp + "\n");
			Iterator<String> i = positions.keySet().iterator();
			boolean notFirstOne = false;
			while (i.hasNext()) {
				String asset = i.next();
				// Output asset only if it belongs in group
				if (assetToGroup.get(asset).equalsIgnoreCase(grp)) {
					if (notFirstOne)
						writer.write("\n");
					writer.write("\t\t<asset position='" + positions.get(asset).toPlainString() + "' ");
					BigDecimal p = positions.get(asset);
					BigDecimal weight1 = p.multiply(new BigDecimal("100.00")).divide(position, 2, BigDecimal.ROUND_HALF_UP)
							.setScale(2);
					writer.write("weight='" + weight1 + "' risk='" + m_hmRiskTable.get(asset).toPlainString() + "'>\n");
					writer.write("\t\t\t" + asset + "\n");
					writer.write("\t\t</asset>");
					notFirstOne = true;
				}
			}
			writer.write("\n\t</group>\n");
		}
		writer.write("</groups>\n");
		writer.flush();
	}

}
