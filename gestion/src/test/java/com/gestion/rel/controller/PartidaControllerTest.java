package com.gestion.rel.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.gestion.rel.domain.Partida;

public class PartidaControllerTest {

	@InjectMocks
	private PartidaController partidaController;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getPartidaList() {
		Collection<Partida> partida = partidaController.list();
		assertNotNull(partida);
		assertFalse(partida.isEmpty());
	}

}
