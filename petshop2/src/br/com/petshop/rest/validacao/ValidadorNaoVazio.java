package br.com.petshop.rest.validacao;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.petshop.base.exception.PetShopSystemException;

@Component
public class ValidadorNaoVazio extends BaseValidator {
	
	@Override
	public void validate() throws PetShopSystemException {

		String campoNome = (String) getValidatedParameter("campoNome");
		Object campo = getValidatedParameter("campoValor");

		if (campo == null) {
			throw new PetShopSystemException(getMessagesValidators().getMessageFromCode_200() + campoNome);
		} else if (campo.toString().isEmpty()) {
			throw new PetShopSystemException(getMessagesValidators().getMessageFromCode_200() + campoNome);
		}
	}

	@Override
	protected List<String> setValidParametersName() {
		return setValidParametersName("campoValor","campoNome");
	}
}
