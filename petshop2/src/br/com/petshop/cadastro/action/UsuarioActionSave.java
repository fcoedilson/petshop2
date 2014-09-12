package br.com.petshop.cadastro.action;

import java.util.Map;

import org.primefaces.model.DualListModel;

import br.com.petshop.architecture.actions.AbstractActionSave;
import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.Perfil;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.base.exception.PetShopSystemException;
import br.com.petshop.cadastro.service.UsuarioService;

public class UsuarioActionSave extends AbstractActionSave<Usuario> {
	
	public UsuarioActionSave(BaseService<Usuario> service) {
		super(service);
	}

	@SuppressWarnings("unchecked")
	public void beforeSave(Usuario usuario, Map<String, Object> actionProperties) {
		DualListModel<Perfil> listaPerfil = (DualListModel<Perfil>) actionProperties.get(UsuarioService.LISTA_PERFIL);
		usuario.setPerfis(listaPerfil.getTarget());
		usuario.setLogin(usuario.getLogin().toUpperCase());
		usuario.setNome(usuario.getNome().toUpperCase());

		if(usuario.isNew() && ((UsuarioService) service).isValidLogin(usuario)){
			throw new PetShopSystemException("login_existente");
		}
	}
	
}