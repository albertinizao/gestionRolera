package com.gestion.rel.binding.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gestion.rel.domain.Partida;

public class PartidaSerializer extends JsonSerializer<Partida> {

	@Autowired
	private HttpServletRequest request;

	@Override
	public void serialize(Partida arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException,
	        JsonProcessingException {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", arg0.getId());
		map.put("name", arg0.getName());
		Collection<Integer> characters = arg0.getCharacters();
		if (characters!=null){
			Collection<Map<String, Object>> characterList = new ArrayList<Map<String,Object>>();
			for (Integer character:characters){
				final Map<String, Object> characterMap = new HashMap<String, Object>();
				characterMap.put("id", character);
				characterMap.put("link",new StringBuilder(request.getContextPath()).append("/partida/").append(arg0.getId()).append("/personaje/").append(character).toString());
				characterList.add(characterMap);
			}
			map.put("characters", characterList);
		}
		map.put("link", new StringBuilder(request.getContextPath()).append("/partida/").append(arg0.getId()).toString());
		arg1.writeObject(map);
	}

}
