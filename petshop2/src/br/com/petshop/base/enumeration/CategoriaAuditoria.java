package br.com.petshop.base.enumeration;

import br.com.petshop.architecture.util.JSFUtil;

/**
 * 0 - ACESSO SISTEMA
 * 1 - PAGAMENTO
 * 2 - CADASTRO USUARIO
 * 3 - CADASTRO CONVENIO
 * 4 - CADASTRO FUNCIONARIO
 * 5 - CADASTRO GRUPO FUNCIONARIO
 * 8 - CADASTRO FORNECEDOR
 * 9 - CADASTRO GRUPO FORNECEDOR
 * 10 - CADASTRO FAVORECIDO
 * 11 - CADASTRO GRUPO
 */
public enum CategoriaAuditoria{
	
	 ACESSO_SISTEMA, PAGAMENTO, CADASTRO_USUARIO, CADASTRO_CONVENIO, CADASTRO_FUNCIONARIO, CADASTRO_GRUPO_FUNCIONARIO,CADASTRO_FORNECEDOR,CADASTRO_GRUPO_FORNECEDOR,CADASTRO_FAVORECIDO,CADASTRO_GRUPO;
	
	@Override
	public String toString(){
		
		switch (this) {
		case ACESSO_SISTEMA:
			return JSFUtil.getMessageFromBundle("status.acesso");
		case PAGAMENTO:
			return JSFUtil.getMessageFromBundle("status.pagamento");
		case CADASTRO_USUARIO:
			return JSFUtil.getMessageFromBundle("status.cadUsuario");
		case CADASTRO_CONVENIO:
			return JSFUtil.getMessageFromBundle("status.cadConvenio");
		case CADASTRO_FUNCIONARIO:
			return JSFUtil.getMessageFromBundle("status.cadFuncionario");
		case CADASTRO_FORNECEDOR:
			return JSFUtil.getMessageFromBundle("status.cadFornecedor");
		case CADASTRO_GRUPO_FUNCIONARIO:
			return JSFUtil.getMessageFromBundle("status.cadGrupoFunc");
		case CADASTRO_GRUPO_FORNECEDOR:
			return JSFUtil.getMessageFromBundle("status.cadGrupoForn");
		case CADASTRO_FAVORECIDO:
			return JSFUtil.getMessageFromBundle("status.cadFavorecido");
		case CADASTRO_GRUPO:
			return JSFUtil.getMessageFromBundle("status.cadGrupo");
		default:
			return null;
		}
	}
	
	public static CategoriaAuditoria fromInt(int valor){
		switch (valor) {
		case 0:
			return ACESSO_SISTEMA;
		case 1:
			return PAGAMENTO;
		case 2:
			return CADASTRO_USUARIO;
		case 3:
			return CADASTRO_CONVENIO;
		case 4:
			return CADASTRO_FUNCIONARIO;
		case 5:
			return CADASTRO_GRUPO_FUNCIONARIO;
		case 8:
			return CADASTRO_FORNECEDOR;
		case 9:
			return CADASTRO_GRUPO_FORNECEDOR;
		case 10:
			return CADASTRO_FAVORECIDO;
		case 11:
			return CADASTRO_GRUPO;
		default:
			return null;
		}
	}
}