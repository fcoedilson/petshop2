package br.com.petshop.cadastro.bean;

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
import br.com.petshop.base.entity.Feriado;
import br.com.petshop.cadastro.service.FeriadoService;

@ManagedBean
@ViewScoped
public class FeriadoBean extends AbstractFacadeBean<Feriado>{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{feriadoService}")
	private FeriadoService feriadoService;

	@Override
	protected String getPageTitle() {
		return "titulo_feriado";
	}

	@Override
	protected Class<Feriado> getDefaultEntityClass() {
		return Feriado.class;
	}
	
	@Override
	protected ActionSearch<Feriado> searchAction() {
		return new DefaultActionSearch<Feriado>(feriadoService);
	}

	@Override
	protected ActionSave<Feriado> saveAction() {
		return new DefaultActionSave<Feriado>(feriadoService);
	}
	
	@Override
	public ActionRemove<Feriado> removeAction() {
		return new DefaultActionRemove<Feriado>(feriadoService);
	}

	public void setFeriadoService(FeriadoService feriadoService) {
		this.feriadoService = feriadoService;
	}
}