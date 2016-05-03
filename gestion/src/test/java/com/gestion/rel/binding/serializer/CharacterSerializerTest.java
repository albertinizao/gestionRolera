package com.gestion.rel.binding.serializer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Relation;
import com.gestion.rel.domain.Relationship;
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
		map.put("relationship", new ArrayList<Map<String, Object>>());
		when(request.getContextPath()).thenReturn(prevUrl);
		characterSerializer.serialize(character, jsonGenerator, null);
		verify(jsonGenerator).writeObject(map);
	}

	@Test
	public void givenCharacterWitinRelationshipThenSerializeIt() throws JsonProcessingException, IOException {
		Integer id = 1;
		String name = "name";
		String prevUrl = "http://pepe.com";
		Integer idGame = 2;
		Integer otherPJId = 3;
		JsonGenerator jsonGenerator = mock(JsonGenerator.class);
		Character character = new Character();
		character.setId(id);
		character.setName(name);
		character.setGame(idGame);
		Collection<Relationship> relationShips = new ArrayList<Relationship>();
		Relationship relationShip = new Relationship();
		relationShip.setCharacterId(otherPJId);
		relationShips.add(relationShip);
		character.setRelationShips(relationShips);
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
		final Map<String, Object> map3 = new HashMap<String, Object>();
		final Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("id", otherPJId);
		map4.put("link",
		        new StringBuilder(prevUrl).append("/").append(UrlPathConstants.GAME).append("/")
		                .append(idGame).append("/").append(UrlPathConstants.CHARACTER).append("/").append(otherPJId)
		                .toString());
		map3.put("character", map4);
		map3.put("relation", 0D);
		Collection<Map<String, Object>> relationShipList = new ArrayList<Map<String, Object>>();
		relationShipList.add(map3);
		map.put("relationship", relationShipList);
		when(request.getContextPath()).thenReturn(prevUrl);
		characterSerializer.serialize(character, jsonGenerator, null);
		verify(jsonGenerator).writeObject(map);
	}


	@Test
	public void givenCharacterWithRelationThenSerializeIt() throws JsonProcessingException, IOException {
		Integer id = 1;
		String name = "name";
		String prevUrl = "http://pepe.com";
		Integer idGame = 2;
		Integer otherPJId = 2;
		Double affectionValue = 1D;
		Long date = 1L;
		Collection<Relation> affection = new ArrayList<Relation>();
		affection.add(new Relation(date++, affectionValue));
		Double workingValue = 2D;
		Collection<Relation> working = new ArrayList<Relation>();
		working.add(new Relation(date++, workingValue));
		Double confidentialValue = 3D;
		Collection<Relation> confidential = new ArrayList<Relation>();
		confidential.add(new Relation(date++, confidentialValue));
		Double funnyValue = 4D;
		Collection<Relation> funny = new ArrayList<Relation>();
		funny.add(new Relation(date++, funnyValue));
		Double loyaltyValue = 5D;
		Collection<Relation> loyalty = new ArrayList<Relation>();
		loyalty.add(new Relation(date++, loyaltyValue));
		Double respectValue = 6D;
		Collection<Relation> respect = new ArrayList<Relation>();
		respect.add(new Relation(date++, respectValue));
		Double trustValue = 7D;
		Collection<Relation> trust = new ArrayList<Relation>();
		trust.add(new Relation(date++, trustValue));
		JsonGenerator jsonGenerator = mock(JsonGenerator.class);
		Character character = new Character();
		character.setId(id);
		character.setName(name);
		character.setGame(idGame);
		Collection<Relationship> relationShips = new ArrayList<Relationship>();
		Relationship relationShip = new Relationship();
		relationShip.setCharacterId(otherPJId);
		relationShip.setAffection(affection);
		relationShip.setWorking(working);
		relationShip.setConfidential(confidential);
		relationShip.setFunny(funny);
		relationShip.setLoyalty(loyalty);
		relationShip.setRespect(respect);
		relationShip.setTrust(trust);
		relationShips.add(relationShip);
		character.setRelationShips(relationShips);
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
//		verify(jsonGenerator).writeObject(JSONToMap.jsonToMap("{game={link=\"http://pepe.com/game/2\", id=\"2\"}, name=name, link=\"http://pepe.com/game/2/character/1\", id=\"1\", relationship=[{trust={link=\"http://pepe.com/game/2/character/1/relationship/trust\", value=7.0}, character={link=\"http://pepe.com/game/2/character/2\", id=\"2\"}, loyalty={link=\"http://pepe.com/game/2/character/1/relationship/loyalty\", value=5.0}, affection={link=\"http://pepe.com/game/2/character/1/relationship/affection\", value=1.0}, working={link=\"http://pepe.com/game/2/character/1/relationship/working\", value=2.0}, respect={link=\"http://pepe.com/game/2/character/1/relationship/respect\", value=6.0}, funny={link=\"http://pepe.com/game/2/character/1/relationship/funny\", value=4.0}, confidential={link=\"http://pepe.com/game/2/character/1/relationship/confidential\", value=3.0}, relation=4.0}]}"));
	}
}
