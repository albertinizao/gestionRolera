package com.gestion.rel.domain;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RelationshipTest {

	@InjectMocks
	private Relationship relationship;

	@Test
	public void existConstuctor() {
		assertNotNull(new Relationship());
	}
	
	@Test
	public void hasDestinationCharacterAttribute(){
		Integer characterId = 5;
		relationship.setCharacterId(characterId);
		assertEquals(characterId,relationship.getCharacterId());
	}
	
	@Test
	public void hasWorkingAttribute(){
		Map<Date, Double> working = new TreeMap<Date, Double>();
		working.put(new Date(), 5D);
		relationship.setWorking(working);
		assertEquals(working,relationship.getWorking());
	}
	
	@Test
	public void hasConfidentialAttribute(){
		Map<Date, Double> confidential = new TreeMap<Date, Double>();
		confidential.put(new Date(), 5D);
		relationship.setConfidential(confidential);
		assertEquals(confidential,relationship.getConfidential());
	}
	
	@Test
	public void hasTrustAttribute(){
		Map<Date, Double> trust = new TreeMap<Date, Double>();
		trust.put(new Date(), 5D);
		relationship.setTrust(trust);
		assertEquals(trust,relationship.getTrust());
	}
	
	@Test
	public void hasRespectAttribute(){
		Map<Date, Double> respect = new TreeMap<Date, Double>();
		respect.put(new Date(), 5D);
		relationship.setRespect(respect);
		assertEquals(respect,relationship.getRespect());
	}
	
	@Test
	public void hasFunnyAttribute(){
		Map<Date, Double> funny = new TreeMap<Date, Double>();
		funny.put(new Date(), 5D);
		relationship.setFunny(funny);
		assertEquals(funny,relationship.getFunny());
	}
	
	@Test
	public void hasLoyaltyAttribute(){
		Map<Date, Double> loyalty = new TreeMap<Date, Double>();
		loyalty.put(new Date(), 5D);
		relationship.setLoyalty(loyalty);
		assertEquals(loyalty,relationship.getLoyalty());
	}
	
	@Test
	public void hasAffectionAttribute(){
		Map<Date, Double> affection = new TreeMap<Date, Double>();
		affection.put(new Date(), 5D);
		relationship.setAffection(affection);
		assertEquals(affection,relationship.getAffection());
	}
	
	@Test
	public void getAverage(){
		Map<Date, Double> working = new TreeMap<Date, Double>();
		working.put(new Date(), 5D);
		relationship.setWorking(working);
		Map<Date, Double> confidential = new TreeMap<Date, Double>();
		confidential.put(new Date(), 5D);
		relationship.setConfidential(confidential);
		Map<Date, Double> trust = new TreeMap<Date, Double>();
		trust.put(new Date(), 5D);
		relationship.setTrust(trust);
		Map<Date, Double> respect = new TreeMap<Date, Double>();
		respect.put(new Date(), 5D);
		relationship.setRespect(respect);
		Map<Date, Double> funny = new TreeMap<Date, Double>();
		funny.put(new Date(), 5D);
		relationship.setFunny(funny);
		Map<Date, Double> loyalty = new TreeMap<Date, Double>();
		loyalty.put(new Date(), 5D);
		relationship.setLoyalty(loyalty);
		Map<Date, Double> affection = new TreeMap<Date, Double>();
		affection.put(new Date(), 5D);
		relationship.setAffection(affection);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.DAY_OF_MONTH, 1);
		Date future = gc.getTime();
		Double value = 0D;
		working.put(future, ++value);
		confidential.put(future, ++value);
		trust.put(future, ++value);
		respect.put(future, ++value);
		funny.put(future, ++value);
		loyalty.put(future, ++value);
		affection.put(future, ++value);
		Double expected = value/2;
		Double actual = relationship.getAverage();
		assertEquals(expected,actual);
		
		
	}
}
