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

import untouchable.RiskAssessor;

public class FundAsset extends Asset {

	private final BigDecimal marketPrice;
	private final BigDecimal fundPerUnitCost;
	private final BigDecimal quantity;
	private final String issueFamily;
	private final BigDecimal fundAssessedRisk;
	private final RiskAssessor assessor;
	public FundAsset(BigDecimal marketPrice, BigDecimal fundPerUnitCost, BigDecimal quantity, String issueFamily,
			BigDecimal fundAssessedRisk, String issueName, String issueGroup, RiskAssessor assessor) {
				super(issueName,issueGroup);
				this.marketPrice = marketPrice;
				this.fundPerUnitCost = fundPerUnitCost;
				this.quantity = quantity;
				this.issueFamily = issueFamily;
				this.fundAssessedRisk = fundAssessedRisk;
				this.assessor = assessor;
	}

	@Override
	public BigDecimal calculatePosition() {
		return marketPrice.subtract(fundPerUnitCost)
			.multiply(quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public BigDecimal calculateRisk() {
		BigDecimal risk;
		BigDecimal riskCoefficient = assessor.getRiskCoefficient(issueFamily, fundAssessedRisk);
		BigDecimal product = riskCoefficient.multiply(calculatePosition());
		risk = product.divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP);
		return risk;
	}

}
