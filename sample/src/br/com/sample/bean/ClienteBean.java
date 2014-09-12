package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Cliente;
import br.com.sample.entity.Endereco;
import br.com.sample.entity.Permissao;
import br.com.sample.service.ClienteService;
import br.com.sample.service.EnderecoService;
import br.com.sample.service.PessoaService;

@Scope("session")
@Component("clienteBean")
public class ClienteBean extends EntityBean<Long, Cliente> {

	@Autowired
	private ClienteService service;

	private Permissao permissao;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private EnderecoService enderecoService;

	protected Long retrieveEntityId(Cliente entity) {
		return entity.getId();
	}

	protected ClienteService retrieveEntityService() {
		return this.service;
	}
	
	@PostConstruct
	public void init(){
		this.entities = service.retrieveAll();
	}

	protected Cliente createNewEntity() {
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		endereco.setPessoa(cliente);
		cliente.setEndereco(endereco);
		return cliente;
	}
	
	public Permissao getPermissao() {
		if (permissao == null) {
			permissao = new Permissao();
		}
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public List<SelectItem> getTipoDocumentos(){
		List<SelectItem> result = new ArrayList<SelectItem>();
		result.add(new SelectItem(1, "CPF"));
		result.add(new SelectItem(2, "CNPJ"));
		return result;
	}


	public List<Cliente> getClientes(){
		return this.service.retrieveAll();
	}
	
	public String prepareSave(){
		super.prepareSave();
		return "cliente";
	}
	
	public String search(){
		super.search();
		return "clienteList";
	}
	
	public String save(){
		retrieveEntityService().save(this.entity);
		return "clienteList";
	}
}