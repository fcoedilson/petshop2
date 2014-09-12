package br.com.petshop.base.enumeration;

import br.com.petshop.architecture.util.JSFUtil;

/**
 * 0 - CEF SIACC_240
 * 1 - CEF SIACC_150

 */
public enum Layout{
	
	SIACC_240, SIACC_150;
	
	@Override
	public String toString(){
		
		switch (this) {
		case SIACC_240:
			return JSFUtil.getMessageFromBundle("layout.siacc240");
		case SIACC_150:
			return JSFUtil.getMessageFromBundle("layout.siacc150");
		default:
			return null;
		}
	}
	
	public static Layout fromInt(int valor){
		switch (valor) {
		case 0:
			return SIACC_240;
		case 1:
			return SIACC_150;
		default:
			return null;
		}
	}

}