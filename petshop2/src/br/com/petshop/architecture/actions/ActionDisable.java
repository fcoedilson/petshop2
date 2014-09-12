package br.com.petshop.architecture.actions;

import br.com.petshop.architecture.service.Disabilitable;

public interface ActionDisable<T extends Disabilitable> {

	/**
	 * Desabilita na base de dados
	 * @param actionEvent
	 * @return
	 */
	public void executeDisable(Disabilitable disabilitable);

	/**
	 * Chamado logo depois que desabilitado com sucesso
	 */
	public void afterDisable();

	/**
	 * Chamado caso ocorra algum erro
	 * @param exception 
	 */
	public void notDisabled(Throwable exception);
	
}
