package com.gestion.rel.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.rel.domain.Game;
import com.gestion.rel.service.GameService;
import com.gestion.rel.utils.UrlPathConstants;
import com.github.leleuj.ss.oauth.client.authentication.OAuthAuthenticationToken;

@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/" + UrlPathConstants.GAME, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Collection<Game> list(@RequestParam(value = "character", required = false) Collection<Integer> characters) {
		return gameService.getAll(characters);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Game get(@PathVariable("id") Integer id) {
		return gameService.get(id);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer create() {
		Optional<Integer> newIdOptional = gameService.getAll(null).stream().map(Game::getId).reduce(Integer::max);
		Integer newId = newIdOptional.isPresent() ? newIdOptional.get() : 0;
		Game game = new Game();
		game.setId(++newId);
		game.setMasters(Arrays.asList(
				new String[] { ((OAuthAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
						.getUserProfile().getId() }));
		gameService.saveOrUpdate(game);
		return newId;
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@PathVariable("id") Integer id, @RequestBody Game game) {
		gameService.saveOrUpdate(game);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{id}/join", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void joinGame(@PathVariable("id") Integer id) {
		Game game = gameService.get(id);
		game.getPlayers().add(((OAuthAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
						.getUserProfile().getId());
		gameService.saveOrUpdate(game);
	}


	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void remove(@PathVariable("id") Integer id) {
		gameService.remove(id);
	}
}
