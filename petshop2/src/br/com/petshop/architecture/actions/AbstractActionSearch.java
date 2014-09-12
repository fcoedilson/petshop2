package br.com.petshop.architecture.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.petshop.architecture.entity.IEntity;
import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.architecture.util.JSFUtil;
import br.com.petshop.base.exception.PetShopSystemException;

public abstract class AbstractActionSearch<T extends IEntity> implements ActionSearch<T>{

	protected BaseService<T> service;
	private List<T> resultList;
	private Integer totalNumRows;
	private Map<String, Object> resultProperties;
	
	public AbstractActionSearch(BaseService<T> service) {
		super();
		this.service = service;
	}

	public void executeSearch(Map<String, Object> filters, Integer first, Integer rows) {
		resultList = service.findByFilter(filters, first, rows);
	}
	
	public void count(Map<String, Object> filters) {
		totalNumRows = service.countByFilter(filters);
	}

	public void notSearched(Throwable exception) {
		exception.printStackTrace();
		if(facesContextIsNull()){
			return;
		}
		if(exception instanceof PetShopSystemException){
			JSFUtil.addGlobalMessage(exception.getMessage(), FacesMessage.SEVERITY_WARN);
//		}else if(exception instanceof ExtratoSystemException){
//			JSFUtil.addGlobalMessage(exception.getMessage(), FacesMessage.SEVERITY_WARN);
		}else{
			JSFUtil.addGlobalMessage("error_excessao", FacesMessage.SEVERITY_WARN);
		}
	}
	
	private boolean facesContextIsNull(){
		return FacesContext.getCurrentInstance() == null;
	}

	public List<T> getResultList() {
		return resultList;
	}
	
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public Integer getTotalNumRows() {
		return totalNumRows;
	}
	
	public Map<String, Object> getResultProperties() {
		if(resultProperties == null){
			resultProperties =  new HashMap<String, Object>();
		}
		return resultProperties;
	}
}