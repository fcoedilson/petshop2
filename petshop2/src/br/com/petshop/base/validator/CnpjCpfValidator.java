package br.com.petshop.base.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class CnpjCpfValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String cnpjCpf = String.valueOf(value);
		if(cnpjCpf.length() <= 11){
			if(!CpfValidator.validaCpf(String.valueOf(value))){
			throw new ValidatorException(MensagemValidator.MensagensValidator.mensagemErro("CPF incorreto", "cnpjCpf"));
			}
		} else {
			if(!CnpjValidator.validaCnpj(String.valueOf(value))){
				throw new ValidatorException(MensagemValidator.MensagensValidator.mensagemErro("CNPJ incorreto", "cnpjCpf"));				
			}		
		}
	}
}

