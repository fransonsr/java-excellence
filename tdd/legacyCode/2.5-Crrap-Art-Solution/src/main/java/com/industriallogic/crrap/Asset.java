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

public abstract class Asset {

	protected final String issueName;
	protected final String issueGroup;

	public Asset(String issueName, String issueGroup) {
		this.issueName = issueName;
		this.issueGroup = issueGroup;
	}

	public abstract BigDecimal calculateRisk();

	public abstract BigDecimal calculatePosition();

	public String getIssueName() {
		return issueName;
	}

	public String getIssueGroup() {
		return issueGroup;
	}

}
