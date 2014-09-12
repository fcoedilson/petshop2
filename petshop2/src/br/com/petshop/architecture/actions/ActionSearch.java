package br.com.petshop.architecture.actions;

import java.util.List;
import java.util.Map;

import br.com.petshop.architecture.entity.IEntity;

public interface ActionSearch<T extends IEntity> {

	/**
	 * Executado antes da busca, podera ser usado para fazer validacoes e coisas afins...
	 * @param beanProperties 
	 * @param searchResult
	 */
	public void beforeSearch(Map<String, Object> filters, Map<String, Object> beanProperties);
	
	/**
	 * Executa uma busca na base de dados e retorna o resultado da lista
	 * @param actionEvent
	 * @return
	 */
	public void executeSearch(Map<String, Object> filters, Integer first, Integer rows);

	/**
	 * Chamado logo depois que a busca e concluida com sucesso
	 * @param beanProperties 
	 * @param integer 
	 */
	public void afterSearch(List<T> searchResult, Integer totalNumRows, Map<String, Object> filter, Map<String, Object> beanProperties);

	/**
	 * Chamado caso ocorra algum erro durante a busca
	 * @param exception 
	 */
	public void notSearched(Throwable exception);

	/**
	 * Durante o tratamento da lista pode ser que outros valores 
	 * como resumos, contagens... sejam criados. Nesse caso, 
	 * esses  valores secundarios serao inseridos em um mapa de 
	 * propriedades que serao acessados por aqui atraves de uma chave.
	 * @param key
	 * @return
	 */
	public Map<String,Object> getResultProperties();
	
	public List<T> getResultList();
	
	public Integer getTotalNumRows();
	
	public void count(Map<String, Object> filters);

}
