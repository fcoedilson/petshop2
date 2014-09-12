package br.com.petshop.seguranca.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.petshop.architecture.service.AuthenticationService;
import br.com.petshop.seguranca.service.QuadroAvisoAdminService;

@ViewScoped
@ManagedBean
public class QuadroAvisoAdminBean implements Serializable {

	private static final long serialVersionUID = 7825372035639318617L;
	
	@ManagedProperty("#{quadroAvisoAdminService}")
	private QuadroAvisoAdminService quadroAvisoAdminService;
	
	private Long countLogControleNSA;
	private Long countLogEmail;
	private Long countLojaExtensaoDesconhecido;

	
	public Long getLogControleNsa(){
		return countLogControleNSA;
	}
	
	public Long getLogEmail(){
		if(countLogEmail == null){
			setCountLogEmail(quadroAvisoAdminService.contarTodosLogEmailNaoEnviados());
		}
		return countLogEmail;
	}
	
	public Long getLojaExtensaoDesconhecido(){
		return countLojaExtensaoDesconhecido;
	}
	
	public boolean isRenderizaQuadroAvisoAdministrador(){
		return AuthenticationService.getLoggedUser().hasPermission("QD_AVISO_ADMIN_R");
	}
	
	public boolean isRenderizaLogEmail(){
		return AuthenticationService.getLoggedUser().hasPermission("QD_LOG_EMAIL_R")
				&& AuthenticationService.getLoggedUser().hasPermission("MENU_EMAIL_R");
	}

	public void setQuadroAvisoAdminService(QuadroAvisoAdminService quadroAvisoAdminService) {
		this.quadroAvisoAdminService = quadroAvisoAdminService;
	}

	public Long getCountLogControleNSA() {
		return countLogControleNSA;
	}

	public void setCountLogControleNSA(Long countLogControleNSA) {
		this.countLogControleNSA = countLogControleNSA;
	}

	public Long getCountLogEmail() {
		return countLogEmail;
	}

	public void setCountLogEmail(Long countLogEmail) {
		this.countLogEmail = countLogEmail;
	}

	public Long getCountLojaExtensaoDesconhecido() {
		return countLojaExtensaoDesconhecido;
	}

	public void setCountLojaExtensaoDesconhecido(Long countLojaExtensaoDesconhecido) {
		this.countLojaExtensaoDesconhecido = countLojaExtensaoDesconhecido;
	}

	public QuadroAvisoAdminService getQuadroAvisoAdminService() {
		return quadroAvisoAdminService;
	}
}