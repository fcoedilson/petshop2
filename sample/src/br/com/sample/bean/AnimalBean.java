package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Animal;
import br.com.sample.entity.Raca;
import br.com.sample.service.AnimalService;
import br.com.sample.service.RacaService;

@Scope("session")
@Component("animalBean")
public class AnimalBean extends EntityBean<Long, Animal> {

	@Autowired
	private AnimalService service;
	
	@Autowired
	private RacaService racaService;
	
	private List<Raca> racas = new ArrayList<Raca>(racaService.retrieveAll());
	
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

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}

}