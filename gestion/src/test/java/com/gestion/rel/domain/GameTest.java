package com.gestion.rel.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {
	
	@Test
	public void hasSimpleConstructor(){
		Game game = new Game();
		assertNotNull(game);
	}

	@Test
	public void hasIdAttribute(){
		Game game = new Game();
		Integer idExpected = 1;
		game.setId(idExpected);
		assertEquals(idExpected,game.getId());
	}

	@Test
	public void hasNameAttribute(){
		Game game = new Game();
		String name = "name";
		game.setName(name);
		assertEquals(name,game.getName());
	}
}
