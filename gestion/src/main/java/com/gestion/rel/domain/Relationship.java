package com.gestion.rel.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.TreeMap;

public class Relationship {

	private Integer characterId;

	private Map<Date, Double> working;

	private Map<Date, Double> confidential;

	private Map<Date, Double> loyalty;

	private Map<Date, Double> trust;

	private Map<Date, Double> respect;

	private Map<Date, Double> funny;

	private Map<Date, Double> affection;
	
	private Comparator<Entry<Date, Double>> comparator = (a, b) -> a.getKey().compareTo(b.getKey());

	public Relationship() {
		working = new TreeMap<Date, Double>();
		confidential = new TreeMap<Date, Double>();
		loyalty = new TreeMap<Date, Double>();
		trust = new TreeMap<Date, Double>();
		respect = new TreeMap<Date, Double>();
		funny = new TreeMap<Date, Double>();
		affection = new TreeMap<Date, Double>();
	}

	public final Integer getCharacterId() {
		return characterId;
	}

	public final Map<Date, Double> getWorking() {
		return working;
	}

	public final Map<Date, Double> getConfidential() {
		return confidential;
	}

	public final Map<Date, Double> getLoyalty() {
		return loyalty;
	}

	public final Map<Date, Double> getTrust() {
		return trust;
	}

	public final Map<Date, Double> getRespect() {
		return respect;
	}

	public final Map<Date, Double> getFunny() {
		return funny;
	}

	public final Map<Date, Double> getAffection() {
		return affection;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public void setWorking(Map<Date, Double> working) {
		this.working = working;
	}

	public void setConfidential(Map<Date, Double> confidential) {
		this.confidential = confidential;
	}

	public void setLoyalty(Map<Date, Double> loyalty) {
		this.loyalty = loyalty;
	}

	public void setTrust(Map<Date, Double> trust) {
		this.trust = trust;
	}

	public void setRespect(Map<Date, Double> respect) {
		this.respect = respect;
	}

	public void setFunny(Map<Date, Double> funny) {
		this.funny = funny;
	}

	public void setAffection(Map<Date, Double> affection) {
		this.affection = affection;
	}

	public final Optional<Entry<Date, Double>> getMaxWorking() {
		return working.entrySet().stream().max(comparator);
	}

	public final Optional<Entry<Date, Double>> getMaxConfidential() {
		return confidential.entrySet().stream().max(comparator);
	}

	public final Optional<Entry<Date, Double>> getMaxLoyalty() {
		return loyalty.entrySet().stream().max(comparator);
	}

	public final Optional<Entry<Date, Double>> getMaxTrust() {
		return trust.entrySet().stream().max(comparator);
	}

	public final Optional<Entry<Date, Double>> getMaxRespect() {
		return respect.entrySet().stream().max(comparator);
	}

	public final Optional<Entry<Date, Double>> getMaxFunny() {
		return funny.entrySet().stream().max(comparator);
	}

	public final Optional<Entry<Date, Double>> getMaxAffection() {
		return affection.entrySet().stream().max(comparator);
	}

	public Double getAverage() {
		Collection<Double> values = new ArrayList<Double>();
		Optional<Entry<Date, Double>> value = getMaxWorking();
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
		OptionalDouble response = values.stream().mapToDouble((a)->a).average();
		return response.orElse(0D);
	}
}
