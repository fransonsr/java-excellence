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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SingleSongPlayerTest {
	private SongPlayer oneSongPlayer;

	@Before
	public void setup() {
		oneSongPlayer = new SongPlayer("one");
		Song song1 = new Song("Limbo Rock", 1000);
		Song song2 = new Song("Limbo", 2000);
		Song song3 = new Song("Rock Around The Clock", 3000);
		oneSongPlayer.addSong(song1);
		oneSongPlayer.addSong(song2);
		oneSongPlayer.addSong(song3);
	}

	@Test
	public void onePlayerExists() {
		assertEquals(1, SongPlayer.getMaximumSimultaneousPlayers());
		assertEquals(1, SongPlayer.getNumberSongPlayersActive());
	}

	@Test
	public void playOneSong() throws SongNotFoundException {
		assertEquals(0, SongPlayer.getTotalNumberSongsPlayed());
		oneSongPlayer.playSong("Limbo Rock");
		assertEquals(1, SongPlayer.getTotalNumberSongsPlayed());
	}

	@Test
	public void playThreeSongs() throws SongNotFoundException {
		assertEquals(0, SongPlayer.getTotalNumberSongsPlayed());
		oneSongPlayer.playSong("Limbo");
		oneSongPlayer.playSong("Rock Around The Clock");
		oneSongPlayer.playSong("Limbo");
		assertEquals(3, SongPlayer.getTotalNumberSongsPlayed());
	}
}
