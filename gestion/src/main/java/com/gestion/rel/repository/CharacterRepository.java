package com.gestion.rel.repository;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gestion.rel.domain.Character;

@Repository
public class CharacterRepository {

	protected Query getPJQuery(Integer gameId) {
		Criteria criteria = Criteria.where("game").is(gameId);
		return Query.query(criteria);
	}

	protected Query getUsersQuery(Collection<String> users) {
		Criteria criteria = Criteria.where("users").in(users);
		return Query.query(criteria);
	}

	@Resource
	private MongoTemplate template;

	public Collection<Character> getAll(Integer gameId) {
		return template.find(getPJQuery(gameId), Character.class);
	}

	public Collection<Character> getByUsers(Collection<String> users) {
		return template.find(getUsersQuery(users), Character.class);
	}

	public Character getById(Integer id) {
		return template.findById(id, Character.class);
	}

	public void saveOrUpdate(Character character) {
		template.save(character);
	}

	public void remove(Integer gameId, Integer id) {
		Character character = new Character();
		character.setId(id);
		character.setGame(gameId);
		template.remove(character);
	}

}
