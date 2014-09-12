package br.com.petshop.base.enumeration;

import br.com.petshop.architecture.util.JSFUtil;

/**
 * 0 - REMESSA
 * 1 - RETORNO

 */
public enum TipoArquivo{
	
	REMESSA, RETORNO;
	
	@Override
	public String toString(){
		
		switch (this) {
		case REMESSA:
			return JSFUtil.getMessageFromBundle("arquivo.remessa");
		case RETORNO:
			return JSFUtil.getMessageFromBundle("arquivo.retorno");
		default:
			return null;
		}
	}
	
	public static TipoArquivo fromInt(int valor){
		switch (valor) {
		case 0:
			return REMESSA;
		case 1:
			return RETORNO;
		default:
			return null;
		}
	}

}