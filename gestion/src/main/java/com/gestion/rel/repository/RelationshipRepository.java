package com.gestion.rel.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.bson.BSON;
import org.bson.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.gestion.rel.domain.Character;
import com.gestion.rel.domain.Relationship;
import com.gestion.rel.utils.UrlPathConstants;

@Repository
public class RelationshipRepository {

	@Autowired
	private CharacterRepository characterRepository;

	@Resource
	private MongoTemplate template;
	
	@Autowired
	private WebDataBinder binder;
	
	@InitBinder
	void registerConverters(WebDataBinder binder) {
		if (binder.getConversionService() instanceof GenericConversionService) {
			GenericConversionService conversionService = (GenericConversionService) binder.getConversionService();
			conversionService.addConverter(Converter<S, T>);
		}
	}
	
	public RelationshipRepository(){
		registerConverters(binder);
		BSON.addEncodingHook(Date.class, new Transformer() {
			
			@Override
			public Object transform(Object objectToTransform) {
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyy");
				try {
	                return sdf.parse((String)objectToTransform);
                } catch (ParseException e) {
	                return null;
                }
			}
		});
		BSON.addDecodingHook(Date.class, new Transformer() {
			
			@Override
			public Object transform(Object objectToTransform) {
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyy");
				return sdf.format(objectToTransform);
			}
		});
	}

	public Collection<Relationship> getAll(Integer charId) {
		Character character = characterRepository.getById(charId);
		Assert.notNull(character);
		return character.getRelationShips();
	}

	public Relationship getByOtherCharacter(Integer charId, Integer otherId) {
		return getByOtherCharacter(getAll(charId), otherId);
	}

	protected Relationship getByOtherCharacter(Collection<Relationship> relations, Integer otherId) {
		Optional<Relationship> optional = relations.stream().filter(r -> r.getCharacterId().equals(otherId)).findFirst();
		Relationship relation = null;
		if (optional.isPresent()){
			relation=optional.get();
		}else{
			relation=new Relationship(otherId);
			relations.add(relation);
		}
		return relation;
	}

	protected Map<Date, Double> getRelationMapByType(Relationship relation, String type) {
		Map<Date, Double> relationMap = null;
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

	public Map<Date, Double> get(Integer charId, Integer otherId, String type) {
		Character character = characterRepository.getById(charId);
		Relationship relation = getByOtherCharacter(character.getRelationShips(), otherId);
		return getRelationMapByType(relation, type);
	}

	public void addValue(Integer charId, Integer otherId, String type, Double newValue) {
		Character character = characterRepository.getById(charId);
		Relationship relation = getByOtherCharacter(character.getRelationShips(), otherId);
		Map<Date, Double> relationMap = getRelationMapByType(relation, type);
		relationMap.put(new Date(), newValue);
		characterRepository.saveOrUpdate(character);
	}

	public void remove(Integer charId, Integer otherId, String type, Date date) {
		Character character = characterRepository.getById(charId);
		Relationship relation = getByOtherCharacter(character.getRelationShips(), otherId);
		Map<Date, Double> relationMap = getRelationMapByType(relation, type);
		relationMap.remove(date);
		characterRepository.saveOrUpdate(character);
	}

}
