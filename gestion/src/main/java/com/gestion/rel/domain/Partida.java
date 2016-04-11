package com.gestion.rel.domain;

import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestion.rel.binding.serializer.PartidaSerializer;

@JsonSerialize(using = PartidaSerializer.class)
public class Partida {

	@Id
	private Integer id;

	private String name;
	
	private Collection<Integer> characters;

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
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
}
