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

public class PlaylistTest {
	private SongPlayer songPlayer;

	@Before
	public void createPlayer() {
		songPlayer = new SongPlayer("a player");
	}

	@Test
	public void addSongs() {
		assertEquals(0, SongPlayer.getTotalNumberOfSongs());
		Song song = new Song("Limbo Rock", 1000);
		songPlayer.addSong(song);
		assertEquals(1, SongPlayer.getTotalNumberOfSongs());
	}

	@Test
	public void songNotFound() {
		try {
			songPlayer.playSong("NoSong");
			fail("should have thrown exception");
		}
		catch (SongNotFoundException expected) {
			assertEquals("Song not found: 'NoSong'", expected.getMessage());
		}
	}

	@Test
	public void mostPopularSong() throws SongNotFoundException {
		Song song = new Song("Limbo Rock", 1000);
		songPlayer.addSong(song);

		Song popularSong = new Song("Limbo", 1000);
		songPlayer.addSong(popularSong);

		songPlayer.playSong("Limbo");
		songPlayer.playSong("Limbo Rock");
		songPlayer.playSong("Limbo");
		songPlayer.playSong("Limbo Rock");
		songPlayer.playSong("Limbo");

		assertEquals("Limbo", SongPlayer.getMostPopularSong());
	}
}
