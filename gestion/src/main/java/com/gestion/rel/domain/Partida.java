package com.gestion.rel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestion.rel.binding.serializer.PartidaSerializer;

@JsonSerialize(using=PartidaSerializer.class)
public class Partida {
	
	private Integer id;

	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}

	private String name;
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
}
