package br.com.petshop.base.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class TelefoneValidator implements Validator {
	public void validate(FacesContext contest, UIComponent component, Object tel) throws ValidatorException {
		if(tel == null ){
			return;
		}
		if (!validaTelefone(String.valueOf(tel))) {
			throw new ValidatorException(MensagemValidator.MensagensValidator.mensagemErro("Telefone incorreto", "telefone"));
		}
	}
	private boolean validaTelefone(String tel) {
		if (tel == null || tel.length() < 10 || tel.length() > 11) {
			return false;
		}
		try {
			Long.parseLong(tel);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}