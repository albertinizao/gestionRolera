package com.gestion.rel.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.rel.domain.Partida;

@RestController
public class PartidaController {

	@RequestMapping(value = "/partidas", method = RequestMethod.GET)
	public Collection<Partida> list() {
		Partida partida = new Partida();
		partida.setId(1);
		partida.setName("pepe");
		Collection<Partida> partidas = new ArrayList<Partida>();
		partidas.add(partida);
		return partidas;
	}
}
