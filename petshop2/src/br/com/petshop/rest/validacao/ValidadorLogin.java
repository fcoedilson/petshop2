package br.com.petshop.rest.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.petshop.architecture.service.AuthenticationService;
import br.com.petshop.base.exception.PetShopSystemException;

@Component
public class ValidadorLogin extends BaseValidator{

	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	public void validate() throws PetShopSystemException {
		String login = (String) getValidatedParameter("login");
		String senha = (String) getValidatedParameter("senha");
		if (!authenticationService.loginConciliacao(login,senha)) {
			throw new PetShopSystemException(getMessagesValidators().getMessageFromCode_100());
		}
	}

	@Override
	protected List<String> setValidParametersName() {
		return setValidParametersName("login","senha");
	}
}
