package com.gestion.rel.binding.serializer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestion.rel.binding.serializer.helper.CharacterHelper;
import com.gestion.rel.binding.serializer.helper.GameHelper;
import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Game;
import com.gestion.rel.domain.User;

public class UserSerializerTest {

	@Mock
	private HttpServletRequest request;

	@InjectMocks
	private UserSerializer userSerializer;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenUserThenSerializeIt() throws JsonProcessingException, IOException {
		String id = "id1";
		Integer idMaster = 1;
		Integer idPlayer = 2;
		Integer idCharacter = 3;
		String prevUrl = "http://pepe.com";
		JsonGenerator jsonGenerator = mock(JsonGenerator.class);
		Game gameMaster = new Game();
		gameMaster.setId(idMaster);
		Game gamePlayer = new Game();
		gamePlayer.setId(idPlayer);
		Character character = new Character();
		character.setGame(idPlayer);
		character.setId(idCharacter);
		User user = new User();
		user.setId(id);
		user.setCharacters(Arrays.asList(new Character[]{character}));
		user.setGamesMaster(Arrays.asList(new Game[]{gameMaster}));
		user.setGamesPlayer(Arrays.asList(new Game[]{gamePlayer}));
		final Map<String, Object> map = new HashMap<String, Object>();
		when(request.getContextPath()).thenReturn(prevUrl);
		map.put("id", id);
		map.put("link", new StringBuilder(prevUrl).append("/user/").append(id).toString());
		Collection<Map<String, Object>> charactersList = new ArrayList<Map<String,Object>>();
		charactersList.add(CharacterHelper.getInstance(request).getMap(character.getGame(),character.getId()));
		map.put("characters", charactersList);
		Collection<Map<String, Object>> gamesMasterList = new ArrayList<Map<String,Object>>();
		gamesMasterList.add(GameHelper.getInstance(request).getMap(user.getGamesMaster().stream().map(f->f.getId()).findFirst().get()));
		map.put("gamesMaster", gamesMasterList);
		Collection<Map<String, Object>> gamesPlayerList = new ArrayList<Map<String,Object>>();
		gamesPlayerList.add(GameHelper.getInstance(request).getMap(user.getGamesPlayer().stream().map(f->f.getId()).findFirst().get()));
		map.put("gamesPlayer", gamesPlayerList);
		when(request.getContextPath()).thenReturn(prevUrl);
		userSerializer.serialize(user, jsonGenerator, null);
		verify(jsonGenerator).writeObject(map);
	}
}
