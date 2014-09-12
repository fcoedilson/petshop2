package br.com.petshop.base.enumeration;

import br.com.petshop.architecture.util.JSFUtil;

/**
 * 0 - PENDENTE
 * 1 - ALTERADO
 * 2 - AUTORIZADO
 * 3 - VENCIDO
 * 4 - GERADO
 * 5 - ACATADO
 * 6 - PAGO
 * 7 - REJEITADO
 * 8 - PAGO_PARCIAL
 */
public enum Status{
	
	PENDENTE, ALTERADO, AUTORIZADO, VENCIDO, GERADO, ACATADO, PAGO, REJEITADO, PAGO_PARCIAL;
	
	@Override
	public String toString(){
		
		switch (this) {
		case PENDENTE:
			return JSFUtil.getMessageFromBundle("status.pendente");
		case ALTERADO:
			return JSFUtil.getMessageFromBundle("status.alterado");
		case AUTORIZADO:
			return JSFUtil.getMessageFromBundle("status.autorizado");
		case PAGO:
			return JSFUtil.getMessageFromBundle("status.pago");
		case VENCIDO:
			return JSFUtil.getMessageFromBundle("status.vencido");
		case GERADO:
			return JSFUtil.getMessageFromBundle("status.gerado");
		case ACATADO:
			return JSFUtil.getMessageFromBundle("status.acatado");
		case REJEITADO:
			return JSFUtil.getMessageFromBundle("status.rejeitado");
		case PAGO_PARCIAL:
			return JSFUtil.getMessageFromBundle("status.pago_parcial");
		default:
			return null;
		}
	}
	
	public static Status fromInt(int valor){
		switch (valor) {
		case 0:
			return PENDENTE;
		case 1:
			return ALTERADO;
		case 2:
			return AUTORIZADO;
		case 3:
			return VENCIDO;
		case 4:
			return GERADO;
		case 5:
			return ACATADO;
		case 6:
			return PAGO;
		case 7:
			return REJEITADO;
		case 8:
			return PAGO_PARCIAL;
		default:
			return null;
		}
	}
}