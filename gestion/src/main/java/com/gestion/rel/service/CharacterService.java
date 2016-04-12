package com.gestion.rel.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.rel.domain.Character;
import com.gestion.rel.repository.GameRepository;
import com.gestion.rel.repository.CharacterRepository;


@Service
public class CharacterService {

	@Autowired
	private CharacterRepository characterRepository;

	public Collection<Character> getAll(Integer gameId) {
		return characterRepository.getAll(gameId);
    }

	public Character get(Integer id) {
		return characterRepository.getById(id);
    }

	public void saveOrUpdate(Character character) {
		characterRepository.saveOrUpdate(character);
    }

	public void remove(Integer gameId, Integer id) {
	    characterRepository.remove(gameId, id);
    }

}
