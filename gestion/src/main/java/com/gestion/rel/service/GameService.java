package com.gestion.rel.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.rel.domain.Game;
import com.gestion.rel.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	public Collection<Game> getAll(Collection<Integer> characters) {
		if (characters==null){
			return gameRepository.getAll();
		}else{
			return gameRepository.getAllByPJ(characters);
		}
	}

	public Game get(Integer id) {
		return gameRepository.getById(id);
	}

	public void saveOrUpdate(Game game) {
		gameRepository.saveOrUpdate(game);
	}
	
	public void remove(Integer id){
		gameRepository.remove(id);
	}

}
