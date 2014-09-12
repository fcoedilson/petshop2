package br.com.petshop.architecture.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.petshop.base.entity.Permissao;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.cadastro.service.UsuarioService;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {
	
	private static final long serialVersionUID = 4515175489599849725L;
	
	private static final String ADMIN_ROOT = "ADMIN_ROOT";
	
	@Autowired
	private UsuarioService usuarioService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Usuario usuario = usuarioService.findByLoginAtivo(username);
		carregarPermissoesUsuario(usuario);
		return usuario;
	}
	
	private void carregarPermissoesUsuario(Usuario usuario){
        
        List<Permissao> listPermissaoUsuario = null;
        if(usuario.getRoot()){
            listPermissaoUsuario = usuarioService.findAllPermissao();
            listPermissaoUsuario.add(new Permissao(ADMIN_ROOT));
        } else {
            listPermissaoUsuario = usuarioService.findPermissaoByUsuario(usuario);
        }
        usuario.setPermissoes(listPermissaoUsuario);
    }
}