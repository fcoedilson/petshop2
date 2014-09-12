package br.com.petshop.architecture.actions;

import java.util.Map;

import br.com.petshop.architecture.entity.IEntity;
import br.com.petshop.architecture.service.BaseService;

public final class DefaultActionSave<T extends IEntity> extends AbstractActionSave<T>{

	public DefaultActionSave(BaseService<T> service) {
		super(service);
	}

	public void beforeSave(T entity, Map<String, Object> actionProperties) {}
	
}
