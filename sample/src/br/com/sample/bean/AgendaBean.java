package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Agenda;
import br.com.sample.entity.Cliente;
import br.com.sample.entity.Endereco;
import br.com.sample.service.AgendaService;
import br.com.sample.service.ClienteService;

@Scope("session")
@Component("agendaBean")
public class AgendaBean extends EntityBean<Long, Agenda> {

	@Autowired
	private AgendaService service;

	@Autowired
	private ClienteService clienteService;

	private List<Cliente> clientes = new ArrayList<Cliente>();


	public static final String list = "/pages/atendimentos/agenda/agendaList.xhtml";
	public static final String single = "/pages/atendimentos/agenda/agenda.xhtml";

	@PostConstruct
	public void init(){
		clientes = clienteService.retrieveAll();
	}

	protected Long retrieveEntityId(Agenda entity) {
		return entity.getId();
	}

	protected AgendaService retrieveEntityService() {
		return this.service;
	}

	protected Agenda createNewEntity() {
		Agenda agenda = new Agenda();
		Cliente cli = new Cliente();
		agenda.setCliente(cli);
		Endereco e = new Endereco();
		e.setPessoa(cli);
		cli.setEndereco(e);
		return agenda;
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


}