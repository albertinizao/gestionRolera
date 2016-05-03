package com.gestion.rel.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.gestion.rel.domain.Relation;
import com.gestion.rel.domain.Relationship;
import com.gestion.rel.service.RelationshipService;

@RunWith(MockitoJUnitRunner.class)
public class RelationshipControllerTest {

	@InjectMocks
	private RelationshipController relationshipController;

	@Mock
	private RelationshipService relationshipService;

	@Test
	public void getAll() {
		Integer charId = 1;
		Collection<Relationship> expected = new ArrayList<Relationship>();
		expected.add(new Relationship());
		Mockito.when(relationshipService.getAll(charId)).thenReturn(expected);
		Collection<Relationship> actual = relationshipController.list(charId);
		assertEquals(expected, actual);
	}

	@Test
	public void getByOtherCharacter() {
		Relationship expected = new Relationship();
		Integer charId = 1;
		Integer otherId = 2;
		Mockito.when(relationshipService.getByOtherCharacter(charId, otherId)).thenReturn(expected);
		Relationship actual = relationshipController.getByOtherCharacter(charId, otherId);
		assertEquals(expected, actual);
	}

	@Test
	public void get() {
		Collection<Relation> expected = new ArrayList<Relation>();
		Integer charId = 1;
		Integer otherId = 2;
		String type = "Pepe";
		Long date = 1L;
		expected.add(new Relation(date, 1D));
		Mockito.when(relationshipService.get(charId, otherId, type)).thenReturn(expected);
		Collection<Relation> actual = relationshipController.get(charId, otherId, type);
		assertEquals(expected, actual);
	}

	@Test
	public void addValue() {
		Integer charId = 1;
		Integer otherId = 2;
		String type = "type";
		Double newValue = 2D;
		relationshipController.addValue(charId, otherId, type, newValue);
		Mockito.verify(relationshipService).addValue(charId, otherId, type, newValue);
	}

	@Test
	public void remove() {
		Integer charId = 1;
		Integer otherId = 2;
		String type = "type";
		Long date = 3L;
		relationshipController.delete(charId, otherId, type, date);
		Mockito.verify(relationshipService).remove(charId, otherId, type, date);
	}

}
