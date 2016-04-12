package com.gestion.rel.repository;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gestion.rel.domain.Game;

@Repository
public class GameRepository {

	private static final long serialVersionUID = -4599832253548800012L;

	private Query getPJQuery(Collection<Integer> pjIds) {
		Criteria criteria = Criteria.where("pj").in(pjIds);
		return Query.query(criteria);
	}

	@Resource
	private MongoTemplate template;

	public long getCount() {
		return template.count(null, Game.class);
	}

	public long getCount(Collection<Integer> pjIds) {
		return template.count(getPJQuery(pjIds), Game.class);
	}

	public Collection<Game> getAll() {
		return template.findAll(Game.class);
	}

	public Collection<Game> getAllByPJ(Collection<Integer> pjIds) {
		List<Game> notes = template.find(getPJQuery(pjIds), Game.class);
		return notes;
	}

	public Collection<Game> getAllByName(String name) {
		Criteria criteria = Criteria.where("name").is(name);
		Query query = Query.query(criteria);

		List<Game> notes = template.find(query, Game.class);
		return notes;
	}

	public Game getById(Integer id) {
		return template.findById(id, Game.class);
	}

	public void saveOrUpdate(Game game) {
		template.save(game);
	}

	public void remove(Integer id) {
		Game game = new Game();
		game.setId(id);
		template.remove(game);
	}

}