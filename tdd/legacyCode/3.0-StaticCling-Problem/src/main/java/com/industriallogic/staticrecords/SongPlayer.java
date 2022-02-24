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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongPlayer {
	private static Map<String, Song> songs = new HashMap<String, Song>();
	private static int totalSongsPlayed;
	private static List<SongPlayer> songPlayers = new ArrayList<SongPlayer>();
	private static int maxPlayersSoFar = 0;

	private String playerName;
	private int numberOfSongsPlayed;

	public SongPlayer(String playerName) {
		this.playerName = playerName;
		numberOfSongsPlayed = 0;
		songPlayers.add(this);
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
		if (!songs.containsKey(songName))
			throw new SongNotFoundException(songName);

		Song theSong = songs.get(songName);
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
		getMaximumSimultaneousPlayers();
		++totalSongsPlayed;
		++numberOfSongsPlayed;
		theSong.incNumTimesPlayed();
	}

	public void addSong(Song song) {
		if (!songs.containsValue(song))
			songs.put(song.name, song);
	}

	// statistics
	public static int getTotalNumberOfSongs() {
		return songs.size();
	}

	// statistics
	public static int getTotalNumberSongsPlayed() {
		return totalSongsPlayed;
	}

	// the song that most players have played.
	public static String getMostPopularSong() {
		String result = null;
		int maxSoFar = 0;
		for (Song song : songs.values())
			if (song.getNumTimesPlayed() > maxSoFar) {
				maxSoFar = song.getNumTimesPlayed();
				result = song.name;
			}

		return result;
	}

	public static int getNumberSongPlayersActive() {
		return songPlayers.size();
	}

	public static int getMaximumSimultaneousPlayers() {
		if (getNumberSongPlayersActive() > maxPlayersSoFar)
			maxPlayersSoFar = getNumberSongPlayersActive();
		return maxPlayersSoFar;
	}

	// player that plays the most songs
	public static String getMostDemandingPlayer() {
		String result = null;
		int maxSoFar = 0;
		for (SongPlayer player : songPlayers)
			if (player.getNumberOfSongsPlayed() > maxSoFar) {
				maxSoFar = player.getNumberOfSongsPlayed();
				result = player.getPlayerName();
			}

		return result;
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

class SongNotFoundException extends Exception {
	SongNotFoundException(String songName) {
		super("Song not found: '" + songName + "'");
	}
}
