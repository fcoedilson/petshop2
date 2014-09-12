package br.com.petshop.seguranca.bean;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;

import br.com.petshop.architecture.service.AuthenticationService;
import br.com.petshop.architecture.util.JSFUtil;
import br.com.petshop.architecture.util.SystemException;
import br.com.petshop.base.entity.ControleBloqueioUsuario;
import br.com.petshop.base.entity.ControleSenha;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.base.service.ControleBloqueioUsuarioService;
import br.com.petshop.base.service.ControleSenhaService;
import br.com.petshop.cadastro.service.UsuarioService;

@ViewScoped
@ManagedBean
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;
	
	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService;
	
	@ManagedProperty(value="#{controleSenhaService}")
	private ControleSenhaService controleSenhaService;
	
	@ManagedProperty(value="#{controleBloqueioUsuarioService}")
	private ControleBloqueioUsuarioService controleBloqueioUsuarioService;

	public String logar() throws SystemException {
		
		if(!validCampos()){
			JSFUtil.addGlobalMessage("todos_campos_obrigatorios", FacesMessage.SEVERITY_WARN);
			return "login";
		}
		Long id = usuarioService.findByLogin(usuario.getLogin());
		if(id == null){
			JSFUtil.addGlobalMessage("usuario_ou_senha_errado", FacesMessage.SEVERITY_WARN);
			return "login";
		}
		
		Usuario user = new Usuario(id);
		ControleBloqueioUsuario controleBloqueioUsuario = controleBloqueioUsuarioService.buscarControleBloqueioUsuario(user);
		
		if(controleBloqueioUsuario != null && !controleBloqueioUsuario.getLoginSucesso()){
			if(controleBloqueioUsuario.getTentativa() == 6 && controleBloqueioUsuario.getBloqueado()){
				Integer minutos = Minutes.minutesBetween(new DateTime(controleBloqueioUsuario.getDataBloqueio()), new DateTime()).getMinutes();
				if(minutos >= 30){
					if(!logaUser()){
						controleBloqueioUsuario.setBloqueado(Boolean.FALSE);
						controleBloqueioUsuarioService.save(controleBloqueioUsuario);
						controleBloqueioUsuarioService.save(new ControleBloqueioUsuario(user));
						return "login";
					}
					controleBloqueioUsuario.setBloqueado(Boolean.FALSE);
					controleBloqueioUsuario.setLoginSucesso(Boolean.TRUE);
					controleBloqueioUsuarioService.save(controleBloqueioUsuario);
				} else {
					JSFUtil.addGlobalMessage("msg_usuario_bloqueado", FacesMessage.SEVERITY_WARN);
					return "login";
				}
			} else if(controleBloqueioUsuario.getTentativa() == 6 && !controleBloqueioUsuario.getBloqueado()){
				if(!logaUser()){
					controleBloqueioUsuario.setBloqueado(Boolean.FALSE);
					controleBloqueioUsuarioService.save(controleBloqueioUsuario);
					controleBloqueioUsuarioService.save(new ControleBloqueioUsuario(user));
					return "login";
				}
				controleBloqueioUsuario.setBloqueado(Boolean.FALSE);
				controleBloqueioUsuario.setLoginSucesso(Boolean.TRUE);
				controleBloqueioUsuarioService.save(controleBloqueioUsuario);
			} else {
				if(!logaUser()){
					Integer tentativa = controleBloqueioUsuario.getTentativa();
					controleBloqueioUsuario.setTentativa(++tentativa);
					if(controleBloqueioUsuario.getTentativa() == 6){
						controleBloqueioUsuario.setBloqueado(Boolean.TRUE);
					}
					controleBloqueioUsuarioService.save(controleBloqueioUsuario);
					return "login";
				}
				controleBloqueioUsuario.setBloqueado(Boolean.FALSE);
				controleBloqueioUsuario.setLoginSucesso(Boolean.TRUE);
				controleBloqueioUsuarioService.save(controleBloqueioUsuario);
			}
			
		}  else {
			if(!logaUser()){
				controleBloqueioUsuarioService.save(new ControleBloqueioUsuario(user));
				return "login";
			}
		}

		return selecionarPainelAcesso();
	}

	public String capsLock() {
		return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK ) ? "Caps Lock Ligado!" : "Caps Lock Desligado!" ;
	}
	
	private String selecionarPainelAcesso(){
		if(validaAlteraSenha()){
			return "/pages/moduloConfiguracao/alterarSenha.xhtml";
		}else if(AuthenticationService.getLoggedUser().hasPermission("MODULO_EXTRATO_R")){
			return  "/pages/moduloConfiguracao/painelAcesso.xhtml";
		}else if(AuthenticationService.getLoggedUser().hasPermission("MODULO_PAGAMENTO_R")){
			return  "/pages/moduloConfiguracao/painelAcesso.xhtml";
		}else return "";
	}


	private Boolean validCampos(){
		return usuario.getLogin().equals("") || usuario.getSenha().equals("") ? false : true;
	}
	
	private Boolean logaUser(){
		return authenticationService.login(usuario.getLogin(), usuario.getSenha()) ? true : false;
	}
	
	private Boolean validaAlteraSenha(){
		
		Usuario user = AuthenticationService.getLoggedUser();
		if(user.getAlteraSenha()){
			return true;
		}
		List<ControleSenha> controleSenhas = controleSenhaService.buscarUltimosRegistros(user, 1);
		if(!controleSenhas.isEmpty()){
			Integer dias = Days.daysBetween(new DateTime(controleSenhas.get(0).getData()), new DateTime()).getDays();
			if(dias >= 90){
				String msg = JSFUtil.getMessageFromBundle("aviso_altera_senha_noventa_dias").replace("{0}", dias.toString());
				JSFUtil.addGlobalMessage(msg, FacesMessage.SEVERITY_WARN);
				return true;
			}
		}
		return false;
	}

	public String logout() {
		authenticationService.logout();
		return "/pages/inicial/login.xhtml";
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public Usuario getUsuario() {
		if(usuario == null){
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setControleSenhaService(ControleSenhaService controleSenhaService) {
		this.controleSenhaService = controleSenhaService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setControleBloqueioUsuarioService(ControleBloqueioUsuarioService controleBloqueioUsuarioService) {
		this.controleBloqueioUsuarioService = controleBloqueioUsuarioService;
	}
}