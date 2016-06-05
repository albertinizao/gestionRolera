package com.gestion.rel.binding.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gestion.rel.binding.serializer.helper.CharacterHelper;
import com.gestion.rel.binding.serializer.helper.GameHelper;
import com.gestion.rel.binding.serializer.helper.RelationHelper;
import com.gestion.rel.binding.serializer.helper.UserHelper;
import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Relation;
import com.gestion.rel.domain.Relationship;
import com.gestion.rel.utils.UrlPathConstants;

public class CharacterSerializer extends JsonSerializer<Character> {

	@Autowired
	private HttpServletRequest request;

	private void addRelationToRelationMap(Map<String, Object> relationMap, String urlRelationPath, Character character,
			Optional<Relation> relation) {
		if (relation.isPresent()) {
			relationMap.put(urlRelationPath, RelationHelper.getInstance(request).getMap(urlRelationPath,
					character.getGame(), character.getId(), relation.get().getValue()));
		}
	}

	@Override
	public void serialize(Character arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", arg0.getId());
		map.put("name", arg0.getName());
		map.put("game", GameHelper.getInstance(request).getMap(arg0.getGame()));
		map.put("link", CharacterHelper.getInstance(request).getLink(arg0.getGame(), arg0.getId()));
		Collection<Relationship> relationShips = arg0.getRelationShips();
		if (relationShips != null) {
			Collection<Map<String, Object>> relationShipList = new ArrayList<Map<String, Object>>();
			for (Relationship relation : relationShips) {
				Map<String, Object> relationMap = new HashMap<String, Object>();
				relationMap.put("character",
						CharacterHelper.getInstance(request).getMap(arg0.getGame(), relation.getCharacterId()));

				addRelationToRelationMap(relationMap, UrlPathConstants.WORKING, arg0, relation.getMaxWorking());
				addRelationToRelationMap(relationMap, UrlPathConstants.CONFIDENTIAL, arg0, relation.getMaxConfidential());
				addRelationToRelationMap(relationMap, UrlPathConstants.LOYALTY, arg0, relation.getMaxLoyalty());
				addRelationToRelationMap(relationMap, UrlPathConstants.TRUST, arg0, relation.getMaxTrust());
				addRelationToRelationMap(relationMap, UrlPathConstants.RESPECT, arg0, relation.getMaxRespect());
				addRelationToRelationMap(relationMap, UrlPathConstants.FUNNY, arg0, relation.getMaxFunny());
				addRelationToRelationMap(relationMap, UrlPathConstants.AFFECTION, arg0, relation.getMaxAffection());

				relationMap.put("relation", relation.getAverage());

				relationShipList.add(relationMap);
			}
			map.put("relationship", relationShipList);
		}
		Collection<Map<String, Object>> masterList = arg0.getUsers().stream()
				.map(f -> UserHelper.getInstance(request).getMap(f)).collect(Collectors.toList());
		if (!masterList.isEmpty()) {
			map.put("users", masterList);
		}
		arg1.writeObject(map);
	}

}
