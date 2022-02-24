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

package com.industriallogic.staticrecords;

public class Song {
	public String name;
	public final int dataLength;

	private byte data[];
	private int numTimesPlayed;

	public Song(String name, int dataLength) {
		this.name = name;
		this.dataLength = dataLength;
		numTimesPlayed = 0;
		data = new byte[dataLength];
		for (int i = 0; i < dataLength; i++)
			data[i] = (byte)(i % 255);
	}

	public byte[] getData() {
		return data;
	}

	public void incNumTimesPlayed() {
		numTimesPlayed++;
	}

	public int getNumTimesPlayed() {
		return numTimesPlayed;
	}
}
