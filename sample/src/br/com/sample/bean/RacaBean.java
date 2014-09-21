package br.com.sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Raca;
import br.com.sample.service.EnderecoService;
import br.com.sample.service.PessoaService;
import br.com.sample.service.RacaService;

@Scope("session")
@Component("racaBean")
public class RacaBean extends EntityBean<Long, Raca> {

	@Autowired
	private RacaService service;


	protected Long retrieveEntityId(Raca entity) {
		return entity.getId();
	}

	protected RacaService retrieveEntityService() {
		return this.service;
	}

	protected Raca createNewEntity() {
		Raca raca = new Raca();
		return raca;
	}

}