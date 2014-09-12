package br.com.petshop.seguranca.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.petshop.architecture.actions.ActionRemove;
import br.com.petshop.architecture.actions.ActionSave;
import br.com.petshop.architecture.actions.ActionSearch;
import br.com.petshop.architecture.actions.DefaultActionRemove;
import br.com.petshop.architecture.actions.DefaultActionSave;
import br.com.petshop.architecture.actions.DefaultActionSearch;
import br.com.petshop.architecture.modules.AbstractFacadeBean;
import br.com.petshop.base.entity.GrupoPermissao;
import br.com.petshop.base.entity.Perfil;
import br.com.petshop.base.entity.Permissao;
import br.com.petshop.seguranca.service.GrupoPermissaoService;
import br.com.petshop.seguranca.service.PerfilService;

@ManagedBean
@ViewScoped
public class PerfilBean extends AbstractFacadeBean<Perfil> {
	
	private static final long serialVersionUID = 852721552877928465L;

	@ManagedProperty(value="#{perfilService}")
	private PerfilService perfilService;
	
	@ManagedProperty(value="#{grupoPermissaoService}")
	private GrupoPermissaoService grupoPermissaoService;

	@Override
	protected String getPageTitle() {
		return "titulo_perfil";
	}
	
	@Override
	protected Class<Perfil> getDefaultEntityClass() {
		return Perfil.class;
	}
	
	@Override
	public void save() {
		setPermissoesPerfil();
		super.save();
	}
	
	@SuppressWarnings("unchecked")
	private void setPermissoesPerfil(){
		List<Permissao> listPermissao = new ArrayList<Permissao>();
		List<GrupoPermissao> gruposPermissoes = (List<GrupoPermissao>) getBeanProperties().get("gruposPermissoes");
		for (GrupoPermissao grupoPermissao : gruposPermissoes) {
			for (Permissao permissao : grupoPermissao.getPermissoes()) {
				if(permissao.getChecado() != null && permissao.getChecado()){
					listPermissao.add(permissao);
				}
			}
		}
		getDefaultEntity().setPermissoes(listPermissao);
	}

	@Override
	public void afterPrepareEdit() {
		List<GrupoPermissao> gruposPermissoes = grupoPermissaoService.findAllWithPermissoes(getDefaultEntity());
		getBeanProperties().put("gruposPermissoes", gruposPermissoes);
	}
	
	@Override
	public void afterPrepareInsert() {
		List<GrupoPermissao> gruposPermissoes = grupoPermissaoService.findAllWithPermissoes(getDefaultEntity());
		getBeanProperties().put("gruposPermissoes", gruposPermissoes);
	}
	
	public ActionSearch<Perfil> searchAction() {
		return new DefaultActionSearch<Perfil>(perfilService);
	}
	 
	protected ActionSave<Perfil> saveAction() {
		return new DefaultActionSave<Perfil>(perfilService);
	}
	
	public ActionRemove<Perfil> removeAction() {
		return new DefaultActionRemove<Perfil>(perfilService);
	}
	
	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	public void setGrupoPermissaoService(GrupoPermissaoService grupoPermissaoService) {
		this.grupoPermissaoService = grupoPermissaoService;
	}
}