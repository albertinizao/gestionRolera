package com.gestion.rel.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Game;
import com.gestion.rel.service.GameService;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	@InjectMocks
	private GameController gameController;

	@Mock
	private GameService gameService;

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

	@Test
	public void givenIdThenReturnGame() {
		Integer id=5;
		Game gameExpected = new Game();
		Mockito.when(gameService.get(id)).thenReturn(gameExpected);
		Game gameActual = gameController.get(id);
		assertNotNull(gameActual);
		assertEquals(gameExpected,gameActual);
	}
	
	@Test
	public void givenGameThenUpdate(){
		Integer id=2;
		Game game = new Game();
		game.setId(id);
		gameController.update(id, game);
		Mockito.verify(gameService).saveOrUpdate(game);
	}
	
	@Test
	public void givenIdThenDelete(){
		Integer id=2;
		gameController.remove(id);
		Mockito.verify(gameService).remove(id);
	}
	
	@Test
	public void givenCreatedGameThenCreateOne(){
		Integer newGameId = 2;
		Collection<Game> gameList = new ArrayList<Game>();
		Game game = new Game();
		game.setId(1);
		gameList.add(game);
		Mockito.when(gameService.getAll(null)).thenReturn(gameList);
		
		Game gameCreated = new Game();
		gameCreated.setId(newGameId);

		Integer actualGameId = gameController.create();
		
		Mockito.verify(gameService).saveOrUpdate(gameCreated);
		assertEquals(newGameId, actualGameId);

	}
	
	@Test
	public void givenNoCreatedCharacterThenCreateOne(){
		Integer newGameId = 1;
		Mockito.when(gameService.getAll(null)).thenReturn(new ArrayList<Game>());
		
		Game gameCreated = new Game();
		gameCreated.setId(newGameId);

		Integer actualCharacterId = gameController.create();
		
		Mockito.verify(gameService).saveOrUpdate(gameCreated);
		assertEquals(newGameId, actualCharacterId);

	}

}
