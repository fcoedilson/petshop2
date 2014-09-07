/**
 * 
 */
package br.com.sample.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Perfil;
import br.com.sample.entity.Permissao;
import br.com.sample.service.PermissaoService;
import br.com.sample.service.PerfilService;

@Scope("session")
@Component("perfilBean")
public class PerfilBean extends EntityBean<Long, Perfil>{


	@Autowired
	private PerfilService service;

	@Autowired
	private PermissaoService permissaoService;


	private Permissao permissao = new Permissao();
	private List<Permissao> permissoes;

	@Override
	protected Perfil createNewEntity() {
		this.permissoes = new ArrayList<Permissao>();
		this.permissoes.addAll(permissaoService.retrieveAll());
		this.permissao = new Permissao();
		return new Perfil();
	}

	@Override
	protected Long retrieveEntityId(Perfil entity) {
		return entity.getId();
	}

	@Override
	protected PerfilService retrieveEntityService() {
		return this.service;
	}

	public String update(){
		return super.update();
	}

	public boolean isPermissaoStatus(){
		return this.permissoes.size() > 0;
	}

	@Transactional(readOnly=true)
	public List<Perfil> getRoles(){
		return  new ArrayList<Perfil>(service.findAll());
	}

	public String add(){
		this.entity.getPermissoes().add(this.permissao);
		service.update(entity);
		this.permissoes.remove(this.permissao);
		return SUCCESS;
	}


	public String remove(){
		this.entity.getPermissoes().remove(this.permissao);
		this.permissoes.add(this.permissao);
		return SUCCESS;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

}
