package com.gestion.rel.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;

public class Relationship {

	private Integer characterId;

	private Collection<Relation> working;

	private Collection<Relation> confidential;

	private Collection<Relation> loyalty;

	private Collection<Relation> trust;

	private Collection<Relation> respect;

	private Collection<Relation> funny;

	private Collection<Relation> affection;

	private RelationComparator comparator = new RelationComparator();

	public Relationship() {
		working = new ArrayList<Relation>();
		confidential = new ArrayList<Relation>();
		loyalty = new ArrayList<Relation>();
		trust = new ArrayList<Relation>();
		respect = new ArrayList<Relation>();
		funny = new ArrayList<Relation>();
		affection = new ArrayList<Relation>();
	}

	public Relationship(Integer characterId) {
		this.characterId = characterId;
		working = new ArrayList<Relation>();
		confidential = new ArrayList<Relation>();
		loyalty = new ArrayList<Relation>();
		trust = new ArrayList<Relation>();
		respect = new ArrayList<Relation>();
		funny = new ArrayList<Relation>();
		affection = new ArrayList<Relation>();
	}

	public final Integer getCharacterId() {
		return characterId;
	}

	public final Collection<Relation> getWorking() {
		return working;
	}

	public final Collection<Relation> getConfidential() {
		return confidential;
	}

	public final Collection<Relation> getLoyalty() {
		return loyalty;
	}

	public final Collection<Relation> getTrust() {
		return trust;
	}

	public final Collection<Relation> getRespect() {
		return respect;
	}

	public final Collection<Relation> getFunny() {
		return funny;
	}

	public final Collection<Relation> getAffection() {
		return affection;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public void setWorking(Collection<Relation> working) {
		this.working = working;
	}

	public void setConfidential(Collection<Relation> confidential) {
		this.confidential = confidential;
	}

	public void setLoyalty(Collection<Relation> loyalty) {
		this.loyalty = loyalty;
	}

	public void setTrust(Collection<Relation> trust) {
		this.trust = trust;
	}

	public void setRespect(Collection<Relation> respect) {
		this.respect = respect;
	}

	public void setFunny(Collection<Relation> funny) {
		this.funny = funny;
	}

	public void setAffection(Collection<Relation> affection) {
		this.affection = affection;
	}

	public final Optional<Relation> getMaxWorking() {
		return working.stream().max(comparator);
	}

	public final Optional<Relation> getMaxConfidential() {
		return confidential.stream().max(comparator);
	}

	public final Optional<Relation> getMaxLoyalty() {
		return loyalty.stream().max(comparator);
	}

	public final Optional<Relation> getMaxTrust() {
		return trust.stream().max(comparator);
	}

	public final Optional<Relation> getMaxRespect() {
		return respect.stream().max(comparator);
	}

	public final Optional<Relation> getMaxFunny() {
		return funny.stream().max(comparator);
	}

	public final Optional<Relation> getMaxAffection() {
		return affection.stream().max(comparator);
	}

	public Double getAverage() {
		Collection<Double> values = new ArrayList<Double>();
		Optional<Relation> value = getMaxWorking();
		if (value.isPresent()) values.add(value.get().getValue());
		value = getMaxConfidential();
		if (value.isPresent()) values.add(value.get().getValue());
		value = getMaxLoyalty();
		if (value.isPresent()) values.add(value.get().getValue());
		value = getMaxTrust();
		if (value.isPresent()) values.add(value.get().getValue());
		value = getMaxRespect();
		if (value.isPresent()) values.add(value.get().getValue());
		value = getMaxFunny();
		if (value.isPresent()) values.add(value.get().getValue());
		value = getMaxAffection();
		if (value.isPresent()) values.add(value.get().getValue());
		OptionalDouble response = values.stream().mapToDouble((a) -> a).average();
		return response.orElse(0D);
	}
}
