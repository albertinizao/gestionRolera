package com.gestion.rel.domain;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

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
		Collection<Relation> working = new ArrayList<Relation>();
		working.add(new Relation(1L, 5D));
		relationship.setWorking(working);
		assertEquals(working,relationship.getWorking());
	}
	
	@Test
	public void hasConfidentialAttribute(){
		Collection<Relation> confidential = new ArrayList<Relation>();
		confidential.add(new Relation(1L, 5D));
		relationship.setConfidential(confidential);
		assertEquals(confidential,relationship.getConfidential());
	}
	
	@Test
	public void hasTrustAttribute(){
		Collection<Relation> trust = new ArrayList<Relation>();
		trust.add(new Relation(1L, 5D));
		relationship.setTrust(trust);
		assertEquals(trust,relationship.getTrust());
	}
	
	@Test
	public void hasRespectAttribute(){
		Collection<Relation> respect = new ArrayList<Relation>();
		respect.add(new Relation(1L, 5D));
		relationship.setRespect(respect);
		assertEquals(respect,relationship.getRespect());
	}
	
	@Test
	public void hasFunnyAttribute(){
		Collection<Relation> funny = new ArrayList<Relation>();
		funny.add(new Relation(1L, 5D));
		relationship.setFunny(funny);
		assertEquals(funny,relationship.getFunny());
	}
	
	@Test
	public void hasLoyaltyAttribute(){
		Collection<Relation> loyalty = new ArrayList<Relation>();
		loyalty.add(new Relation(1L, 5D));
		relationship.setLoyalty(loyalty);
		assertEquals(loyalty,relationship.getLoyalty());
	}
	
	@Test
	public void hasAffectionAttribute(){
		Collection<Relation> affection = new ArrayList<Relation>();
		affection.add(new Relation(1L, 5D));
		relationship.setAffection(affection);
		assertEquals(affection,relationship.getAffection());
	}
	
	@Test
	public void getMaxAffection(){
		Double max = 5D;
		Collection<Relation> map = new ArrayList<Relation>();
		map.add(new Relation(1L, max));
		relationship.setAffection(map);
		assertEquals(max,relationship.getMaxAffection().get().getValue());
	}
	
	@Test
	public void getMaxConfidential(){
		Double max = 5D;
		Collection<Relation> map = new ArrayList<Relation>();
		map.add(new Relation(1L, max));
		relationship.setConfidential(map);
		assertEquals(max,relationship.getMaxConfidential().get().getValue());
	}
	
	@Test
	public void getMaxFunny(){
		Double max = 5D;
		Collection<Relation> map = new ArrayList<Relation>();
		map.add(new Relation(1L, max));
		relationship.setFunny(map);
		assertEquals(max,relationship.getMaxFunny().get().getValue());
	}
	
	@Test
	public void getMaxLoyalty(){
		Double max = 5D;
		Collection<Relation> map = new ArrayList<Relation>();
		map.add(new Relation(1L, max));
		relationship.setLoyalty(map);
		assertEquals(max,relationship.getMaxLoyalty().get().getValue());
	}
	
	@Test
	public void getMaxRespect(){
		Double max = 5D;
		Collection<Relation> map = new ArrayList<Relation>();
		map.add(new Relation(1L, max));
		relationship.setRespect(map);
		assertEquals(max,relationship.getMaxRespect().get().getValue());
	}
	
	@Test
	public void getMaxTrust(){
		Double max = 5D;
		Collection<Relation> map = new ArrayList<Relation>();
		map.add(new Relation(1L, max));
		relationship.setTrust(map);
		assertEquals(max,relationship.getMaxTrust().get().getValue());
	}
	
	@Test
	public void getMaxWorking(){
		Double max = 5D;
		Collection<Relation> map = new ArrayList<Relation>();
		map.add(new Relation(1L, max));
		relationship.setWorking(map);
		assertEquals(max,relationship.getMaxWorking().get().getValue());
	}
	
	@Test
	public void getAverage(){
		Date dateDate = new Date();
		Long date = dateDate.getTime();
		Collection<Relation> working = new ArrayList<Relation>();
		working.add(new Relation(date++, 5D));
		relationship.setWorking(working);
		Collection<Relation> confidential = new ArrayList<Relation>();
		confidential.add(new Relation(date++, 5D));
		relationship.setConfidential(confidential);
		Collection<Relation> trust = new ArrayList<Relation>();
		trust.add(new Relation(date++, 5D));
		relationship.setTrust(trust);
		Collection<Relation> respect = new ArrayList<Relation>();
		respect.add(new Relation(date++, 5D));
		relationship.setRespect(respect);
		Collection<Relation> funny = new ArrayList<Relation>();
		funny.add(new Relation(date++, 5D));
		relationship.setFunny(funny);
		Collection<Relation> loyalty = new ArrayList<Relation>();
		loyalty.add(new Relation(date++, 5D));
		relationship.setLoyalty(loyalty);
		Collection<Relation> affection = new ArrayList<Relation>();
		affection.add(new Relation(date++, 5D));
		relationship.setAffection(affection);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dateDate);
		gc.add(GregorianCalendar.DAY_OF_MONTH, 1);
		Date future = gc.getTime();
		Double value = 0D;
		working.add(new Relation(future.getTime(), ++value));
		confidential.add(new Relation(future.getTime(), ++value));
		trust.add(new Relation(future.getTime(), ++value));
		respect.add(new Relation(future.getTime(), ++value));
		funny.add(new Relation(future.getTime(), ++value));
		loyalty.add(new Relation(future.getTime(), ++value));
		affection.add(new Relation(future.getTime(), ++value));
		Double expected = (1+value)/2;
		Double actual = relationship.getAverage();
		assertEquals(expected,actual);
		
		
	}
}
