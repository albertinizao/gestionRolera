package com.gestion.rel.binding.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.gestion.rel.domain.Game;

public class GameDeserializer extends JsonDeserializer<Game> {

	@Override
	public Game deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException,
	        JsonProcessingException {
		Game game = new Game();
		ObjectCodec oc = arg0.getCodec();
		JsonNode node = oc.readTree(arg0);
		game.setId(node.get("id").asInt());
		game.setName(node.get("name").asText());
		JsonNode charactersNode = node.get("characters");
		if (charactersNode!=null){
			Collection<Integer> characterIds = new ArrayList<Integer>();
			Iterator<JsonNode> i = charactersNode.iterator();
			do{
				JsonNode character = i.next();
				characterIds.add(character.get("id").asInt());
			}while(i.hasNext());
			game.setCharacters(characterIds);
		}
		return game;
	}

}
