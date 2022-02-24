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
import java.util.List;

public class SongPlayerList {
	private List<SongPlayer> songPlayers;
	private int maximumSimultaneousPlayers;

	public SongPlayerList() {
		songPlayers = new ArrayList<SongPlayer>();
		maximumSimultaneousPlayers = 0;
	}

	public void addPlayer(SongPlayer player) {
		songPlayers.add(player);
		getMaximumSimultaneousPlayers();
	}

	// statistics
	public int getMaximumSimultaneousPlayers() {
		if (getNumberSongPlayersActive() > maximumSimultaneousPlayers)
			maximumSimultaneousPlayers = getNumberSongPlayersActive();
		return maximumSimultaneousPlayers;
	}

	public int getNumberSongPlayersActive() {
		return songPlayers.size();
	}

	// player that plays the most songs
	public String getMostDemandingPlayer() {
		String result = null;
		int maxSoFar = 0;
		for (SongPlayer player : songPlayers)
			if (player.getNumberOfSongsPlayed() > maxSoFar) {
				maxSoFar = player.getNumberOfSongsPlayed();
				result = player.getPlayerName();
			}

		return result;
	}
}
