package br.com.petshop.rest.validacao;

import groovyjarjarcommonscli.Options;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.record.formula.functions.Series;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import br.com.petshop.architecture.util.StaticFilter;
import br.com.petshop.base.entity.LogAcesso;
import br.com.petshop.cadastro.service.UsuarioService;
import br.com.petshop.seguranca.service.LogAcessoService;

@Path("/")
@Component
public class GraficoAcessoUsuariosRestService {
	
	private LogAcessoService logAcessoService;
	private UsuarioService usuarioService;
	
	@GET
	@Path("graficoAcessoUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarInformacoesGrafico(
			@QueryParam(StaticFilter.DATA_INICIAL) String dateTimeInicial, 
			@QueryParam(StaticFilter.DATA_FINAL) String dateTimeFinal){
		
		DateTime dateTimeInicio = new DateTime(dateTimeInicial);
		DateTime dateTimeFim = new DateTime(dateTimeFinal);
		int quantidadeDias = Days.daysBetween(dateTimeInicio, dateTimeFim).getDays() + 1;
		Options options = new Options();
		Series series = null;
		
		
		String[] categoriasMes = getCategoriasMes(dateTimeInicio , dateTimeFim);
		return Response.status(200).entity(options).build();
	}
	
	private double[] verificarArray(Double[] arrayString) {
		double valorAnterior = 0.0;
		double[] valores = new double[arrayString.length];
		int i = 0;
		for(Double valor : arrayString){
			//nessa verificacao poderia ser informado que o saldo esta desatualizado
			if(valor==null){
				valores[i] = valorAnterior ;
			}else {
				valores[i] = valor;
				valorAnterior = valor;
			}
			i++;
		}
		return valores;
	}

	private Double[] criarArray(int quantidadeDias, DateTime dateTimeInicio, List<LogAcesso> listaSaldo) {
		Double[] valores = new Double[quantidadeDias];
	    for (int i = 0; i < quantidadeDias ; i++) {
		      valores[i] = getQuantidadeLog(listaSaldo, dateTimeInicio.toDate());
		      dateTimeInicio = dateTimeInicio.plusDays(1);
	    }
	    return valores;
	}
	
	private Double getQuantidadeLog(List<LogAcesso> listaLog, Date data) {
		Double d = 0.0;
		for(LogAcesso log : listaLog){
			if(new DateMidnight(log.getDataAcesso()).equals(new DateMidnight(data))){
				d++;
			}
		}return d;
	}

	private String[] getCategoriasMes(DateTime dateTimeInicio , DateTime dateTimeFim){
		int quantidadeDias = Days.daysBetween(dateTimeInicio, dateTimeFim).getDays() + 1;
		
		String[] categories = new String[quantidadeDias];
		for(int i = 0; i < quantidadeDias; i++){
			categories[i] = dateTimeInicio.getDayOfMonth() + getMes(dateTimeInicio);
			dateTimeInicio = dateTimeInicio.plusDays(1);
		}
		return categories;
	}

	private String getMes(DateTime dateTimeInicio) {
		if(dateTimeInicio.getMonthOfYear()==1){
			return "Jan";
		}else if(dateTimeInicio.getMonthOfYear()==2){
			return "Fev";
		}else if(dateTimeInicio.getMonthOfYear()==3){
			return "Mar";
		}else if(dateTimeInicio.getMonthOfYear()==4){
			return "Abr";
		}else if(dateTimeInicio.getMonthOfYear()==5){
			return "Mai";
		}else if(dateTimeInicio.getMonthOfYear()==6){
			return "Jun";
		}else if(dateTimeInicio.getMonthOfYear()==7){
			return "Jul";
		}else if(dateTimeInicio.getMonthOfYear()==8){
			return "Ago";
		}else if(dateTimeInicio.getMonthOfYear()==9){
			return "Set";
		}else if(dateTimeInicio.getMonthOfYear()==10){
			return "Out";
		}else if(dateTimeInicio.getMonthOfYear()==11){
			return "Nov";
		}
		return "Dez";
	}

	public LogAcessoService getLogAcessoService() {
		if(logAcessoService==null){
			return  (LogAcessoService) ContextLoader.getCurrentWebApplicationContext().getBean("logAcessoService");
		}
		return logAcessoService;
	}

	public UsuarioService getUsuarioService() {
		if(usuarioService==null){
			return  (UsuarioService) ContextLoader.getCurrentWebApplicationContext().getBean("usuarioService");
		}
		return usuarioService;
	}

}
