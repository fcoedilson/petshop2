package br.com.petshop.architecture.actions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.architecture.service.Disabilitable;
import br.com.petshop.architecture.util.JSFUtil;

public class DefaultActionDisable<T extends Disabilitable> implements ActionDisable<T>{

	private BaseService<T> service;
	private Disabilitable disabilitable;
	
	public DefaultActionDisable(BaseService<T> service) {
		super();
		this.service = service;
	}

	@SuppressWarnings("unchecked")
	public void executeDisable(Disabilitable disabilitable) {
		this.disabilitable = disabilitable;
		disabilitable.setAtivo(!disabilitable.getAtivo());
		service.save((T) disabilitable);
	}

	public void afterDisable() {
		if(facesContextIsNull()){
			return;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro "+getHabilitadoOuDesabilitado(disabilitable)+" com sucesso.", null));
	}

	public void notDisabled(Throwable exception) {
		exception.printStackTrace();
		if(facesContextIsNull()){
			return;
		}
		JSFUtil.addGlobalMessage("error_excessao", FacesMessage.SEVERITY_WARN);
	}
	
	private String getHabilitadoOuDesabilitado(Disabilitable disabilitable){
		return disabilitable.getAtivo()? "desabilitado" : "habilitado";
	}
	
	private boolean facesContextIsNull(){
		return FacesContext.getCurrentInstance() == null;
	}

}
