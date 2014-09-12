package br.com.petshop.seguranca.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import br.com.petshop.architecture.actions.ActionRemove;
import br.com.petshop.architecture.actions.ActionSave;
import br.com.petshop.architecture.actions.ActionSearch;
import br.com.petshop.architecture.actions.DefaultActionRemove;
import br.com.petshop.architecture.actions.DefaultActionSave;
import br.com.petshop.architecture.actions.DefaultActionSearch;
import br.com.petshop.architecture.modules.AbstractFacadeBean;
import br.com.petshop.base.entity.GrupoPermissao;
import br.com.petshop.base.entity.Permissao;
import br.com.petshop.seguranca.service.GrupoPermissaoService;

@ManagedBean
@ViewScoped
public class GrupoPermissaoBean extends AbstractFacadeBean<GrupoPermissao> {
	
	private static final long serialVersionUID = -2664858927542022019L;
	
	@ManagedProperty(value="#{grupoPermissaoService}")
	private GrupoPermissaoService grupoPermissaoService;
	
	private Permissao permissao;
	
	private long idPermissao;
	
	@Override
	protected String getPageTitle() {
		return "titulo_grupoPermissao";
	}
	
	@Override
	protected Class<GrupoPermissao> getDefaultEntityClass() {
		return GrupoPermissao.class;
	}
	
	@Override
	public void beforePrepareInsert(){
		permissao = new Permissao();
	}
	
	public void removerPermissao(){
		Permissao permissaoDelecao = (Permissao) getBeanProperties().get("permissaoDelecao");
		getDefaultEntity().getPermissoes().remove(permissaoDelecao);
	}
	
	public void adicionarPermissao(){
		if(!validaPermissao()){
			return;
		}
		if(permissao.getId() != null){
			permissao = new Permissao();
			return;
		}
		permissao.setId(--idPermissao);
		permissao.setGrupoPermissao(getDefaultEntity());
		getDefaultEntity().getPermissoes().add(permissao.duplicate());
		permissao = new Permissao();
	}
	
	private boolean validaPermissao() {
		return StringUtils.isNotBlank(permissao.getChave()) && StringUtils.isNotBlank(permissao.getDescricao());
	}
	
	//actions
	
	@Override
	public ActionSearch<GrupoPermissao> searchAction() {
		return new DefaultActionSearch<GrupoPermissao>(grupoPermissaoService);
	}
	 
	@Override
	protected ActionSave<GrupoPermissao> saveAction() {
		return new DefaultActionSave<GrupoPermissao>(grupoPermissaoService);
	}
	
	@Override
	public ActionRemove<GrupoPermissao> removeAction() {
		return new DefaultActionRemove<GrupoPermissao>(grupoPermissaoService);
	}

	//fim actions

	public void setGrupoPermissaoService(GrupoPermissaoService grupoPermissaoService) {
		this.grupoPermissaoService = grupoPermissaoService;
	}

	public Permissao getPermissao() {
		if(permissao == null){
			permissao = new Permissao();
		}
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

}
