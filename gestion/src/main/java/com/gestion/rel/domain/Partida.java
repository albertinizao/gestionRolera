package com.gestion.rel.domain;

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
		this.name=name.toUpperCase();
	}
	
	public String getName(){
		return this.name;
	}
}
