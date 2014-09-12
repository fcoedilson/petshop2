package br.com.petshop.base.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class CepValidator implements Validator {
	public void validate(FacesContext contest, UIComponent component, Object cep) throws ValidatorException {
		if (!validaCep(String.valueOf(cep))) {
			throw new ValidatorException(MensagemValidator.MensagensValidator.mensagemErro("CEP incorreto", "cep"));
		}
	}
	private boolean validaCep(String cepInteger) {
		if (cepInteger == null || cepInteger.length() != 8 ) {
			return false;
		}
		try {
			Long.parseLong(cepInteger);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
        
	}
}