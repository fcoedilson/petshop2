package br.com.petshop.architecture.actions;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.petshop.architecture.entity.IEntity;
import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.architecture.util.JSFUtil;
import br.com.petshop.base.exception.PetShopSystemException;

@SuppressWarnings("unused")
public abstract class AbstractActionSave<T extends IEntity> implements ActionSave<T>{

	protected BaseService<T> service;
	private IEntity entity;
	
	public AbstractActionSave(BaseService<T> service) {
		super();
		this.service = service;
	}

	public T executeSave(T entity) {
		this.entity = entity;
		return service.save( entity);
	}

	public void afterSave(T entity, Map<String, Object> beanProperties) {
		if(FacesContextIsNull()){
			return;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro " + getSavedOrChanged(entity) + " com sucesso.", null));
	}

	public void notSaved(Throwable exception) {
		exception.printStackTrace();
		if(FacesContextIsNull()){
			return;
		}
		if(exception instanceof PetShopSystemException){
			JSFUtil.addGlobalMessage(exception.getMessage(), FacesMessage.SEVERITY_WARN);
		} else {
			JSFUtil.addGlobalMessage("error_excessao", FacesMessage.SEVERITY_WARN);
		}
	}
	
	private String getSavedOrChanged(IEntity entity){
		return entity.getId() == 0 ? "salvo" : "alterado";
	}
	
	private boolean FacesContextIsNull(){
		return FacesContext.getCurrentInstance() == null;
	}

}
