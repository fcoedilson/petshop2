package br.com.petshop.seguranca.bean;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;

import br.com.petshop.architecture.actions.ActionSearch;
import br.com.petshop.architecture.modules.AbstractFacadeBean;
import br.com.petshop.architecture.service.AuthenticationService;
import br.com.petshop.base.entity.LogAcesso;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.base.vo.LogAcessoVO;
import br.com.petshop.seguranca.action.LogAcessoActionSearch;
import br.com.petshop.seguranca.service.LogAcessoService;

@ViewScoped
@ManagedBean
public class LogAcessoBean extends AbstractFacadeBean<LogAcesso>{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{logAcessoService}")
	private LogAcessoService logAcessoService;

	@Override
	protected String getPageTitle() {
		return "titulo_log_acesso";
	}

	protected Class<LogAcesso> getDefaultEntityClass() {
		return LogAcesso.class;
	}
	
	protected ActionSearch<LogAcesso> searchAction() {
		return new LogAcessoActionSearch(logAcessoService);
	}
	
	public void initBean(){
		getFilter().put(DATA_INICIO, new DateTime().withDayOfMonth(1).toDate());
		getFilter().put(DATA_FIM, new DateTime().toDate());
		search();
	}
	
	public String gerarRelatorio(){
		String imgCabecalho = "/resources/imagens/cabecalho_report.jpg".replace("/", File.separator);
		String pathCabecalhoImg = getReportService().getPathReal(imgCabecalho);
		String path = "/report/logAcesso/logAcesso.jasper".replace("/", File.separator);
		String nomeDownload = "logAcesso";
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("USUARIO", AuthenticationService.getLoggedUser().getNome());
		parametros.put("CABECALHO", pathCabecalhoImg);
		parametros.put("DATA", new DateTime().toString("dd/MM/yyyy"));
		getReportService().buildReportXLSAttachment(path, nomeDownload, parametros, getSearchResult());
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String gerarRelatorioMaisAcessados(){
		String imgCabecalho = "/resources/imagens/cabecalho_report.jpg".replace("/", File.separator);
		String pathCabecalhoImg = getReportService().getPathReal(imgCabecalho);
		String path = "/report/logAcesso/logAcessoMaisAcessados.jasper".replace("/", File.separator);
		String nomeDownload = "maisAcessados";
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("USUARIO", AuthenticationService.getLoggedUser().getNome());
		parametros.put("CABECALHO", pathCabecalhoImg);
		parametros.put("DATA", new DateTime().toString("dd/MM/yyyy"));
		List<LogAcessoVO> acessoVOs = (List<LogAcessoVO>)getBeanProperties().get("maisAcessados");
		Collections.sort(acessoVOs);
		getReportService().buildReportXLSAttachment(path, nomeDownload, parametros, acessoVOs);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String gerarRelatorioMenosAcessados(){
		String imgCabecalho = "/resources/imagens/cabecalho_report.jpg".replace("/", File.separator);
		String pathCabecalhoImg = getReportService().getPathReal(imgCabecalho);
		String path = "/report/logAcesso/logAcessoMaisAcessados.jasper".replace("/", File.separator);
		String nomeDownload = "menosAcessados";
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("USUARIO", AuthenticationService.getLoggedUser().getNome());
		parametros.put("CABECALHO", pathCabecalhoImg);
		parametros.put("DATA", new DateTime().toString("dd/MM/yyyy"));
		List<LogAcessoVO> acessoVOs = (List<LogAcessoVO>)getBeanProperties().get("menosAcessados");
		Collections.sort(acessoVOs);
		getReportService().buildReportXLSAttachment(path, nomeDownload, parametros, acessoVOs);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String gerarRelatorioSemAcesso(){
		String imgCabecalho = "/resources/imagens/cabecalho_report.jpg".replace("/", File.separator);
		String pathCabecalhoImg = getReportService().getPathReal(imgCabecalho);
		String path = "/report/logAcesso/logUltimoAcesso.jasper".replace("/", File.separator);
		String nomeDownload = "menosAcessados";
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("USUARIO", AuthenticationService.getLoggedUser().getNome());
		parametros.put("CABECALHO", pathCabecalhoImg);
		parametros.put("DATA", new DateTime().toString("dd/MM/yyyy"));
		List<LogAcessoVO> acessoVOs = (List<LogAcessoVO>)getBeanProperties().get("usuarioSemAcesso");
		Collections.sort(acessoVOs);
		getReportService().buildReportXLSAttachment(path, nomeDownload, parametros, acessoVOs);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuario(){
		return (List<Usuario>)getFilter().get(USUARIO);
	}
	
	public void setUsuario(List<Usuario> list){
		getFilter().put(USUARIO, list);
	}

	public void setLogAcessoService(LogAcessoService logAcessoService) {
		this.logAcessoService = logAcessoService;
	}
}