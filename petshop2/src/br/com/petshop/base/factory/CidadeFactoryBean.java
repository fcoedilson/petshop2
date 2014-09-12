package br.com.petshop.base.factory;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.petshop.base.entity.Cidade;
import br.com.petshop.base.entity.Estado;
import br.com.petshop.base.service.CidadeService;

@Component
public class CidadeFactoryBean implements Serializable {

	private static final long serialVersionUID = 5382508038904528302L;
	
	@Autowired
	private CidadeService cidadeService;
	
	public List<Cidade> findByEstado(Estado estado){
		if(estado == null || estado.getId() == null){
			return null;
		}
		return cidadeService.findByEstado(estado);
	}
	
	public List<Cidade> findByNameMaisEstado(String nome, Estado estado){
		if(nome == null || estado==null){
			return null;
		}
		return cidadeService.findByNameMaisEstado(nome, estado);
	}
}