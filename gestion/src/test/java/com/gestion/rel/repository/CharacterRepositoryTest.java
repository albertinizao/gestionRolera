package com.gestion.rel.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.gestion.rel.domain.Character;

import com.gestion.rel.domain.Game;

@RunWith(MockitoJUnitRunner.class)
public class CharacterRepositoryTest {

	@Mock
	private MongoTemplate template;

	@InjectMocks
	private CharacterRepository characterRepository;

	@Test
	public void givenPjIdsThenBuildQuery() {
		Collection<Integer> pjIds = new ArrayList<Integer>();
		Integer pjId = 1;
		Integer gameId = 2;
		pjIds.add(pjId);
		Criteria criteria = Criteria.where("game").is(gameId);
		Query expected = Query.query(criteria);
		Query actual = characterRepository.getPJQuery(gameId);
		assertEquals(expected, actual);
	}

	@Test
	public void givenUserIdsThenBuildQuery() {
		Collection<String> playersIds = new ArrayList<String>();
		String playerId = "1";
		playersIds.add(playerId);
		Criteria criteria = Criteria.where("users").in(playersIds);
		Query expected = Query.query(criteria);
		Query actual = characterRepository.getUsersQuery(playersIds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldInvokeGetAll(){
		Integer gameId = 2;
		List<Character> expected = new ArrayList<Character>();
		expected.add(new Character());
		Mockito.when(template.find(Mockito.any(), Mockito.eq(Character.class))).thenReturn(expected);
		Collection<Character> actual = characterRepository.getAll(gameId);
		assertEquals(expected, actual);
	}
	
	@Test
	public void givenPjIdsThenReturnTheirGames(){
		Integer charId = 1;
		Collection<Integer> pjIds = new ArrayList<Integer>();
		pjIds.add(charId);
		Character expected = new Character();
		Mockito.when(template.findById(charId, Character.class)).thenReturn(expected);
		Character actual = characterRepository.getById(charId);
		assertEquals(expected, actual);
	}
	
	@Test
	public void givenIdThenReturnCharacter(){
		Integer id = 1;
		Character expected = new Character();
		expected.setId(id);
		Mockito.when(template.findById(id, Character.class)).thenReturn(expected);
		Character actual = characterRepository.getById(id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void givenGameThenSaveIt(){
		Character character = new Character();
		character.setId(1);
		Integer gameId = 2;
		characterRepository.saveOrUpdate(character);
		Mockito.verify(template).save(character);
	}
	
	@Test
	public void givenIdThenDeleteIt(){
		Integer characterId=1;
		Integer gameId = 2;
		Character character = new Character();
		character.setId(characterId);
		character.setGame(gameId);
		characterRepository.remove(gameId, characterId);
		Mockito.verify(template).remove(character);
	}

}
