package com.gestion.rel.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.rel.domain.Partida;
import com.gestion.rel.repository.PartidaRepository;

@Service
public class PartidaService {

	@Autowired
	private PartidaRepository partidaRepository;

	public Collection<Partida> getAll() {
		return partidaRepository.getAll();
	}

	public void saveOrUpdate(Partida partida) {
		partidaRepository.saveOrUpdate(partida);
	}

}
