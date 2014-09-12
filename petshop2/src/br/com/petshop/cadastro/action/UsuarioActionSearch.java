package br.com.petshop.cadastro.action;

import java.util.List;
import java.util.Map;

import br.com.petshop.architecture.actions.AbstractActionSearch;
import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.cadastro.service.UsuarioService;

public class UsuarioActionSearch extends AbstractActionSearch<Usuario> {

	public UsuarioActionSearch(BaseService<Usuario> service) {
		super(service);
	}

	public void beforeSearch(Map<String, Object> filters, Map<String, Object> beanProperties) {}

	public void afterSearch(List<Usuario> searchResult, Integer totalNumRows, Map<String, Object> filter, Map<String, Object> beanProperties) {
		for(Usuario usuario : searchResult){
			usuario.setUltimoBloqueio(((UsuarioService)service).buscarUltimoBloqueio(usuario));
		}		
	}
}