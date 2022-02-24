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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class AssetGroup {
	String currentGroupName;
	BigDecimal position;
	BigDecimal weight;
	HashMap<String, String> assetToGroup;
	HashMap<String, BigDecimal> riskHashtable;

	public AssetGroup(String currentGroupName, BigDecimal position, BigDecimal weight, 
			HashMap<String, String> assetToGroup, HashMap<String, BigDecimal> riskHashtable) {
		this.currentGroupName = currentGroupName;
		this.position = position;
		this.weight = weight;
		this.assetToGroup = assetToGroup;
		this.riskHashtable = riskHashtable;
	}

	void writeGroup(AssetReportWriter reportWriter, TreeMap<String, BigDecimal> positions) {
		reportWriter.writeGroupTagStart();
		reportWriter.writeGroupPosition(position);
		reportWriter.writeGroupWeight(weight);
		reportWriter.writeCloseTag();
		reportWriter.writeGroupName(currentGroupName);
		
		Iterator<String> positionsIterator = positions.keySet().iterator();
		boolean firstOne = true;
		while (positionsIterator.hasNext()) {
			String asset = positionsIterator.next();
			// Output asset only if it belongs in group
			if (isAssetInGroup(currentGroupName, asset)) {
				reportWriter.writeAssetPosition(positions.get(asset).toPlainString(), firstOne);
				BigDecimal positionAsset = positions.get(asset);
				BigDecimal weight1 = positionAsset.multiply(new BigDecimal("100.00"))
						.divide(position, 2, BigDecimal.ROUND_HALF_UP)
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
	boolean isAssetInGroup(String currentGroupName, String asset) {
		return assetToGroup.get(asset).equalsIgnoreCase(currentGroupName);
	}

}
