package com.industriallogic.staticrecords;

import static org.junit.Assert.*;

import org.junit.Test;

public class SongTest {
	@Test
	public void song() {
		Song song = new Song("Limbo Rock", 1000);
		assertEquals("Limbo Rock", song.name);
		assertEquals(1000, song.dataLength);
		assertNotNull(song.getData());
	}
}
