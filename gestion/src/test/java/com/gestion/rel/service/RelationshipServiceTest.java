package com.gestion.rel.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.gestion.rel.domain.Relation;
import com.gestion.rel.domain.Relationship;
import com.gestion.rel.repository.RelationshipRepository;

@RunWith(MockitoJUnitRunner.class)
public class RelationshipServiceTest {

	@Mock
	private RelationshipRepository relationshipRepository;

	@InjectMocks
	private RelationshipService relationshipService;
	
	@Test
	public void getAll(){
		Integer charId=1;
		Collection<Relationship> expected = new ArrayList<Relationship>();
		expected.add(new Relationship());
		Mockito.when(relationshipRepository.getAll(charId)).thenReturn(expected);
		Collection<Relationship> actual = relationshipService.getAll(charId);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getByOtherCharacter(){
		Relationship expected = new Relationship();
		Integer charId=1;
		Integer otherId=2;
		Mockito.when(relationshipRepository.getByOtherCharacter(charId, otherId)).thenReturn(expected);
		Relationship actual = relationshipService.getByOtherCharacter(charId, otherId);
		assertEquals(expected, actual);
	}
	
	@Test
	public void get(){ 
		Collection<Relation> expected = new ArrayList<Relation>();
		Integer charId=1; Integer otherId=2; String type="Pepe";
		expected.add(new Relation(1L, 1D));
		Mockito.when(relationshipRepository.get(charId, otherId, type)).thenReturn(expected);
		Collection<Relation> actual = relationshipService.get(charId, otherId, type);
		assertEquals(expected, actual);
	}
	
	@Test
	public void addValue(){
		Integer charId=1;
		Integer otherId=2;
		String type="type";
		Double newValue=2D;
		relationshipService.addValue(charId, otherId, type, newValue);
		Mockito.verify(relationshipRepository).addValue(charId, otherId, type, newValue);
	}
	
	@Test
	public void remove(){
		Integer charId=1;
		Integer otherId=2;
		String type="type";
		Long date=1L;
		relationshipService.remove(charId, otherId, type, date);
		Mockito.verify(relationshipRepository).remove(charId, otherId, type, date);
	}

}
