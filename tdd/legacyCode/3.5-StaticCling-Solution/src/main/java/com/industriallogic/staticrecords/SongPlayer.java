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

public class SongPlayer {
	private String playerName;
	private int numberOfSongsPlayed;
	private Playlist playlist;

	public SongPlayer(String playerName, Playlist playlist) {
		this.playerName = playerName;
		this.playlist = playlist;
		numberOfSongsPlayed = 0;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getNumberOfSongsPlayed() {
		return numberOfSongsPlayed;
	}

	public void playSong(String songName) throws SongNotFoundException {
		// omitting code for running on a separate thread, with mutexes to protect
		// instance and static variables, etc.
		Song theSong = playlist.findSong(songName);
		startStream(theSong.name);
		int networkSegmentSize = 100;
		int offset = 0;
		do {
			int segmentSize = networkSegmentSize;
			if (segmentSize + offset > theSong.dataLength)
				segmentSize = theSong.dataLength - offset;
			streamData(theSong.getData(), offset, segmentSize);
			offset += networkSegmentSize;
		} while (offset < theSong.dataLength);
		stopStream();

		playlist.incSongsPlayed();
		++numberOfSongsPlayed;
		theSong.incNumTimesPlayed();
	}

	private void startStream(String songName) {
		// send song info to client, including songName
	}

	private void streamData(byte[] data, int from, int dataSegmentSize) {
		// send a part of the song's binary data to the client
	}

	private void stopStream() {
		// tell the song client to end the song playback
	}
}
