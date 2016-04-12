package com.gestion.rel.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.rel.domain.Character;
import com.gestion.rel.service.CharacterService;
import com.gestion.rel.utils.UrlPathConstants;

@RestController
public class CharacterController {

	@Autowired
	private CharacterService characterService;

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Collection<Character> list(@PathVariable("gameId") Integer gameId) {
		return characterService.getAll(gameId);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER + "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Character get(@PathVariable("gameId") Integer gameId, @PathVariable("id") Integer id) {
		return characterService.get(id);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer create(@PathVariable("gameId") Integer gameId) {
		Optional<Integer> newIdOptional = characterService.getAll(gameId).stream().map(Character::getId)
		        .reduce(Integer::max);
		Integer newId = newIdOptional.isPresent()?newIdOptional.get():0;
		Character character = new Character();
		character.setId(++newId);
		character.setGame(gameId);
		characterService.saveOrUpdate(character);
		return newId;
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER + "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@PathVariable("gameId") Integer gameId, @PathVariable("id") Integer id,
	        @RequestBody Character character) {
		characterService.saveOrUpdate(character);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER + "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void remove(@PathVariable("gameId") Integer gameId, @PathVariable("id") Integer id) {
		characterService.remove(gameId, id);
	}
}
