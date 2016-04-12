package com.gestion.rel.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gestion.rel.domain.Game;
import com.gestion.rel.service.GameService;

public class GameControllerTest {

	@InjectMocks
	private GameController gameController;

	@Mock
	private GameService gameService;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenCharactersListThenReturnAllGames() {
		Collection<Integer> characters = new ArrayList<Integer>();
		Collection<Game> gamesExpected = new ArrayList<Game>();
		gamesExpected.add(new Game());
		Mockito.when(gameService.getAll(characters)).thenReturn(gamesExpected);
		Collection<Game> gamesActual = gameController.list(characters);
		assertNotNull(gamesActual);
		assertEquals(gamesExpected,gamesActual);
	}

}
