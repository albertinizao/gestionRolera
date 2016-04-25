package com.gestion.rel.binding.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Relationship;
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
		game.put("link",
		        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME).append("/")
		                .append(arg0.getGame()).toString());
		map.put("game", game);
		map.put("link",
		        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME).append("/")
		                .append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER).append("/")
		                .append(arg0.getId()).toString());
		Collection<Relationship> relationShips = arg0.getRelationShips();
		if (relationShips != null) {
			Collection<Map<String, Object>> relationShipList = new ArrayList<Map<String, Object>>();
			for (Relationship relation : relationShips) {
				final Map<String, Object> relationMap = new HashMap<String, Object>();

				final Map<String, Object> otherChar = new HashMap<String, Object>();
				otherChar.put("id", relation.getCharacterId());
				otherChar.put(
				        "link",
				        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
				                .append("/").append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER)
				                .append("/").append(relation.getCharacterId()).toString());
				relationMap.put("character", otherChar);

				final Map<String, Object> working = new HashMap<String, Object>();
				Optional<Entry<Date, Double>> optional = relation.getMaxWorking();
				if (optional.isPresent()) {
					working.put("value", optional.get().getValue());
					working.put("link",
					        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
					                .append("/").append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER)
					                .append("/").append(arg0.getId()).append("/").append(UrlPathConstants.RELATIONSHIP)
					                .append("/").append(UrlPathConstants.WORKING).toString());
					relationMap.put(UrlPathConstants.WORKING, working);
				}

				final Map<String, Object> confidential = new HashMap<String, Object>();
				optional = (relation.getMaxConfidential());
				if (optional.isPresent()) {
					confidential.put("value", optional.get().getValue());
					confidential.put("link",
					        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
					                .append("/").append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER)
					                .append("/").append(arg0.getId()).append("/").append(UrlPathConstants.RELATIONSHIP)
					                .append("/").append(UrlPathConstants.CONFIDENTIAL).toString());
					relationMap.put(UrlPathConstants.CONFIDENTIAL, confidential);
				}

				final Map<String, Object> loyalty = new HashMap<String, Object>();
				optional = (relation.getMaxLoyalty());
				if (optional.isPresent()) {
					loyalty.put("value", optional.get().getValue());
					loyalty.put("link",
					        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
					                .append("/").append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER)
					                .append("/").append(arg0.getId()).append("/").append(UrlPathConstants.RELATIONSHIP)
					                .append("/").append(UrlPathConstants.LOYALTY).toString());
					relationMap.put(UrlPathConstants.LOYALTY, loyalty);
				}

				final Map<String, Object> trust = new HashMap<String, Object>();
				optional = (relation.getMaxTrust());
				if (optional.isPresent()) {
					trust.put("value", optional.get().getValue());
					trust.put("link",
					        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
					                .append("/").append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER)
					                .append("/").append(arg0.getId()).append("/").append(UrlPathConstants.RELATIONSHIP)
					                .append("/").append(UrlPathConstants.TRUST).toString());
					relationMap.put(UrlPathConstants.TRUST, trust);
				}

				final Map<String, Object> respect = new HashMap<String, Object>();
				optional = (relation.getMaxRespect());
				if (optional.isPresent()) {
					respect.put("value", optional.get().getValue());
					respect.put("link",
					        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
					                .append("/").append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER)
					                .append("/").append(arg0.getId()).append("/").append(UrlPathConstants.RELATIONSHIP)
					                .append("/").append(UrlPathConstants.RESPECT).toString());
					relationMap.put(UrlPathConstants.RESPECT, respect);
				}

				final Map<String, Object> funny = new HashMap<String, Object>();
				optional = (relation.getMaxFunny());
				if (optional.isPresent()) {
					funny.put("value", optional.get().getValue());
					funny.put("link",
					        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
					                .append("/").append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER)
					                .append("/").append(arg0.getId()).append("/").append(UrlPathConstants.RELATIONSHIP)
					                .append("/").append(UrlPathConstants.FUNNY).toString());
					relationMap.put(UrlPathConstants.FUNNY, funny);
				}

				final Map<String, Object> affection = new HashMap<String, Object>();
				optional = (relation.getMaxAffection());
				if (optional.isPresent()) {
					affection.put("value", optional.get().getValue());
					affection.put("link",
					        new StringBuilder(request.getContextPath()).append("/").append(UrlPathConstants.GAME)
					                .append("/").append(arg0.getGame()).append("/").append(UrlPathConstants.CHARACTER)
					                .append("/").append(arg0.getId()).append("/").append(UrlPathConstants.RELATIONSHIP)
					                .append("/").append(UrlPathConstants.AFFECTION).toString());
					relationMap.put(UrlPathConstants.AFFECTION, affection);
				}

				relationMap.put("relation", relation.getAverage());

				relationShipList.add(relationMap);
			}
			map.put("relationship", relationShipList);
		}
		arg1.writeObject(map);
	}

}
