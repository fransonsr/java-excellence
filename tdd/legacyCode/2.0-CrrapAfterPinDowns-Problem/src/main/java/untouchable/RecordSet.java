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

package untouchable;

import java.math.BigDecimal;
import java.util.Vector;

public class RecordSet {

	private Vector<String> columns;
	private Vector<String[]> rows;

	public RecordSet(String[] fields) {
		rows = new Vector<String[]>();
		columns = new Vector<String>();
		for (int col = 0; col < fields.length; col++)
			this.columns.add(fields[col]);
	}

	public RecordSet(String[] fields, String[][] rows) {
		this(fields);
		for (int row = 0; row < rows.length; row++)
			addRow(rows[row]);
	}

	public void addRow(String[] rowData) {
		rows.add(rowData);
	}

	public void addColumn(String column) {
		if (nameToIndex(column) != -1)
			return;
		columns.add(column);
		for (int row = 0; row < rows.size(); row++) {
			rows.set(row, addColumnToRow((String[]) rows.get(row), null));
		}
	}

	private String[] addColumnToRow(String[] start, String newValue) {
		String[] replace = new String[start.length + 1];
		for (int col = 0; col < start.length; col++)
			replace[col] = start[col];
		replace[start.length] = newValue;
		return replace;
	}

	private int nameToIndex(String field) {
		for (int candidate = 0; candidate < columns.size(); candidate++) {
			if (columns.get(candidate).equals(field))
				return candidate;
		}
		return -1;
	}

	public int getRowCount() {
		return rows.size();
	}

	public int getColumnCount() {
		return columns.size();
	}

	public String getItem(int row, String column) {
		int col = nameToIndex(column);
		return getItem(row, col);
	}

	public String getItem(int row, int col) {
		if (row < 0 || row >= rows.size())
			return "";
		if (col < 0 || col >= columns.size())
			return "";
		return ((String[]) rows.get(row))[col];
	}

	public BigDecimal getDecimal(int row, int col) {
		String result = getItem(row, col);
		if (result == "")
			result = "0.00";
		return new BigDecimal(result);
	}

	public BigDecimal getDecimal(int row, String field) {
		String result = getItem(row, field);
		if (result == "")
			result = "0.00";
		return new BigDecimal(result);
	}
}
