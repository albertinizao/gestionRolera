package com.gestion.rel.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartidaTest {
	
	@Test
	public void hasSimpleConstructor(){
		Partida partida = new Partida();
		assertNotNull(partida);
	}

	@Test
	public void hasIdAttribute(){
		Partida partida = new Partida();
		Integer idExpected = 1;
		partida.setId(idExpected);
		assertEquals(idExpected,partida.getId());
	}

	@Test
	public void hasNameAttribute(){
		Partida partida = new Partida();
		String name = "name";
		partida.setName(name);
		assertEquals(name,partida.getName());
	}
}
