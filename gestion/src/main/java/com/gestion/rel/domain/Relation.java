package com.gestion.rel.domain;

import java.util.Date;


public class Relation {

	private Long key;

	private Double value;
	
	public Relation(){}
	public Relation(Double value){
		Date date = new Date();
		this.key=date.getTime();
		this.value=value;
	}
	public Relation(Long key, Double value){
		this.key=key;
		this.value=value;
	}

	
    public Long getKey() {
    	return key;
    }

	
    public void setKey(Long key) {
    	this.key = key;
    }

	
    public Double getValue() {
    	return value;
    }

	
    public void setValue(Double value) {
    	this.value = value;
    }
	
	

}
