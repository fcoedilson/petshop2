package br.com.petshop.seguranca.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.petshop.architecture.actions.AbstractActionSearch;
import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.architecture.util.StaticFilter;
import br.com.petshop.base.entity.LogAcesso;
import br.com.petshop.base.vo.LogAcessoVO;
import br.com.petshop.seguranca.service.LogAcessoService;

public class LogAcessoActionSearch  extends AbstractActionSearch<LogAcesso> {

	public LogAcessoActionSearch(BaseService<LogAcesso> service) {
		super(service);
	}

	public void beforeSearch(Map<String, Object> filters, Map<String, Object> beanProperties) {}

	public void afterSearch(List<LogAcesso> searchResult, Integer totalNumRows, Map<String, Object> filter, Map<String, Object> beanProperties) {
		
		beanProperties.put("maior", null);
		beanProperties.put("menor", null);
		beanProperties.put("periodo", new DateTime(filter.get(StaticFilter.DATA_INICIO)).toString("dd/MM/yyyy") + " - " + new DateTime(filter.get(StaticFilter.DATA_FIM)).toString("dd/MM/yyyy"));
		
		
		CartesianChartModel menosAcessados = new CartesianChartModel();
		CartesianChartModel maisAcessados = new CartesianChartModel();
		
		ChartSeries acessos;
		ChartSeries acessosMenos;
		
		beanProperties.put("acessos", maisAcessados);
		beanProperties.put("acessosMenos", menosAcessados);
		beanProperties.put("usuarioSemAcesso", ((LogAcessoService)service).buscarUsuarioSemAcesso(filter));
		
	}
	
	private void verificarValorMaior(Long value, Map<String, Object> beanProperties){
		if(value > getMaior(beanProperties)){
			setMaior(value, beanProperties);
		}
	}
	
	private void verificarValorMenor(Long value, Map<String, Object> beanProperties){
		if(value > getMenor(beanProperties)){
			setMenor(value,beanProperties);
		}
	}
	
	private Long getMaior(Map<String, Object> beanProperties){
		return beanProperties.get("maior") == null ? 0L : Long.parseLong(beanProperties.get("maior").toString());
	}
	
	private Long getMenor(Map<String, Object> beanProperties){
		return beanProperties.get("menor") == null ? 0L : Long.parseLong(beanProperties.get("menor").toString());
	}
	
	private void setMaior(Long value, Map<String, Object> beanProperties){
		beanProperties.put("maior", value);
	}
	
	private void setMenor(Long value, Map<String, Object> beanProperties){
		beanProperties.put("menor", value);
	}
}