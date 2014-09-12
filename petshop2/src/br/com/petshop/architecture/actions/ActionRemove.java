package br.com.petshop.architecture.actions;

import br.com.petshop.architecture.entity.IEntity;

public interface ActionRemove<T extends IEntity> {

	/**
	 * Executa uma remocao na base de dados
	 * @param actionEvent
	 * @return
	 */
	public void executeRemove(IEntity filters);

	/**
	 * Chamado logo depois que a remocao e concluida com sucesso
	 */
	public void afterRemove();

	/**
	 * Chamado caso ocorra algum erro durante a remocao
	 * @param exception 
	 */
	public void notRemoved(Throwable exception);
	
}
