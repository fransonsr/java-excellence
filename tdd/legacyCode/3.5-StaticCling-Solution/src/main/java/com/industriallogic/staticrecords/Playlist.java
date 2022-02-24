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

import java.util.HashMap;
import java.util.Map;

public class Playlist {
	private Map<String, Song> songs;
	private int totalSongsPlayed;

	public Playlist() {
		songs = new HashMap<String, Song>();
		totalSongsPlayed = 0;
	}

	public void addSong(Song song) {
		if (!songs.containsValue(song))
			songs.put(song.name, song);
	}

	public Song findSong(String songName) throws SongNotFoundException {
		if (!songs.containsKey(songName))
			throw new SongNotFoundException(songName);
		return songs.get(songName);
	}

	// statistics
	public int getTotalNumberOfSongs() {
		return songs.size();
	}

	public int getTotalNumberSongsPlayed() {
		return totalSongsPlayed;
	}

	public void incSongsPlayed() {
		++totalSongsPlayed;
	}

	// the song that most players have played.
	public String getMostPopularSong() {
		String result = null;
		int maxSoFar = 0;
		for (Song song : songs.values())
			if (song.getNumTimesPlayed() > maxSoFar)
			{
				maxSoFar = song.getNumTimesPlayed();
				result = song.name;
			}

		return result;
	}
}

@SuppressWarnings("serial")
class SongNotFoundException extends Exception {
	SongNotFoundException(String songName) {
		super("Song not found: '" + songName + "'");
	}
}
