package com.gestion.rel.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.rel.domain.Relation;
import com.gestion.rel.domain.Relationship;
import com.gestion.rel.repository.RelationshipRepository;

@Service
public class RelationshipService {

	@Autowired
	private RelationshipRepository relationshipRepository;

	public Collection<Relationship> getAll(Integer charId) {
		return relationshipRepository.getAll(charId);
	}

	public Relationship getByOtherCharacter(Integer charId, Integer otherId) {
		return relationshipRepository.getByOtherCharacter(charId, otherId);
	}

	public Collection<Relation> get(Integer charId, Integer otherId, String type) {
		return relationshipRepository.get(charId, otherId, type);
	}

	public void addValue(Integer charId, Integer otherId, String type, Double newValue) {
		relationshipRepository.addValue(charId, otherId, type, newValue);
	}

	public void remove(Integer charId, Integer otherId, String type, Long date) {
		relationshipRepository.remove(charId, otherId, type, date);
	}

}
