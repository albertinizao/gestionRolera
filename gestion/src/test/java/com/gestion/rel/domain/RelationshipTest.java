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
	public void getMaxAffection(){
		Double max = 5D;
		Map<Date, Double> map = new TreeMap<Date, Double>();
		map.put(new Date(), max);
		relationship.setAffection(map);
		assertEquals(max,relationship.getMaxAffection().get().getValue());
	}
	
	@Test
	public void getMaxConfidential(){
		Double max = 5D;
		Map<Date, Double> map = new TreeMap<Date, Double>();
		map.put(new Date(), max);
		relationship.setConfidential(map);
		assertEquals(max,relationship.getMaxConfidential().get().getValue());
	}
	
	@Test
	public void getMaxFunny(){
		Double max = 5D;
		Map<Date, Double> map = new TreeMap<Date, Double>();
		map.put(new Date(), max);
		relationship.setFunny(map);
		assertEquals(max,relationship.getMaxFunny().get().getValue());
	}
	
	@Test
	public void getMaxLoyalty(){
		Double max = 5D;
		Map<Date, Double> map = new TreeMap<Date, Double>();
		map.put(new Date(), max);
		relationship.setLoyalty(map);
		assertEquals(max,relationship.getMaxLoyalty().get().getValue());
	}
	
	@Test
	public void getMaxRespect(){
		Double max = 5D;
		Map<Date, Double> map = new TreeMap<Date, Double>();
		map.put(new Date(), max);
		relationship.setRespect(map);
		assertEquals(max,relationship.getMaxRespect().get().getValue());
	}
	
	@Test
	public void getMaxTrust(){
		Double max = 5D;
		Map<Date, Double> map = new TreeMap<Date, Double>();
		map.put(new Date(), max);
		relationship.setTrust(map);
		assertEquals(max,relationship.getMaxTrust().get().getValue());
	}
	
	@Test
	public void getMaxWorking(){
		Double max = 5D;
		Map<Date, Double> map = new TreeMap<Date, Double>();
		map.put(new Date(), max);
		relationship.setWorking(map);
		assertEquals(max,relationship.getMaxWorking().get().getValue());
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
		Double expected = (1+value)/2;
		Double actual = relationship.getAverage();
		assertEquals(expected,actual);
		
		
	}
}
