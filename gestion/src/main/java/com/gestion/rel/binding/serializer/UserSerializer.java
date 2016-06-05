package com.gestion.rel.binding.serializer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gestion.rel.binding.serializer.helper.CharacterHelper;
import com.gestion.rel.binding.serializer.helper.GameHelper;
import com.gestion.rel.binding.serializer.helper.UserHelper;
import com.gestion.rel.domain.Game;
import com.gestion.rel.domain.User;

public class UserSerializer extends JsonSerializer<User> {

	@Autowired
	private HttpServletRequest request;

	@Override
	public void serialize(User arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", arg0.getId());
		Collection<Map<String, Object>> characterList = arg0.getCharacters().stream()
				.map(f -> CharacterHelper.getInstance(request).getMap(f.getGame(), f.getId())).collect(Collectors.toList());
		if (!characterList.isEmpty()) {
			map.put("characters", characterList);
		}
		Collection<Map<String, Object>> masterList = arg0.getGamesMaster().stream()
				.map(f -> GameHelper.getInstance(request).getMap(f.getId())).collect(Collectors.toList());
		if (!masterList.isEmpty()) {
			map.put("gamesMaster", masterList);
		}
		Collection<Map<String, Object>> playerList = arg0.getGamesPlayer().stream()
				.map(f -> GameHelper.getInstance(request).getMap(f.getId())).collect(Collectors.toList());
		if (!playerList.isEmpty()) {
			map.put("gamesPlayer", playerList);
		}
		map.put("link", UserHelper.getInstance(request).getLink(arg0.getId()));
		arg1.writeObject(map);
	}

}
