package com.gestion.rel.repository;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gestion.rel.domain.Partida;

@Repository
public class PartidaRepository {

	private static final long serialVersionUID = -4599832253548800012L;

	private Query getPJQuery(Collection<Integer> pjIds) {
		Criteria criteria = Criteria.where("pj").in(pjIds);
		return Query.query(criteria);
	}

	@Resource
	private MongoTemplate template;

	public long getCount() {
		return template.count(null, Partida.class);
	}

	public long getCount(Collection<Integer> pjIds) {
		return template.count(getPJQuery(pjIds), Partida.class);
	}

	public Collection<Partida> getAll() {
		return template.findAll(Partida.class);
	}

	public Collection<Partida> getAllByPJ(Collection<Integer> pjIds) {
		List<Partida> notes = template.find(getPJQuery(pjIds), Partida.class);
		return notes;
	}

	public Collection<Partida> getAllByName(String name) {
		Criteria criteria = Criteria.where("name").is(name);
		Query query = Query.query(criteria);

		List<Partida> notes = template.find(query, Partida.class);
		return notes;
	}

	public Partida getById(Integer id) {
		return template.findById(id, Partida.class);
	}

	public void saveOrUpdate(Partida partida) {
		template.save(partida);
	}

}
