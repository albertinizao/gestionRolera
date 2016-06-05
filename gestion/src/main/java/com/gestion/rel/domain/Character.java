package com.gestion.rel.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestion.rel.binding.serializer.CharacterSerializer;

@JsonSerialize(using = CharacterSerializer.class)
public class Character {

	@Id
	private int id;

	private String name;

	private Integer game;
	
	private Collection<String> users;

	private Collection<Relationship> relationShips;
	
	public Character(){
		relationShips=new ArrayList<>();
		users=new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGame() {
		return game;
	}

	public void setGame(Integer game) {
		this.game = game;
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

	public Collection<Relationship> getRelationShips() {
		return relationShips;
	}

	public void setRelationShips(Collection<Relationship> relationShips) {
		this.relationShips = relationShips;
	}

	public Collection<String> getUsers() {
		return users;
	}

	public void setUsers(Collection<String> users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
