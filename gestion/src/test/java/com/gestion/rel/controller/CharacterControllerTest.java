package com.gestion.rel.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gestion.rel.domain.Character;
import com.gestion.rel.service.CharacterService;


public class CharacterControllerTest {
	
	@Mock
	private CharacterService characterService;

	@InjectMocks
	private CharacterController characterController;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenGameIdThenReturnAllCharacter(){
		Integer gameId = 13;
		Collection<com.gestion.rel.domain.Character> expected = new ArrayList<com.gestion.rel.domain.Character>();
		com.gestion.rel.domain.Character character = new com.gestion.rel.domain.Character();
		character.setId(gameId);
		expected.add(character);
		Mockito.when(characterService.getAll(gameId)).thenReturn(expected);
		Collection<com.gestion.rel.domain.Character> actual = characterController.list(gameId);
		assertNotNull(actual);
		assertEquals(expected,actual);
	}
	
	@Test
	public void givenIdThenReturnCharacter(){
		Integer gameId = 10;
		Integer id = 13;
		com.gestion.rel.domain.Character expected = new com.gestion.rel.domain.Character();
		expected.setId(id);
		Mockito.when(characterService.get(id)).thenReturn(expected);
		com.gestion.rel.domain.Character actual = characterController.get(gameId, id);
		assertNotNull(actual);
		assertEquals(expected,actual);
	}
	
	@Test
	public void givenCharacterThenUpdate(){
		Integer gameId=1;
		Integer id=2;
		com.gestion.rel.domain.Character character = new Character();
		character.setGame(gameId);
		character.setId(id);
		characterController.update(gameId, id, character);
		Mockito.verify(characterService).saveOrUpdate(character);
	}
	
	@Test
	public void givenIdThenDelete(){
		Integer gameId=1;
		Integer id=2;
		characterController.remove(gameId, id);
		Mockito.verify(characterService).remove(gameId, id);
	}
	
	@Test
	public void givenCreatedCharacterThenCreateOne(){
		Integer gameId = 1;
		Integer newCharacterId = 2;
		Collection<com.gestion.rel.domain.Character> characterList = new ArrayList<com.gestion.rel.domain.Character>();
		com.gestion.rel.domain.Character character = new com.gestion.rel.domain.Character();
		character.setId(gameId);
		character.setId(1);
		characterList.add(character);
		Mockito.when(characterService.getAll(gameId)).thenReturn(characterList);
		
		com.gestion.rel.domain.Character characterCreated = new Character();
		characterCreated.setGame(gameId);
		characterCreated.setId(newCharacterId);

		Integer actualCharacterId = characterController.create(gameId);
		
		Mockito.verify(characterService).saveOrUpdate(characterCreated);
		assertEquals(newCharacterId, actualCharacterId);

	}
	
	@Test
	public void givenNoCreatedCharacterThenCreateOne(){
		Integer gameId = 1;
		Integer newCharacterId = 1;
		Mockito.when(characterService.getAll(gameId)).thenReturn(new ArrayList<Character>());
		
		com.gestion.rel.domain.Character characterCreated = new Character();
		characterCreated.setGame(gameId);
		characterCreated.setId(newCharacterId);

		Integer actualCharacterId = characterController.create(gameId);
		
		Mockito.verify(characterService).saveOrUpdate(characterCreated);
		assertEquals(newCharacterId, actualCharacterId);

	}
}
