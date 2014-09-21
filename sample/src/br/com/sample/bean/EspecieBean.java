package br.com.sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Especie;
import br.com.sample.service.EspecieService;

@Scope("session")
@Component("especieBean")
public class EspecieBean extends EntityBean<Long, Especie> {

	@Autowired
	private EspecieService service;


	protected Long retrieveEntityId(Especie entity) {
		return entity.getId();
	}

	protected EspecieService retrieveEntityService() {
		return this.service;
	}

	protected Especie createNewEntity() {
		Especie especie = new Especie();
		return especie;
	}

}