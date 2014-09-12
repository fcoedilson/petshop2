package br.com.petshop.architecture.modules;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.petshop.architecture.actions.ActionDisable;
import br.com.petshop.architecture.actions.ActionRemove;
import br.com.petshop.architecture.actions.ActionSave;
import br.com.petshop.architecture.actions.ActionSearch;
import br.com.petshop.architecture.entity.IEntity;
import br.com.petshop.architecture.service.Disabilitable;
import br.com.petshop.architecture.util.PagedDataModel;
import br.com.petshop.architecture.util.Paginator;

public class DefaultEntityBean implements Serializable {

	private static final long serialVersionUID = -8972886155283262435L;

	private static final int PAGE_SIZE = 20;

	private IEntity defaultEntity;

	private Map<String, Object> filter = new HashMap<String, Object>(); //vive so ate a proxima pesquisa (usado para filtrar a pesquisa principal)
	private Map<String, Object> resultProperties;					    //vive so ate a proxima pesquisa (usado para respostas com mais de um objeto)
	private List<IEntity> resultList;							        //vive so ate a proxima pesquisa (usado para retornara lista sem paginacao)
	private Paginator pagedList;						                //vive so ate a proxima pesquisa (usado para retornara lista com paginacao)
	
	private Map<String, Object> beanProperties = new HashMap<String, Object>(); //vive durante toda a vida do bean (pode ser resetado a qualquer momento)
	
	public void resetResults(){

		if(!filter.isEmpty()){
			filter = new HashMap<String, Object>();
		}
		
		if(resultProperties != null){
			resultProperties.clear();
		}
		
		if(resultList != null){
			resultList.clear();
		}
		
		if(pagedList != null){
			pagedList.resetAll();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean search(ActionSearch actionSearch) {
		try {
			if(pagedList == null  || beanProperties.get("selecionador") != null && (Boolean)beanProperties.get("selecionador")){
				
				//pesquisa sem paginacao
				
				actionSearch.beforeSearch(filter, beanProperties);
				actionSearch.executeSearch(filter, null, null);
				actionSearch.afterSearch(actionSearch.getResultList(), actionSearch.getTotalNumRows(), filter, beanProperties);
				
				resultList = actionSearch.getResultList();
				resultProperties = actionSearch.getResultProperties();
				return true;
			}
				
			//pesquisa com paginacao
			actionSearch.beforeSearch(filter, beanProperties);
			actionSearch.executeSearch(filter, pagedList.getFirst(), PAGE_SIZE);
			actionSearch.count(filter);
			actionSearch.afterSearch(actionSearch.getResultList(), actionSearch.getTotalNumRows(), filter, beanProperties);
			resultList = actionSearch.getResultList();
			resultProperties = actionSearch.getResultProperties();
			pagedList.setDataModel(new PagedDataModel(resultList, actionSearch.getTotalNumRows(), PAGE_SIZE));
			return true;
			
		} catch (Throwable e) {
			actionSearch.notSearched(e);
			return false;
		}
	}
	
	public boolean save(ActionSave<IEntity> actionSave){
		try {
			actionSave.beforeSave(defaultEntity, beanProperties);
			defaultEntity = actionSave.executeSave(defaultEntity);
			actionSave.afterSave(defaultEntity, beanProperties);
			return true;
		} catch (Throwable e) {
			actionSave.notSaved(e);
			return false;
		}
		
	}
	
	public boolean disable(ActionDisable<?> actionDisable) {
		try {
			actionDisable.executeDisable((Disabilitable) getDefaultEntity());
			actionDisable.afterDisable();
			return true;
		} catch (Throwable e) {
			actionDisable.notDisabled(e);
			return false;
		}
	}

	public boolean remove(ActionRemove<?> actionRemove) {
		try {
			actionRemove.executeRemove(defaultEntity);
			actionRemove.afterRemove();
			return true;
		} catch (Throwable e) {
			actionRemove.notRemoved(e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends IEntity> T getDefaultEntity() {
		return (T) defaultEntity;
	}

	@SuppressWarnings("unchecked")
	public <T extends IEntity> T createDefaultEntity(Class<T> entityClass) {
		try {
			defaultEntity = entityClass.newInstance();
			
			if(defaultEntity instanceof Disabilitable){
				Disabilitable activeEntity = (Disabilitable) defaultEntity;
				activeEntity.setAtivo(true);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			defaultEntity = null;

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			defaultEntity = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}

		return (T) defaultEntity;
	}

	public <T extends IEntity> void setDefaultEntity(T defaultEntity) {
		this.defaultEntity = defaultEntity;
	}

	// Gets e sets

	public Map<String, Object> getFilter() {
		return filter;
	}

	public void setFilter(Map<String, Object> filter) {
		this.filter = filter;
	}

	@SuppressWarnings("unchecked")
	public <T extends IEntity> List<T> getSearchResult() {
		return (List<T>) resultList;
	}

	@SuppressWarnings("unchecked")
	public <T extends IEntity> void setSearchResult(List<T> searchResult) {
		this.resultList = (List<IEntity>) searchResult;
	}

	public Map<String, Object> getResultProperties() {
		return resultProperties;
	}

	public Paginator getPagedList() {
		return pagedList;
	}

	public void setPagedList(Paginator pagedList) {
		this.pagedList = pagedList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List<?> resultList) {
		this.resultList = (List<IEntity>) resultList;
	}

	public Map<String, Object> getBeanProperties() {
		return beanProperties;
	}

	public void setActionProperties(Map<String, Object> actionProperties) {
		this.beanProperties = actionProperties;
	}
}