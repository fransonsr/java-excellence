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

public class AssetReportWriter {
	private PrintWriter writer;

	public AssetReportWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public void writeRootTagStart() {
		writer.write("<groups>\n");
	}

	public void writeRootTagEnd() {
		writer.write("</groups>\n");
		writer.flush();
	}

	public void writeGroupPosition(BigDecimal position) {
		writer.write("position='" + position.toPlainString() + "' ");
	}

	public void writeGroupWeight(BigDecimal weight) {
		writer.write("weight='" + weight);
	}

	public void writeGroupTagStart() {
		writer.write("\t<group ");
	}

	public void writeAssetPosition(String positionsAsset, boolean firstOne) {
		if (!firstOne)
			writer.write("\n");
		writeAssetTagStart();
		writePosition(positionsAsset);
	}

	private void writePosition(String positionsAsset) {
		writer.write("position='" + positionsAsset + "' ");
	}

	private void writeAssetTagStart() {
		writer.write("\t\t<asset ");
	}

	public void writeAsset(String asset) {
		writer.write("\t\t\t" + asset + "\n");
	}

	public void writeGroupName(String currentGroupName) {
		writer.write("\t\t" + currentGroupName + "\n");
	}

	public void writeWeight(BigDecimal weight1) {
		writer.write("weight='" + weight1 + "' ");
	}

	public void writeRisk(String risk) {
		writer.write("risk='" + risk);
		writeCloseTag();
	}

	public void writeCloseTag() {
		writer.write("'>\n");
	}

	public void writeAssetTagEnd() {
		writer.write("\t\t</asset>");
	}

	public void writeGroupTagEnd() {
		writer.write("\n\t</group>\n");
	}

}
