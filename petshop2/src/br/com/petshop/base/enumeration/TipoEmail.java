package br.com.petshop.base.enumeration;

import br.com.petshop.architecture.util.JSFUtil;

public enum TipoEmail {

	EMAIL_GERA_SENHA;
	
	@Override
	public String toString(){
		
		switch (this) {
		case EMAIL_GERA_SENHA:
			return JSFUtil.getMessageFromBundle("tipo_email_gera_senha");
		default:
			return null;
		}
	}
	
}
