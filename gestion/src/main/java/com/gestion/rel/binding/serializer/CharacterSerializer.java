package com.gestion.rel.binding.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gestion.rel.domain.Character;
import com.gestion.rel.utils.UrlPathConstants;

public class CharacterSerializer extends JsonSerializer<Character> {

	@Autowired
	private HttpServletRequest request;

	@Override
	public void serialize(Character arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException,
	        JsonProcessingException {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", arg0.getId());
		map.put("name", arg0.getName());
		Map<String, Object> game = new HashMap<String, Object>();
		game.put("id", arg0.getGame());
		game.put("link", new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
		        .append("/").append(arg0.getGame()).toString());
		map.put("game", game);
		map.put("link",
		        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME).append("/")
		                .append(arg0.getId()).append("/").append(UrlPathConstants.CHARACTER).append("/")
		                .append(arg0.getId()).toString());
		arg1.writeObject(map);
	}

}
