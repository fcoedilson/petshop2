package br.com.petshop.base.enumeration;

import br.com.petshop.architecture.util.JSFUtil;

/**
 * 0 - TITULO
 * 1 - TAXA_TRIBUTO

 */
public enum TipoBoleto{
	
	TITULO, TAXA_TRIBUTO;
	
	@Override
	public String toString(){
		
		switch (this) {
		case TITULO:
			return JSFUtil.getMessageFromBundle("boleto.titulo");
		case TAXA_TRIBUTO:
			return JSFUtil.getMessageFromBundle("boleto.taxa_tributo");
		default:
			return null;
		}
	}
	
	public static TipoBoleto fromInt(int valor){
		switch (valor) {
		case 0:
			return TITULO;
		case 1:
			return TAXA_TRIBUTO;
		default:
			return null;
		}
	}
}