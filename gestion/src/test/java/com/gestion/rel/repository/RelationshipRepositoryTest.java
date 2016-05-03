package com.gestion.rel.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Relation;
import com.gestion.rel.domain.Relationship;
import com.gestion.rel.utils.UrlPathConstants;

@RunWith(MockitoJUnitRunner.class)
public class RelationshipRepositoryTest {

	@Mock
	private MongoTemplate template;

	@Mock
	private CharacterRepository characterRepository;

	@InjectMocks
	private RelationshipRepository relationshipRepository;

	private static Integer CHAR_ID = 1;

	@Test
	public void givenCharIdThenGetRelation() {
		Character character = new Character();
		Collection<Relationship> expected = new ArrayList<Relationship>();
		expected.add(new Relationship());
		character.setRelationShips(expected);
		Mockito.when(characterRepository.getById(CHAR_ID)).thenReturn(character);
		Collection<Relationship> actual = relationshipRepository.getAll(CHAR_ID);
		assertEquals(expected, actual);
	}

	@Test
	public void givenCharIdAndOtherIdThenGetRelationship() {
		Integer otherId = 2;
		Character character = new Character();
		Collection<Relationship> collection = new ArrayList<Relationship>();
		collection.add(new Relationship(otherId - 1));
		Relationship expected = new Relationship(otherId);
		collection.add(expected);
		collection.add(new Relationship(otherId + 1));
		character.setRelationShips(collection);
		Mockito.when(characterRepository.getById(CHAR_ID)).thenReturn(character);
		Relationship actual = relationshipRepository.getByOtherCharacter(CHAR_ID, otherId);
		assertEquals(expected, actual);
	}

	@Test
	public void getRelationMapByAffection(){
		Relationship relationship = new Relationship();
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setAffection(expected);
		Collection<Relation> actual = relationshipRepository.getRelationMapByType(relationship, UrlPathConstants.AFFECTION);
		assertEquals(expected,actual);		
	}

	@Test
	public void getRelationMapByConfidential(){
		Relationship relationship = new Relationship();
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setConfidential(expected);
		Collection<Relation> actual = relationshipRepository.getRelationMapByType(relationship, UrlPathConstants.CONFIDENTIAL);
		assertEquals(expected,actual);		
	}

	@Test
	public void getRelationMapByFunny(){
		Relationship relationship = new Relationship();
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setFunny(expected);
		Collection<Relation> actual = relationshipRepository.getRelationMapByType(relationship, UrlPathConstants.FUNNY);
		assertEquals(expected,actual);		
	}

	@Test
	public void getRelationMapByLoyalty(){
		Relationship relationship = new Relationship();
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setLoyalty(expected);
		Collection<Relation> actual = relationshipRepository.getRelationMapByType(relationship, UrlPathConstants.LOYALTY);
		assertEquals(expected,actual);		
	}

	@Test
	public void getRelationMapByRespect(){
		Relationship relationship = new Relationship();
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setRespect(expected);
		Collection<Relation> actual = relationshipRepository.getRelationMapByType(relationship, UrlPathConstants.RESPECT);
		assertEquals(expected,actual);		
	}

	@Test
	public void getRelationMapByTrust(){
		Relationship relationship = new Relationship();
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setTrust(expected);
		Collection<Relation> actual = relationshipRepository.getRelationMapByType(relationship, UrlPathConstants.TRUST);
		assertEquals(expected,actual);		
	}

	@Test
	public void getRelationMapByWorking(){
		Relationship relationship = new Relationship();
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setWorking(expected);
		Collection<Relation> actual = relationshipRepository.getRelationMapByType(relationship, UrlPathConstants.WORKING);
		assertEquals(expected,actual);		
	}

	@Test(expected=IllegalArgumentException.class)
	public void getRelationMapByMiss(){
		Relationship relationship = new Relationship();
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setWorking(expected);
		Collection<Relation> actual = relationshipRepository.getRelationMapByType(relationship, "falso");
	}

	@Test
	public void givenCharIdOtherCharIdAndTypeReturnRelationshipMap(){
		Integer otherId = 2;
		Character character = new Character();
		Collection<Relationship> collection = new ArrayList<Relationship>();
		collection.add(new Relationship(otherId-1));
		Relationship relationship = new Relationship(otherId);
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setWorking(expected);
		collection.add(relationship);
		collection.add(new Relationship(otherId+1));
		character.setRelationShips(collection);
		Mockito.when(characterRepository.getById(CHAR_ID)).thenReturn(character);
		Collection<Relation> actual = relationshipRepository.get(CHAR_ID, otherId, UrlPathConstants.WORKING);
		assertEquals(expected,actual);	
	}

	@Test
	public void addValue(){
		Double addToMax=1D;
		Double newValue = 8D;
		Integer otherId = 2;
		Character character = new Character();
		Collection<Relationship> collection = new ArrayList<Relationship>();
		collection.add(new Relationship(otherId-1));
		Relationship relationship = new Relationship(otherId);
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(1L, 5D));
		relationship.setWorking(expected);
		collection.add(relationship);
		collection.add(new Relationship(otherId+1));
		character.setRelationShips(collection);
		Mockito.when(characterRepository.getById(CHAR_ID)).thenReturn(character);
		Collection<Relation> previous = new ArrayList<Relation>(expected);
		Relationship relPrevious = new Relationship(otherId);
		relPrevious.setWorking(previous);
		relationshipRepository.addValue(CHAR_ID, otherId, UrlPathConstants.WORKING, relationship.getMaxWorking().get().getValue()+addToMax);
		ArgumentCaptor<Character> firstFooCaptor = ArgumentCaptor.forClass(Character.class);
		Mockito.verify(characterRepository).saveOrUpdate(firstFooCaptor.capture());
		Collection<Relationship> relations = firstFooCaptor.getValue().getRelationShips();
		Relationship actual = relations.stream().filter(r -> r.getCharacterId().equals(otherId)).findFirst().orElse(new Relationship(otherId));

		assertNotEquals(relPrevious,actual);
		assertEquals(new Double(relPrevious.getMaxWorking().get().getValue()+addToMax), actual.getMaxWorking().get().getValue());
	}

	@Test
	public void remove() {
		Double addToMax = 1D;
		Double oldValue = 4D;
		Double newValue = oldValue * 2;
		Integer otherId = 2;
		Date date = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.DAY_OF_MONTH, -1);
		Character character = new Character();
		Collection<Relationship> collection = new ArrayList<Relationship>();
		collection.add(new Relationship(otherId - 1));
		Relationship relationship = new Relationship(otherId);
		Collection<Relation> expected = new ArrayList<Relation>();
		expected.add(new Relation(date.getTime(), newValue));
		expected.add(new Relation(gc.getTime().getTime(), oldValue));// always less than other
		relationship.setWorking(expected);
		collection.add(relationship);
		collection.add(new Relationship(otherId + 1));
		character.setRelationShips(collection);
		Mockito.when(characterRepository.getById(CHAR_ID)).thenReturn(character);
		Collection<Relation> previous = new ArrayList<Relation>(expected);
		Relationship relPrevious = new Relationship(otherId);
		relPrevious.setWorking(previous);
		relationshipRepository.remove(CHAR_ID, otherId, UrlPathConstants.WORKING, date.getTime());
		ArgumentCaptor<Character> firstFooCaptor = ArgumentCaptor.forClass(Character.class);
		Mockito.verify(characterRepository).saveOrUpdate(firstFooCaptor.capture());
		Collection<Relationship> relations = firstFooCaptor.getValue().getRelationShips();
		Relationship actual = relations.stream().filter(r -> r.getCharacterId().equals(otherId)).findFirst()
		        .orElse(new Relationship(otherId));

		assertNotEquals(relPrevious, actual);
		assertEquals(oldValue, actual.getMaxWorking().get().getValue());
	}

}
