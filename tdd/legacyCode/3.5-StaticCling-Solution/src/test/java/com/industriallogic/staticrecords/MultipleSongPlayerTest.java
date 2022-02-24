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

public class MultipleSongPlayerTest {
	private Playlist playlist;
	private SongPlayer oneSongPlayer;
	private SongPlayer twoSongPlayer;
	private SongPlayer threeSongPlayer;
	private SongPlayerList playerList;

	@Before
	public void setup() {
		playerList = new SongPlayerList();
		playlist = new Playlist();

		oneSongPlayer = new SongPlayer("one", playlist);
		twoSongPlayer = new SongPlayer("two", playlist);
		threeSongPlayer = new SongPlayer("three", playlist);
		playerList.addPlayer(oneSongPlayer);
		playerList.addPlayer(twoSongPlayer);
		playerList.addPlayer(threeSongPlayer);

		Song song1 = new Song("Limbo Rock", 1000);
		Song song2 = new Song("Limbo", 2000);
		Song song3 = new Song("Rock Around The Clock", 3000);
		playlist.addSong(song1);
		playlist.addSong(song2);
		playlist.addSong(song3);
	}

	@Test
	public void threePlayersExist() {
		assertEquals(3, playerList.getNumberSongPlayersActive());
		assertEquals(3, playerList.getMaximumSimultaneousPlayers());
	}

	@Test
	public void playOneSongOnEachPlayer() throws SongNotFoundException {
		assertEquals(0, playlist.getTotalNumberSongsPlayed());
		oneSongPlayer.playSong("Limbo");
		twoSongPlayer.playSong("Rock Around The Clock");
		threeSongPlayer.playSong("Limbo");
		assertEquals(3, playlist.getTotalNumberSongsPlayed());
	}

	@Test
	public void mostDemandingUser() throws SongNotFoundException {
		oneSongPlayer.playSong("Limbo");
		twoSongPlayer.playSong("Limbo Rock");
		twoSongPlayer.playSong("Rock Around The Clock");
		threeSongPlayer.playSong("Limbo Rock");
		threeSongPlayer.playSong("Rock Around The Clock");
		threeSongPlayer.playSong("Limbo");

		assertEquals("three", playerList.getMostDemandingPlayer());
	}
}
