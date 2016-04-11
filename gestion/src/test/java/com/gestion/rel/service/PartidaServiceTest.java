package com.gestion.rel.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gestion.rel.domain.Partida;
import com.gestion.rel.repository.PartidaRepository;

public class PartidaServiceTest {

	@Mock
	private PartidaRepository partidaRepository;

	@InjectMocks
	private PartidaService partidaService;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	public void getAll() {
		Collection<Partida> partidasExpected = new ArrayList<Partida>();
		partidasExpected.add(new Partida());
		when(partidaRepository.getAll()).thenReturn(partidasExpected);
		Collection<Partida> partidasActual = partidaService.getAll();
		assertEquals(partidasExpected, partidasActual);
	}

	public void saveOrUpdate(Partida partida) {
		partidaService.saveOrUpdate(partida);
		verify(partidaRepository).saveOrUpdate(partida);
	}
}
