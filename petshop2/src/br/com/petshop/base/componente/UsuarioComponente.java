package br.com.petshop.base.componente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.petshop.base.entity.LogAcesso;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.cadastro.service.UsuarioService;
import br.com.petshop.seguranca.service.LogAcessoService;

@Component
public class UsuarioComponente {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private LogAcessoService logAcessoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Scheduled(cron="* 00 23 * * ?")
	public void desabilitarUsuarioInativo(){
		for(Usuario usuario : usuarioService.buscarUsuarioAtivo()){
			LogAcesso logAcesso = logAcessoService.buscarUltimoAcesso(usuario);
			if(logAcesso != null){
				if(Days.daysBetween(new DateTime(logAcesso.getDataAcesso()), new DateTime()).getDays() >= 90){
					usuarioService.updateAtivo(usuario, false);
				}
			}
		}
	}
}