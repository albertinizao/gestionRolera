package com.gestion.rel.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gestion.rel.domain.Character;
import com.gestion.rel.repository.CharacterRepository;
import com.gestion.rel.repository.CharacterRepository;


public class CharacterServiceTest {

	@Mock
	private CharacterRepository characterRespoitory;

	@InjectMocks
	private CharacterService characterService;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAll() {
		Integer id = 1;
		Collection<Character> charactersExpected = new ArrayList<Character>();
		charactersExpected.add(new Character());
		when(characterRespoitory.getAll(id)).thenReturn(charactersExpected);
		Collection<Character> charactersActual = characterService.getAll(id);
		assertEquals(charactersExpected, charactersActual);
	}

	@Test
	public void get() {
		Character expected = new Character();
		Integer characterId= 1;
		when(characterRespoitory.getById(characterId)).thenReturn(expected);
		Character actual = characterService.get(characterId);
		assertEquals(expected, actual);
	}

	@Test
	public void saveOrUpdate() {
		Character character = new Character();
		characterService.saveOrUpdate(character);
		verify(characterRespoitory).saveOrUpdate(character);
	}

	@Test
	public void remove() {
		Integer characterId = 1;
		Integer gameId = 2;
		characterService.remove(characterId, gameId);
		verify(characterRespoitory).remove(characterId, gameId);
	}
}