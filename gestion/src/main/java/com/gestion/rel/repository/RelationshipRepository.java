package com.gestion.rel.repository;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.bson.BSON;
import org.bson.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Relation;
import com.gestion.rel.domain.Relationship;
import com.gestion.rel.utils.UrlPathConstants;

@Repository
public class RelationshipRepository {

	@Autowired
	private CharacterRepository characterRepository;

	@Resource
	private MongoTemplate template;

	public Collection<Relationship> getAll(Integer charId) {
		Character character = characterRepository.getById(charId);
		Assert.notNull(character);
		return character.getRelationShips();
	}

	public Relationship getByOtherCharacter(Integer charId, Integer otherId) {
		return getByOtherCharacter(getAll(charId), otherId);
	}

	protected Relationship getByOtherCharacter(Collection<Relationship> relations, Integer otherId) {
		Optional<Relationship> optional = relations.stream().filter(r -> r.getCharacterId().equals(otherId))
		        .findFirst();
		Relationship relation = null;
		if (optional.isPresent()) {
			relation = optional.get();
		} else {
			relation = new Relationship(otherId);
			relations.add(relation);
		}
		return relation;
	}

	protected Collection<Relation> getRelationMapByType(Relationship relation, String type) {
		Collection<Relation> relationMap = null;
		if (UrlPathConstants.AFFECTION.equalsIgnoreCase(type)) {
			relationMap = relation.getAffection();
		} else if (UrlPathConstants.CONFIDENTIAL.equalsIgnoreCase(type)) {
			relationMap = relation.getConfidential();
		} else if (UrlPathConstants.FUNNY.equalsIgnoreCase(type)) {
			relationMap = relation.getFunny();
		} else if (UrlPathConstants.LOYALTY.equalsIgnoreCase(type)) {
			relationMap = relation.getLoyalty();
		} else if (UrlPathConstants.RESPECT.equalsIgnoreCase(type)) {
			relationMap = relation.getRespect();
		} else if (UrlPathConstants.TRUST.equalsIgnoreCase(type)) {
			relationMap = relation.getTrust();
		} else if (UrlPathConstants.WORKING.equalsIgnoreCase(type)) {
			relationMap = relation.getWorking();
		}
		Assert.notNull(relationMap);
		return relationMap;
	}

	public Collection<Relation> get(Integer charId, Integer otherId, String type) {
		Character character = characterRepository.getById(charId);
		Relationship relation = getByOtherCharacter(character.getRelationShips(), otherId);
		return getRelationMapByType(relation, type);
	}

	public void addValue(Integer charId, Integer otherId, String type, Double newValue) {
		Character character = characterRepository.getById(charId);
		Relationship relation = getByOtherCharacter(character.getRelationShips(), otherId);
		Collection<Relation> relationMap = getRelationMapByType(relation, type);
		Date date = new Date();
		relationMap.add(new Relation(date.getTime(), newValue));
		characterRepository.saveOrUpdate(character);
	}

	public void remove(Integer charId, Integer otherId, String type, Long date) {
		Character character = characterRepository.getById(charId);
		Relationship relation = getByOtherCharacter(character.getRelationShips(), otherId);
		Collection<Relation> relationMap = getRelationMapByType(relation, type);
		Relation toRemove = null;
		for (Relation relationElement:relationMap){
			if (relationElement.getKey().equals(date)){
				toRemove=relationElement;
			}
		}
		relationMap.remove(toRemove);
		characterRepository.saveOrUpdate(character);
	}

}
