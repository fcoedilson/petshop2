package br.com.petshop.architecture.actions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.petshop.architecture.entity.IEntity;
import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.architecture.util.JSFUtil;

public class DefaultActionRemove<T extends IEntity> implements ActionRemove<T>{

	private BaseService<T> service;
	
	public DefaultActionRemove(BaseService<T> service) {
		super();
		this.service = service;
	}

	@SuppressWarnings("unchecked")
	public void executeRemove(IEntity entity) {
		service.remove((T) entity);
	}

	public void afterRemove() {
		if(facesContextIsNull()){
			return;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro removido com sucesso.", null));
	}

	public void notRemoved(Throwable exception) {
		exception.printStackTrace();
		if(facesContextIsNull()){
			return;
		}
		JSFUtil.addGlobalMessage("error_exclusao", FacesMessage.SEVERITY_WARN);
	}
	
	private static boolean facesContextIsNull(){
		return FacesContext.getCurrentInstance() == null;
	}

}
