package br.com.petshop.rest.validacao;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

@Component("messagesPagamentoValidators")
public class MessagesPagamentoValidators {
	private static ResourceBundle resource = ResourceBundle.getBundle("br.com.petshop.rest.validacao.messagesPagamento", new Locale("pt_BR"));

	public String getMessageFromCode_000() {
		return resource.getString("000");
	}
	
//  --------------
	
	public String getMessageFromCode_100() {
		return resource.getString("100");
	}

	public String getMessageFromCode_101() {
		return resource.getString("101");
	}

	public String getMessageFromCode_102() {
		return resource.getString("102");
	}

//  --------------
	
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

	public String getMessageFromCode_209(String nomeCampo, Integer tamMax) {
		return getFormattedMessage("209", nomeCampo, tamMax);
	}
	
	public String getMessageFromCode_210() {
	    return resource.getString("210");
	}
	
	public String getMessageFromCode_211() {
	    return resource.getString("211");
	}
	
	public String getMessageFromCode_212() {
	    return resource.getString("212");
	}
	
	public String getMessageFromCode_213() {
	    return resource.getString("213");
	}
	
	public String getMessageFromCode_214() {
	    return resource.getString("214");
	}
	
	public String getMessageFromCode_215() {
	    return resource.getString("215");
	}
	
	public String getMessageFromCode_216() {
	    return resource.getString("216");
	}
	
	public String getMessageFromCode_217() {
		return resource.getString("217");
	}
	
//  --------------
	
	public String getMessageFromCode_300() {
		return resource.getString("300");
	}
	
	public String getMessageFromCode_301() {
		return resource.getString("301");
	}
	
	public String getMessageFromCode_302() {
		return resource.getString("302");
	}
	
//  --------------
	
	public String getMessageFromCode_400() {
		return resource.getString("400");
	}
	
	public String getMessageFromCode_401() {
		return resource.getString("401");
	}
	private String getFormattedMessage(String key, Object...params){
		MessageFormat messageFormat = new MessageFormat(resource.getString(key));
		Object[] objs = params;
		return messageFormat.format(objs);
	}
	
}
