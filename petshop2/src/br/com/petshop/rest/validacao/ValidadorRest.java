package br.com.petshop.rest.validacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.petshop.base.exception.PetShopSystemException;

@Component("validadorRest")
public class ValidadorRest {
	
	@Autowired
	private ValidadorLogin validadorLogin;
	
	@Autowired
	private ValidadorLoginInclusao validadorLoginInclusao;
	
	
	public void validarCamposObrigatorios(String... valores) throws PetShopSystemException {
		for (int i = 0; i < valores.length; i += 2) {
			IValidator validator = new ValidadorNaoVazio();
			validator.addValidatedField("campoNome" , valores[i]);
			validator.addValidatedField("campoValor",valores[i + 1]);
			validator.validate();
		}
	}	

	public void validarTamanhoCampo(String nomeCampo, String valor, Integer tamMax) throws PetShopSystemException{
		IValidator validator = new ValidadorTamMax();
		validator.addValidatedField("nomeCampo", nomeCampo);
		validator.addValidatedField("valor", 	 valor);
		validator.addValidatedField("tamMax", 	 tamMax);
		validator.validate();
	}
	
	public void validarNumero(String nomeCampo, String numero) throws PetShopSystemException {
		IValidator validator = new ValidadorSomenteNumero();
		validator.addValidatedField("numero", numero);
		validator.addValidatedField("nomeCampo", nomeCampo);
		validator.validate();
	}

	public void validarUsuarioESenha(String login, String senha) throws PetShopSystemException {
		IValidator validator = validadorLogin;
		validator.addValidatedField("login", login);
		validator.addValidatedField("senha", senha);
		validator.validate();
	}

	public void validarPeriodo(Date dataInicial, Date dataFinal) {
		IValidator validator = new ValidadorPeriodo();
		validator.addValidatedField("dataInicial", dataInicial);
		validator.addValidatedField("dataFinal", dataFinal);
		validator.validate();
	}

	public void validarUsuarioESenhaConsulta(String login, String senha) {
		IValidator validator = validadorLoginInclusao;
		validator.addValidatedField("login", login);
		validator.addValidatedField("senha", senha);
		validator.validate();
	}
	
}
