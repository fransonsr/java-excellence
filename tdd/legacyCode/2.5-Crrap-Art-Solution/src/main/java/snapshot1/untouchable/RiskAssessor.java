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

package snapshot1.untouchable;

import java.math.BigDecimal;

public class RiskAssessor {

	private static RiskAssessor instance = null;

	public BigDecimal getRiskCoefficient(String family, BigDecimal assessedCeiling) {
		return new BigDecimal(family.length()).add(assessedCeiling).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static RiskAssessor getInstance() {
		if (instance == null)
			instance = new RiskAssessor();
		return instance;
	}
}
