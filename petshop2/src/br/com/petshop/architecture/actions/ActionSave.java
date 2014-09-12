package br.com.petshop.architecture.actions;

import java.util.Map;

import br.com.petshop.architecture.entity.IEntity;

public interface ActionSave<T extends IEntity> {

	/**
	 * Chamado antes de salvar na base de dados.
	 * @param entity
	 * @param beanProperties 
	 */
	public void beforeSave(T entity, Map<String, Object> beanProperties);
	
	/**
	 * Salva na base de dados
	 * @param actionEvent
	 * @return
	 */
	public T executeSave(T entity);

	/**
	 * Chamado logo depois de salvo com sucesso
	 * @param beanProperties 
	 * @param defaultEntity 
	 */
	public void afterSave(T defaultEntity, Map<String, Object> beanProperties);

	/**
	 * Chamado caso ocorra algum erro ao salvar
	 * @param exception 
	 */
	public void notSaved(Throwable exception);

}
