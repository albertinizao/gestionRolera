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

import com.gestion.rel.domain.Game;

@RunWith(MockitoJUnitRunner.class)
public class GameRepositoryTest {

	@Mock
	private MongoTemplate template;

	@InjectMocks
	private GameRepository gameRepository;

	@Test
	public void givenPjIdsThenBuildQuery() {
		Collection<Integer> pjIds = new ArrayList<Integer>();
		Integer pjId = 1;
		pjIds.add(pjId);
		Criteria criteria = Criteria.where("pj").in(pjIds);
		Query expected = Query.query(criteria);
		Query actual = gameRepository.getPJQuery(pjIds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldInvokeGetAll(){
		List<Game> expected = new ArrayList<Game>();
		expected.add(new Game());
		Mockito.when(template.findAll(Game.class)).thenReturn(expected);
		Collection<Game> actual = gameRepository.getAll();
		assertEquals(expected, actual);
	}
	
	@Test
	public void givenPjIdsThenReturnTheirGames(){
		Collection<Integer> pjIds = new ArrayList<Integer>();
		pjIds.add(1);
		List<Game> expected = new ArrayList<Game>();
		expected.add(new Game());
		Mockito.when(template.find(Mockito.any(Query.class), Mockito.eq(Game.class))).thenReturn(expected);
		Collection<Game> actual = gameRepository.getAllByPJ(pjIds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void givenIdThenReturnGame(){
		Integer id = 1;
		Game expected = new Game();
		expected.setId(id);
		Mockito.when(template.findById(id, Game.class)).thenReturn(expected);
		Game actual = gameRepository.getById(id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void givenGameThenSaveIt(){
		Game game = new Game();
		game.setId(1);
		gameRepository.saveOrUpdate(game);
		Mockito.verify(template).save(game);
	}
	
	@Test
	public void givenIdThenDeleteIt(){
		Integer gameId=1;
		Game game = new Game();
		game.setId(gameId);
		gameRepository.remove(gameId);
		Mockito.verify(template).remove(game);
	}

}
