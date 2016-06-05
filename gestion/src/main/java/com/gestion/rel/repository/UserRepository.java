package com.gestion.rel.repository;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gestion.rel.domain.Game;
import com.gestion.rel.domain.User;

@Repository
public class UserRepository {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private CharacterRepository characterRepository;

	public User getUserById(String id) {
		Collection<String> ids = new ArrayList<>();
		ids.add(id);
		User user = new User();
		user.setId(id);
		user.setCharacters(characterRepository.getByUsers(ids));
		user.setGamesPlayer(gameRepository.getAllByPlayers(ids));
		user.setGamesMaster(gameRepository.getAllByMasters(ids));
		return user;
	}

}
