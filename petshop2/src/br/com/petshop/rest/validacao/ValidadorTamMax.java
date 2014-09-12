package br.com.petshop.rest.validacao;

import java.util.List;

import br.com.petshop.base.exception.PetShopSystemException;

public class ValidadorTamMax extends BaseValidator {

	@Override
	public void validate() throws PetShopSystemException {
		String  nomeCampo 	= (String) getValidatedParameter("nomeCampo");
		String  valor 		= (String) getValidatedParameter("valor");
		Integer tamMax 		= (Integer) getValidatedParameter("tamMax");
		
		if(valor.length() > tamMax){
			throw new PetShopSystemException(getMessagesValidators().getMessageFromCode_209(nomeCampo, tamMax));
		}
	}
	
	@Override
	protected List<String> setValidParametersName() {
		return setValidParametersName("nomeCampo", "valor", "tamMax");
	}

}
