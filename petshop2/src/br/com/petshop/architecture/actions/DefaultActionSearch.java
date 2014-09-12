package br.com.petshop.architecture.actions;

import java.util.List;
import java.util.Map;

import br.com.petshop.architecture.entity.IEntity;
import br.com.petshop.architecture.service.BaseService;

public final class DefaultActionSearch<T extends IEntity> extends AbstractActionSearch<T>{

	public void beforeSearch(Map<String, Object> filters, Map<String, Object> actionProperties) {}

	public DefaultActionSearch(BaseService<T> service) {
		super(service);
	}


	public Map<String, Object> getResultProperties() {
		return null;
	}

	public void afterSearch(List<T> searchResult, Integer totalNumRows,	Map<String, Object> filter, Map<String, Object> actionProperties) {}
}
