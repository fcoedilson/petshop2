package br.com.sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Animal;
import br.com.sample.service.AnimalService;

@Scope("session")
@Component("animalBean")
public class AnimalBean extends EntityBean<Long, Animal> {

	@Autowired
	private AnimalService service;

	protected Long retrieveEntityId(Animal entity) {
		return entity.getId();
	}

	protected AnimalService retrieveEntityService() {
		return this.service;
	}

	protected Animal createNewEntity() {
		Animal animal = new Animal();
		return animal;
	}

}