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

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestion.rel.domain.Partida;

public class PartidaSerializerTest {

	@Mock
	private HttpServletRequest request;

	@InjectMocks
	private PartidaSerializer partidaSerializer;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenPartidaThenSerializeIt() throws JsonProcessingException, IOException {
		Integer id = 1;
		String name = "name";
		String prevUrl = "http://pepe.com";
		JsonGenerator jsonGenerator = mock(JsonGenerator.class);
		Partida partida = new Partida();
		partida.setId(id);
		partida.setName(name);
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", partida.getId());
		map.put("name", partida.getName());
		map.put("link", new StringBuilder(prevUrl).append("/partida/").append(partida.getId()).toString());
		when(request.getContextPath()).thenReturn(prevUrl);
		partidaSerializer.serialize(partida, jsonGenerator, null);
		verify(jsonGenerator).writeObject(map);
	}

	@Test
	public void givenPartidaWithCharactersThenSerializeIt() throws JsonProcessingException, IOException {
		Integer id = 1;
		String name = "name";
		String prevUrl = "http://pepe.com";
		JsonGenerator jsonGenerator = mock(JsonGenerator.class);
		Partida partida = new Partida();
		partida.setId(id);
		partida.setName(name);
		Integer[] characters = {1,2};
		partida.setCharacters(Arrays.asList(characters));
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", partida.getId());
		map.put("name", partida.getName());
		Collection<Map<String, Object>> charactersList = new ArrayList<Map<String,Object>>();
		for (Integer charId:characters){
			Map<String, Object> charMap = new HashMap<String, Object>();
			charMap.put("id", charId);
			charMap.put("link", new StringBuilder(prevUrl).append("/partida/").append(partida.getId()).append("/personaje/").append(charId).toString());
			charactersList.add(charMap);
		}
		map.put("characters", charactersList);
		map.put("link", new StringBuilder(prevUrl).append("/partida/").append(partida.getId()).toString());
		when(request.getContextPath()).thenReturn(prevUrl);
		partidaSerializer.serialize(partida, jsonGenerator, null);
		verify(jsonGenerator).writeObject(map);
	}
}
