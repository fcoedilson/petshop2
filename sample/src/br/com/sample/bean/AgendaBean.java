package br.com.sample.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Agenda;
import br.com.sample.service.AgendaService;

@Scope("session")
@Component("agendaBean")
public class AgendaBean extends EntityBean<Long, Agenda> {

	@Autowired
	private AgendaService service;


	public static final String list = "/pages/cadastros/agenda/agendaList.xhtml";
	public static final String single = "/pages/cadastros/agenda/agenda.xhtml";

	protected Long retrieveEntityId(Agenda entity) {
		return entity.getId();
	}

	protected AgendaService retrieveEntityService() {
		return this.service;
	}

	protected Agenda createNewEntity() {
		Agenda cargo = new Agenda();
		return cargo;
	}
	
	@Override
	public String search() {
		super.search();
		return list;
	}

	public String save(){
		super.save();
		return list;
	}

	public String update(){
		super.update();
		return list;
	}

	public String prepareSave(){
		super.prepareSave();
		return single;
	}

	public String prepareUpdate(){
		super.prepareUpdate();
		return single;
	}

}