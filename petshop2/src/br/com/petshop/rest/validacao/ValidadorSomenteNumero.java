package br.com.petshop.rest.validacao;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.petshop.base.exception.PetShopSystemException;

public class ValidadorSomenteNumero extends BaseValidator{

	@Override
	public void validate() throws PetShopSystemException {
		String numero = (String) getValidatedParameter("numero");
		String nomeCampo = (String) getValidatedParameter("nomeCampo");

		if(!StringUtils.isNumeric(numero)){
			throw new PetShopSystemException(getMessagesValidators().getMessageFromCode_205(nomeCampo));
		}
	}

	@Override
	protected List<String> setValidParametersName() {
		return setValidParametersName("numero" ,  "nomeCampo");
	}

}
