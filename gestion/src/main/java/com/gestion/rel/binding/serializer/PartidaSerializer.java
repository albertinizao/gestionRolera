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
		map.put("link", new StringBuilder(request.getContextPath()).append("/partida/").append(arg0.getId()).toString());
		arg1.writeObject(map);
	}

}
