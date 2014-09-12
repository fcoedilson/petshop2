package br.com.petshop.base.factory;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.petshop.base.entity.Estado;
import br.com.petshop.base.service.EstadoService;

@Component
public class EstadoFactoryBean implements Serializable {
	
	private static final long serialVersionUID = 3888453725612248587L;
	
	@Autowired
	private EstadoService estadoService;
	
	public List<Estado> findAll(){
		return estadoService.findAll();
	}
}