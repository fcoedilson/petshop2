package br.com.petshop.rest.validacao;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

@Component("messagesValidators")
public class MessagesValidators {
	private static ResourceBundle resource = ResourceBundle.getBundle("br.com.petshop.rest.validacao.messages", new Locale("pt_BR"));

	public String getMessageFromCode_000() {
		return resource.getString("000");
	}

	public String getMessageFromCode_100() {
		return resource.getString("100");
	}

	public String getMessageFromCode_101() {
		return resource.getString("101");
	}

	public String getMessageFromCode_104() {
		return resource.getString("104");
	}
	
	public String getMessageFromCode_200() {
		return resource.getString("200");
	}

	public String getMessageFromCode_201() {
		return resource.getString("201");
	}

	public String getMessageFromCode_202() {
		return resource.getString("202");
	}

	public String getMessageFromCode_203() {
		return resource.getString("203");
	}

	public String getMessageFromCode_204() {
		return resource.getString("204");
	}

	public String getMessageFromCode_205(String nomeCampo) {
		return getFormattedMessage("205", nomeCampo);
	}

	public String getMessageFromCode_206(Integer tamanhoInicioValido, Integer tamanhoFimValido) {
		return getFormattedMessage("206",tamanhoInicioValido,tamanhoFimValido);
	}
	
	public String getMessageFromCode_207() {
		return resource.getString("207");
	}
	
	public String getMessageFromCode_208() {
		return resource.getString("208");
	}
	
	public String getMessageFromCode_210() {
	    return resource.getString("210");
	}
	
	public String getMessageFromCode_209(String nomeCampo, Integer tamMax) {
		return getFormattedMessage("209", nomeCampo, tamMax);
	}
	
	public String getMessageFromCode_300() {
		return resource.getString("300");
	}
	
	private String getFormattedMessage(String key, Object...params){
		MessageFormat messageFormat = new MessageFormat(resource.getString(key));
		Object[] objs = params;
		return messageFormat.format(objs);
	}

}
