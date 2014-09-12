package br.com.petshop.architecture.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.petshop.architecture.util.Digest;
import br.com.petshop.architecture.util.JSFUtil;
import br.com.petshop.base.entity.LogAcesso;
import br.com.petshop.base.entity.PermissaoIP;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.seguranca.service.LogAcessoService;
import br.com.petshop.seguranca.service.PermissaoIPService;

@Component("authenticationService")
public class AuthenticationService implements Serializable {

	private static final long serialVersionUID = 873131988517615130L;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PermissaoIPService permissaoIPService;
	
	@Autowired
	private LogAcessoService logAcessoService;
	
	
	public boolean login(String username, String password) {
		try {
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, Digest.MD5digest(password)));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authenticate);
				List<PermissaoIP> permissaoIPList = permissaoIPService.findByUsuario(AuthenticationService.getLoggedUser());
				if(!permissaoIPList.isEmpty()){
					if(FacesContext.getCurrentInstance() != null){
						String userIP = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
						if(!checkAcessIP(userIP, permissaoIPList)){
							JSFUtil.addGlobalMessage("usuario_sem_permissao_ip", FacesMessage.SEVERITY_WARN);
							logout();
							return false;
						}
					}
				}
				AuthenticationService.getLoggedUser().setLogAcessos(logAcessoService.buscarUltimosAcessos(AuthenticationService.getLoggedUser()));
				saveLogAcesso(AuthenticationService.getLoggedUser());
				return true;
			}
		} catch (AuthenticationException e) {
			JSFUtil.addGlobalMessage("usuario_ou_senha_errado", FacesMessage.SEVERITY_WARN);
            return false;
		}
		return false;
	}
	
	public boolean loginConciliacao(String username, String password) {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)).isAuthenticated();
		} catch (AuthenticationException e) {
            return false;
		}
	}
	
	public boolean loginInclusaoRest(String username, String password) {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, Digest.MD5digest(password))).isAuthenticated();
		} catch (AuthenticationException e) {
            return false;
		}
	}
	
	private boolean checkAcessIP(String userIP, List<PermissaoIP> permissaoIPList) {
		if(userIP.equals("0:0:0:0:0:0:0:1")){
			return true;
		}
		for(PermissaoIP pip : permissaoIPList){
			String ipMask = pip.getIp().replace(".", "[.]").replace("*", "[0-9]{1,3}");
			if(userIP.matches(ipMask)){
				return true;
			}
		}
		return false;
	}
	
	private void saveLogAcesso(Usuario usuario){
		LogAcesso logAcesso = new LogAcesso();
		logAcesso.setUsuario(usuario);
		logAcesso.setDataAcesso(new Date());
		if(FacesContext.getCurrentInstance() != null){
			logAcesso.setIp(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr());
			logAcesso.setBrowser(FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("User-Agent"));
			logAcessoService.save(logAcesso);
		}
	}

	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		invalidateSession();
	}
	
	public void changeLoggedUser(Usuario usuario){
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		AuthenticationService.getLoggedUser().setLogAcessos(logAcessoService.buscarUltimosAcessos(AuthenticationService.getLoggedUser()));
	}
	
	public static Usuario getLoggedUser(){
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public Usuario getUsuario(){
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	private void invalidateSession(){
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc == null) {
			return;
		}
		
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
	}
	
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}
}