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
import java.io.StringWriter;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import untouchable.RecordSet;
import untouchable.RiskAssessor;

public class AssetReportTest implements AssetReportConstants {

	private static final String RISK_75 = "4.00";
	private static final String TOTAL_COST_4 = RISK_75;
	private static final String PER_UNIT_COST_1 = "1.00";
	private static final String RISK_CEILING_10 = "10.00";
	private static final String MARKET_PRICE_2 = "2.00";
	private static final String QUANTITY_10 = "10.00";
	private RecordSet records;
	private AssetReport report;

	class FakeRiskAssessor extends RiskAssessor {
		public BigDecimal getRiskCoefficient(String family, BigDecimal assessedCeiling) {
			return new BigDecimal("13.00");
		}
	}

	@Before
	public void setUp() {
		report = new AssetReport(new FakeRiskAssessor());
	}

	private void newRecordSetForFund() {
		records = new RecordSet(new String[] {
				ISSUE_GROUP, ISSUE_FAMILY, ISSUE_NAME, QUANTITY, MARKET_PRICE,
				FUND_PER_UNIT_COST, FUND_ASSESSED_RISK });
	}

	private void newRecordSetForEquity() {
		records = new RecordSet(new String[] {
				ISSUE_GROUP, ISSUE_FAMILY, ISSUE_NAME, QUANTITY, MARKET_PRICE,
				EQUITY_TOTAL_COST, EQUITY_SIMPLE_RISK_COEFFICIENT });
	}

	@Test
	public void singleEquity() throws Exception {
		newRecordSetForEquity();
		records.addRow(new String[] { "Equities", "family", "Equity",
				QUANTITY_10, MARKET_PRICE_2, TOTAL_COST_4, RISK_75 });

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		report.execute(records, writer);
		String xml = "<groups>\n" + "\t<group position='16.00' weight='100.00'>\n" + "\t\tEquities\n"
				+ "\t\t<asset position='16.00' weight='100.00' risk='0.64'>\n" + "\t\t\tEquity\n" + "\t\t</asset>\n"
				+ "\t</group>\n" + "</groups>\n";
		assertEquals(xml, stringWriter.toString());
	}

	@Test
	public void singleMoneyMarket() throws Exception {
		newRecordSetForFund();
		records.addRow(new String[] { "Funds", "FUND", "Fund", QUANTITY_10,
				MARKET_PRICE_2, PER_UNIT_COST_1, RISK_CEILING_10 });

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		report.execute(records, writer);
		String xml = "<groups>\n" + "\t<group position='10.00' weight='100.00'>\n" + "\t\tFunds\n"
				+ "\t\t<asset position='10.00' weight='100.00' risk='1.30'>\n" + "\t\t\tFund\n" + "\t\t</asset>\n"
				+ "\t</group>\n" + "</groups>\n";
		assertEquals(xml, stringWriter.toString());
	}

	@Test
	public void mixed() throws Exception {
		newRecordSetForFund();
		records.addRow(new String[] { "Assets", "family", "Equity", QUANTITY_10,
				MARKET_PRICE_2, TOTAL_COST_4, RISK_75 });
		records.addRow(new String[] { "Assets", "family", "Fund", QUANTITY_10,
				MARKET_PRICE_2, PER_UNIT_COST_1, RISK_CEILING_10 });

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		report.execute(records, writer);
		String xml = "<groups>\n" + "\t<group position='35.00' weight='100.00'>\n" + "\t\tAssets\n"
				+ "\t\t<asset position='16.00' weight='45.71' risk='0.64'>\n" + "\t\t\tEquity\n" + "\t\t</asset>\n"
				+ "\t\t<asset position='19.00' weight='54.29' risk='1.90'>\n" + "\t\t\tFund\n" + "\t\t</asset>\n"
				+ "\t</group>\n" + "</groups>\n";
		assertEquals(xml, stringWriter.toString());
	}

	@Test
	public void sameTypeSameGroup() throws Exception {
		newRecordSetForEquity();
		records.addRow(new String[] { "Alpha", "family", "Alpha Equity",
				QUANTITY_10, MARKET_PRICE_2, TOTAL_COST_4, RISK_75 });
		records.addRow(new String[] { "Alpha", "family", "Beta Equity",
				QUANTITY_10, MARKET_PRICE_2, TOTAL_COST_4, RISK_75 });
		records.addRow(new String[] { "Alpha", "family", "Delta Equity",
				QUANTITY_10, MARKET_PRICE_2, TOTAL_COST_4, RISK_75 });

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		report.execute(records, writer);
		String xml = "<groups>\n" + "\t<group position='48.00' weight='100.00'>\n" + "\t\tAlpha\n"
				+ "\t\t<asset position='16.00' weight='33.33' risk='0.64'>\n" + "\t\t\tAlpha Equity\n" + "\t\t</asset>\n"
				+ "\t\t<asset position='16.00' weight='33.33' risk='0.64'>\n" + "\t\t\tBeta Equity\n" + "\t\t</asset>\n"
				+ "\t\t<asset position='16.00' weight='33.33' risk='0.64'>\n" + "\t\t\tDelta Equity\n" + "\t\t</asset>\n"
				+ "\t</group>\n" + "</groups>\n";
		assertEquals(xml, stringWriter.toString());

	}

	@Test
	public void multiGroup() throws Exception {
		newRecordSetForEquity();
		records.addRow(new String[] { "Alpha", "family", "Alpha Equity", QUANTITY_10, MARKET_PRICE_2, TOTAL_COST_4, RISK_75 });
		records.addRow(new String[] { "Beta", "family", "Beta Equity", QUANTITY_10, MARKET_PRICE_2, TOTAL_COST_4, RISK_75 });

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		report.execute(records, writer);
		String xml = "<groups>\n" + "\t<group position='16.00' weight='50.00'>\n" + "\t\tAlpha\n"
				+ "\t\t<asset position='16.00' weight='100.00' risk='0.64'>\n" + "\t\t\tAlpha Equity\n" + "\t\t</asset>\n"
				+ "\t</group>\n" + "\t<group position='16.00' weight='50.00'>\n" + "\t\tBeta\n"
				+ "\t\t<asset position='16.00' weight='100.00' risk='0.64'>\n" + "\t\t\tBeta Equity\n" + "\t\t</asset>\n"
				+ "\t</group>\n" + "</groups>\n";
		assertEquals(xml, stringWriter.toString());

	}

}
