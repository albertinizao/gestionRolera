package com.gestion.rel.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestion.rel.binding.deserializer.GameDeserializer;
import com.gestion.rel.binding.serializer.GameSerializer;

@JsonSerialize(using = GameSerializer.class)
@JsonDeserialize(using = GameDeserializer.class)
public class Game {

	@Id
	private Integer id;

	private String name;

	private Collection<String> masters;

	private Collection<Integer> characters;

	private TreeSet<String> players;

	public Game() {
		masters = new ArrayList<>();
		characters = new ArrayList<>();
		players = new TreeSet<>();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Collection<Integer> getCharacters() {
		return characters;
	}

	public void setCharacters(Collection<Integer> characters) {
		this.characters = characters;
	}

	// @Override
	// public String toString() {
	// return ToStringBuilder.reflectionToString(this);
	// }
	//
	// @Override
	// public int hashCode() {
	// return HashCodeBuilder.reflectionHashCode(this);
	// }

	public Collection<String> getMasters() {
		return masters;
	}

	public void setMasters(Collection<String> masters) {
		this.masters = masters;
	}

	public Collection<String> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<String> players) {
		this.players = new TreeSet<String>(players);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
