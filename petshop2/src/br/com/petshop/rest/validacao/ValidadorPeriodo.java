package br.com.petshop.rest.validacao;

import java.util.Date;
import java.util.List;

import br.com.petshop.base.exception.PetShopSystemException;

public class ValidadorPeriodo extends BaseValidator{

	@Override
	public void validate() throws PetShopSystemException {
		Date dataInicial = (Date) getValidatedParameter("dataInicial");
		Date dataFinal = (Date) getValidatedParameter("dataFinal");
		if (dataFinal.before(dataInicial)) {
			throw new PetShopSystemException(getMessagesValidators().getMessageFromCode_100());
		}
	}

	@Override
	protected List<String> setValidParametersName() {
		return setValidParametersName("dataInicial" , "dataFinal");

	}
}
