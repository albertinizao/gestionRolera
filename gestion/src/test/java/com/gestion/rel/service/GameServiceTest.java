package com.gestion.rel.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.gestion.rel.domain.Game;
import com.gestion.rel.repository.GameRepository;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

	@Mock
	private GameRepository gameRepository;

	@InjectMocks
	private GameService gameService;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAll() {
		Collection<Game> gamesExpected = new ArrayList<Game>();
		gamesExpected.add(new Game());
		when(gameRepository.getAll()).thenReturn(gamesExpected);
		Collection<Game> gamesActual = gameService.getAll(null);
		assertEquals(gamesExpected, gamesActual);
	}

	@Test
	public void getAllFromCharacters() {
		Collection<Integer> characters = new ArrayList<Integer>();
		characters.add(1);
		Collection<Game> gamesExpected = new ArrayList<Game>();
		gamesExpected.add(new Game());
		when(gameRepository.getAllByPJ(characters)).thenReturn(gamesExpected);
		Collection<Game> gamesActual = gameService.getAll(characters);
		assertEquals(gamesExpected, gamesActual);
	}

	@Test
	public void get() {
		Game expected = new Game();
		Integer gameId= 1;
		when(gameRepository.getById(gameId)).thenReturn(expected);
		Game actual = gameService.get(gameId);
		assertEquals(expected, actual);
	}

	@Test
	public void saveOrUpdate() {
		Game game = new Game();
		gameService.saveOrUpdate(game);
		verify(gameRepository).saveOrUpdate(game);
	}

	@Test
	public void remove() {
		Integer gameId = 1;
		gameService.remove(gameId);
		verify(gameRepository).remove(gameId);
	}
}
