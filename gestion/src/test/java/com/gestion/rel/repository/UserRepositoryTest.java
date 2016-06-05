package com.gestion.rel.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Game;
import com.gestion.rel.domain.User;
import com.gestion.rel.repository.CharacterRepository;
import com.gestion.rel.repository.GameRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

	@Mock
	private GameRepository gameRepository;

	@Mock
	private CharacterRepository characterRepository;

	@InjectMocks
	private UserRepository userRepository;

	@Test
	public void getUser() {
		String id = "AAS";
		User expected = new User();
		expected.setId(id);
		Collection<String> ids = new ArrayList<>();
		ids.add(id);
		Collection<Character> characters = new ArrayList<>();
		characters.add(new Character());
		expected.setCharacters(characters);
		Mockito.when(characterRepository.getByUsers(ids)).thenReturn(characters);
		Collection<Game> gamesMaster = new ArrayList<>();
		gamesMaster.add(new Game());
		expected.setGamesMaster(gamesMaster);
		Mockito.when(gameRepository.getAllByMasters(ids)).thenReturn(gamesMaster);
		Collection<Game> gamesPlayer = new ArrayList<>();
		gamesPlayer.add(new Game());
		expected.setGamesPlayer(gamesPlayer);
		Mockito.when(gameRepository.getAllByPlayers(ids)).thenReturn(gamesPlayer);
		User actual = userRepository.getUserById(id);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
