package br.com.petshop.rest.validacao;

import br.com.petshop.base.exception.PetShopSystemException;


public interface IValidator {
	void validate() throws PetShopSystemException;
	IValidator addValidatedField(String key, Object obj);
}
