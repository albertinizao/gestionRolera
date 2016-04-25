package com.gestion.rel.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.rel.domain.Relationship;
import com.gestion.rel.service.RelationshipService;
import com.gestion.rel.utils.UrlPathConstants;

@RestController
public class RelationshipController {

	@Autowired
	private RelationshipService relationshipService;

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER + "/{charId}/"
	        + UrlPathConstants.RELATIONSHIP, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Collection<Relationship> list(@PathVariable("charId") Integer charId) {
		return relationshipService.getAll(charId);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER + "/{charId}/"
	        + UrlPathConstants.RELATIONSHIP + "/{otherCharId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Relationship getByOtherCharacter(@PathVariable("charId") Integer charId, @PathVariable("otherCharId") Integer otherCharId) {
		return relationshipService.getByOtherCharacter(charId, otherCharId);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER + "/{charId}/"
	        + UrlPathConstants.RELATIONSHIP + "/{otherCharId}/{type}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Map<Date, Double> get(@PathVariable("charId") Integer charId, @PathVariable("otherCharId") Integer otherCharId, @PathVariable("type") String type) {
		return relationshipService.get(charId, otherCharId, type);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER + "/{charId}/"
	        + UrlPathConstants.RELATIONSHIP + "/{otherCharId}/{type}/{newValue}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addValue(@PathVariable("charId") Integer charId, @PathVariable("otherCharId") Integer otherCharId, @PathVariable("type") String type, @PathVariable("newValue") Double newValue) {
		relationshipService.addValue(charId, otherCharId, type, newValue);
	}

	@RequestMapping(value = "/" + UrlPathConstants.GAME + "/{gameId}/" + UrlPathConstants.CHARACTER + "/{charId}/"
	        + UrlPathConstants.RELATIONSHIP + "/{otherCharId}/{type}/{date}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("charId") Integer charId, @PathVariable("otherCharId") Integer otherCharId, @PathVariable("type") String type, @PathVariable("date") Date date) {
		relationshipService.remove(charId, otherCharId, type, date);
	}
}
