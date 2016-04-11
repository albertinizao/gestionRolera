package com.gestion.rel.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.rel.domain.Partida;
import com.gestion.rel.service.PartidaService;

@RestController
public class PartidaController {
	
	@Autowired
	private PartidaService partidaService;

	@RequestMapping(value = "/partidas", method = RequestMethod.GET)
	public Collection<Partida> list() {
		Partida partida = new Partida();
		partida.setId(2);
		partida.setName("juancho");
		partidaService.saveOrUpdate(partida);
		return partidaService.getAll();
	}
}
