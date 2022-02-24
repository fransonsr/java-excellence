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

public class EquityAsset extends Asset{

	private final BigDecimal marketPrice;
	private final BigDecimal equityTotalCost;
	private final BigDecimal quantity;
	private final BigDecimal equitySimpleRiskCoefficient;

	public EquityAsset(BigDecimal marketPrice, BigDecimal equityTotalCost, BigDecimal quantity,
			BigDecimal equitySimpleRiskCoefficient, String issueName, String issueGroup) {
				super(issueName, issueGroup);
				this.marketPrice = marketPrice;
				this.equityTotalCost = equityTotalCost;
				this.quantity = quantity;
				this.equitySimpleRiskCoefficient = equitySimpleRiskCoefficient;
	}

	public BigDecimal calculatePosition() {
		return quantity.multiply(marketPrice)
			.subtract(equityTotalCost).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal calculateRisk() {
		return equitySimpleRiskCoefficient.multiply(calculatePosition()).divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP);
	}

}
