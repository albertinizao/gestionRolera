package com.gestion.rel.binding.serializer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
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
import com.gestion.rel.domain.Character;
import com.gestion.rel.utils.UrlPathConstants;

public class CharacterSerializerTest {

	@Mock
	private HttpServletRequest request;

	@InjectMocks
	private CharacterSerializer characterSerializer;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenCharacterThenSerializeIt() throws JsonProcessingException, IOException {
		Integer id = 1;
		String name = "name";
		String prevUrl = "http://pepe.com";
		Integer idGame = 2;
		JsonGenerator jsonGenerator = mock(JsonGenerator.class);
		Character character = new Character();
		character.setId(id);
		character.setName(name);
		character.setGame(idGame);
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", character.getId());
		map.put("name", character.getName());
		map.put("link",
		        new StringBuilder(prevUrl).append("/").append(UrlPathConstants.GAME).append("/")
		                .append(idGame).append("/").append(UrlPathConstants.CHARACTER).append("/").append(id)
		                .toString());
		final Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", idGame);
		map2.put("link", new StringBuilder(prevUrl).append("/").append(UrlPathConstants.GAME).append("/")
		        .append(idGame).toString());
		map.put("game", map2);
		when(request.getContextPath()).thenReturn(prevUrl);
		characterSerializer.serialize(character, jsonGenerator, null);
		verify(jsonGenerator).writeObject(map);
	}
}
