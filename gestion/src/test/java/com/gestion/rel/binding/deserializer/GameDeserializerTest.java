package com.gestion.rel.binding.deserializer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gestion.rel.domain.Game;

public class GameDeserializerTest {

	@InjectMocks
	private GameDeserializer gameDeserializer;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenGameThenDeserializeIt() throws JsonProcessingException, IOException {
		final GameDeserializer deserializer = gameDeserializer;
		final JsonParser mockParser = mock(JsonParser.class);
		final ObjectCodec mock = mock(ObjectCodec.class);

		when(mockParser.getCodec()).thenReturn(mock);
		JsonNodeFactory factory = new JsonNodeFactory(false);

		Integer id = 1;
		String name = "name";
		String prevUrl = "http://pepe.com";
		JsonGenerator jsonGenerator = mock(JsonGenerator.class);
		Game game = new Game();
		game.setId(id);
		game.setName(name);
		final ObjectNode map = factory.objectNode();
		map.put("id", game.getId());
		map.put("name", game.getName());
		map.put("link", new StringBuilder(prevUrl).append("/game/").append(game.getId()).toString());
		when(mock.readTree(mockParser)).thenReturn(map);
		Game actual = gameDeserializer.deserialize(mockParser, null);
		assertEquals(game, actual);
	}

	@Test
	public void givenGameWithCharactersThenDeserializeIt() throws JsonProcessingException, IOException {
		final GameDeserializer deserializer = gameDeserializer;
		final JsonParser mockParser = mock(JsonParser.class);
		final ObjectCodec mock = mock(ObjectCodec.class);

		when(mockParser.getCodec()).thenReturn(mock);
		JsonNodeFactory factory = new JsonNodeFactory(false);

		Integer id = 1;
		String name = "name";
		String prevUrl = "http://pepe.com";
		JsonGenerator jsonGenerator = mock(JsonGenerator.class);
		Game game = new Game();
		game.setId(id);
		game.setName(name);
		Integer[] characters = { 1, 2 };
		game.setCharacters(Arrays.asList(characters));
		final ObjectNode map = factory.objectNode();
		map.put("id", game.getId());
		map.put("name", game.getName());
		ArrayNode charactersList = factory.arrayNode();
		for (Integer charId : characters) {
			ObjectNode charMap = factory.objectNode();
			charMap.put("id", charId);
			charMap.put("link", new StringBuilder(prevUrl).append("/game/").append(game.getId()).append("/character/")
			        .append(charId).toString());
			charactersList.add(charMap);
		}
		map.put("characters", charactersList);
		map.put("link", new StringBuilder(prevUrl).append("/game/").append(game.getId()).toString());
		when(mock.readTree(mockParser)).thenReturn(map);
		Game actual = gameDeserializer.deserialize(mockParser, null);
		assertEquals(game, actual);
	}
}
