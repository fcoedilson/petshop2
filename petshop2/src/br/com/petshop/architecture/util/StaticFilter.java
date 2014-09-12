package br.com.petshop.architecture.util;

public class StaticFilter {
	
	public static final String STATUS_VALIDAS = " (1,2,3,5,8) ";
	public static final String STATUS_EM_ABERTO = " (1,2) ";
	public static final String STATUS_CANCELADAS = " (4,6,7) ";
	
	public static final int PAGE_SIZE = 20;
	public static final String TIPO = "tipo";
	public static final String GRUPO = "grupo";
	public static final String ATIVO = "ativo";
	public static final String VALOR = "valor";
	public static final String VISAO = "visao";
	public static final String CONTA = "conta";
	public static final String STATUS = "status";
	public static final String GRUPOS = "grupos";
	public static final String USUARIO = "usuario";
	public static final String DATA_FIM = "dataFim";
	public static final String CONVENIO = "convenio";
	public static final String DESCRICAO = "descricao";
	public static final String DATA_FINAL = "dataFinal";
	public static final String DATA_INICIO = "dataInicio";
	public static final String RAZAO_SOCIAL = "razaoSocial";
	public static final String DATA_INICIAL = "dataInicial";
	public static final String TIPO_CONVENIO = "tipoConvenio";
	public static final String TIPO_ARQUIVO = "tipoArquivo";
	public static final String PAGINA_CLICADA = "paginaClicada";
	public static final String DATA_FINAL_ANO = "dataFinalAno";
	public static final String LISTA_AUT_REMESSSA = "listaAutorizacaoRemessa";
	public static final String AUTORIZACAO_REMESSSA = "autorizacaoRemessa";
	public static final String HABILITA_GERA_SENHA = "habilitaGeraSenha";
	
	public static int getPageSize() {
		return PAGE_SIZE;
	}
}
