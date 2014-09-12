package br.com.petshop.architecture.service;

import br.com.petshop.architecture.entity.IEntity;

public interface Disabilitable extends IEntity {

	public Boolean getAtivo();
	
	public void setAtivo(Boolean ativo);
}
