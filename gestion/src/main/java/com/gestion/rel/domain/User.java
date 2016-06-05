package com.gestion.rel.domain;

import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestion.rel.binding.serializer.UserSerializer;

@JsonSerialize(using = UserSerializer.class)
public class User {
	private String id;
	private Collection<Character> characters;
	private Collection<Game> gamesMaster;
	private Collection<Game> gamesPlayer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collection<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(Collection<Character> characters) {
		this.characters = characters;
	}

	public Collection<Game> getGamesMaster() {
		return gamesMaster;
	}

	public void setGamesMaster(Collection<Game> gamesMaster) {
		this.gamesMaster = gamesMaster;
	}

	public Collection<Game> getGamesPlayer() {
		return gamesPlayer;
	}

	public void setGamesPlayer(Collection<Game> gamesPlayer) {
		this.gamesPlayer = gamesPlayer;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
