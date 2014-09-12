package br.com.petshop.cadastro.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.primefaces.model.DualListModel;

import br.com.petshop.architecture.actions.ActionRemove;
import br.com.petshop.architecture.actions.ActionSave;
import br.com.petshop.architecture.actions.ActionSearch;
import br.com.petshop.architecture.actions.DefaultActionRemove;
import br.com.petshop.architecture.modules.AbstractFacadeBean;
import br.com.petshop.architecture.service.AuthenticationService;
import br.com.petshop.architecture.util.Digest;
import br.com.petshop.architecture.util.JSFUtil;
import br.com.petshop.base.config.Mail;
import br.com.petshop.base.entity.ControleSenha;
import br.com.petshop.base.entity.Email;
import br.com.petshop.base.entity.Perfil;
import br.com.petshop.base.entity.PermissaoIP;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.base.enumeration.TipoEmail;
import br.com.petshop.base.service.ControleBloqueioUsuarioService;
import br.com.petshop.base.service.ControleSenhaService;
import br.com.petshop.cadastro.action.UsuarioActionSave;
import br.com.petshop.cadastro.action.UsuarioActionSearch;
import br.com.petshop.cadastro.service.UsuarioService;
import br.com.petshop.cadastro.util.UsuarioUtil;
import br.com.petshop.seguranca.service.PerfilService;
import br.com.petshop.seguranca.service.PermissaoIPService;

@ManagedBean
@ViewScoped
public class UsuarioBean extends AbstractFacadeBean<Usuario>{

	private static final long serialVersionUID = -2230000154974560853L;

	@ManagedProperty(value="#{perfilService}")
	private PerfilService perfilService;
	
	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService usuarioService;

	@ManagedProperty(value="#{permissaoIPService}")
	private PermissaoIPService permissaoIPService;
	
	@ManagedProperty(value="#{controleSenhaService}")
	private ControleSenhaService controleSenhaService;
	
	@ManagedProperty(value="#{mail}")
	private Mail mail;
	
	@ManagedProperty(value="#{controleBloqueioUsuarioService}")
	private ControleBloqueioUsuarioService controleBloqueioUsuarioService; 
	
	@Override
	protected String getPageTitle() {
		return "titulo_usuario";
	}
	
	@Override
	protected Class<Usuario> getDefaultEntityClass() {
		return Usuario.class;
	}
	
	@Override
	public void initBean(){
		getFilter().put(ATIVO, UsuarioService.NULL);
		getBeanProperties().put(UsuarioService.LIST_IP, new ArrayList<PermissaoIP>());
		getBeanProperties().put(UsuarioService.IP, null);
		getBeanProperties().put(HABILITA_GERA_SENHA, true);
	}
	
	public void beforeSave(){
		if(getDefaultEntity().isNew()){
			Usuario loggedUser = AuthenticationService.getLoggedUser();
			getDefaultEntity().setSenha(Digest.MD5digest(String.valueOf(new DateTime().getMillisOfSecond())));
			setDefaultEntity(UsuarioUtil.gerarSenhaUsuario(getDefaultEntity()));
		}
		
	}
	
	public void afterSave(){
		controleSenhaService.save(persistirControleSenha(getDefaultEntity()));
		enviarEmail(getDefaultEntity());
	}
	
	public void afterPrepareInsert() {
		setDefaultEntity(new Usuario());
		setListaPerfil();
	}
	
	public void afterPrepareEdit() {
		setListaPerfil();
		getBeanProperties().put(UsuarioService.LIST_IP, permissaoIPService.findByUsuario(getDefaultEntity()));
		getBeanProperties().put(HABILITA_GERA_SENHA, true);
	}
	

	private void setListaPerfil() {
		List<Perfil> perfisDisponivel = perfilService.findAllAtivo();
		perfisDisponivel.removeAll(getDefaultEntity().getPerfis());
		DualListModel<Perfil> listaPerfil = new DualListModel<Perfil>(perfisDisponivel, getDefaultEntity().getPerfis());
		getBeanProperties().put(UsuarioService.LISTA_PERFIL, listaPerfil);
	}
	
	@SuppressWarnings("unchecked")
	public void adcionaIP(){
		List<PermissaoIP> listIP = (List<PermissaoIP>) getBeanProperties().get(UsuarioService.LIST_IP);
		String ip = getBeanProperties().get(UsuarioService.IP).toString();
		if(!isCadastrado(ip, listIP)){
			PermissaoIP permissaoIP = new PermissaoIP(ip, getDefaultEntity());
			permissaoIPService.save(permissaoIP);
			listIP.add(permissaoIP);
			getBeanProperties().put(UsuarioService.LIST_IP, listIP);
			getBeanProperties().put(UsuarioService.IP, null);
		} else {
			JSFUtil.addGlobalMessage(JSFUtil.getMessageFromBundle(UsuarioService.IP_REGISTRADO), FacesMessage.SEVERITY_WARN);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void removeIP(PermissaoIP ip){
		List<PermissaoIP> listIP = (List<PermissaoIP>) getBeanProperties().get(UsuarioService.LIST_IP);
		listIP.remove(ip);
		permissaoIPService.remove(ip);
		getBeanProperties().put(UsuarioService.LIST_IP, listIP);
	}
	
	private boolean isCadastrado(String ip, List<PermissaoIP> listIP){
		for(PermissaoIP permissaoIP : listIP){
			if(permissaoIP.getIp().equals(ip)){
				return true;
			}
		}
		return false;
	}
	
	private ControleSenha persistirControleSenha(Usuario usuario){
		ControleSenha controleSenha = new ControleSenha();
		controleSenha.setSenha(usuario.getSenha());
		controleSenha.setData(new DateTime().toDate());
		controleSenha.setUsuario(usuario);
		return controleSenha;
	}
	
	public void gerarSenha(){
		setDefaultEntity(UsuarioUtil.gerarSenhaUsuario(getDefaultEntity()));
		enviarEmail(getDefaultEntity());
		usuarioService.save(getDefaultEntity());
	}
	
	private void enviarEmail(Usuario usuario){
		if(JSFUtil.facesContextIsNull()){
			return;
		}
		if(StringUtils.isBlank(usuario.getSenhaEmail())){
			usuario.setSenhaEmail("");
		}
		Email email = new Email();
		email.setTitulo(JSFUtil.getMessageFromBundle("titulo_email_gera_senha"));
		email.setCorpo(JSFUtil.getMessageFromBundle("corpo_email_gera_senha").replace("{0}",  usuario.getNome()).replace("{1}", usuario.getSenhaEmail()).replace("{2}", usuario.getLogin()));
		email.setDataEnvio(new DateTime().toDate());
		email.setDestinatario(usuario.getEmail());
		email.setTipoEmail(TipoEmail.EMAIL_GERA_SENHA);
		email.setEnviado(true);
		mail.enviarEmail(email);
		JSFUtil.addGlobalMessage("msg_email_enviado", FacesMessage.SEVERITY_INFO);
		getBeanProperties().put(HABILITA_GERA_SENHA, false);
	}
	
	public void buscarUsuario(Usuario usuario){
		getBeanProperties().put("ultimosBloqueios", controleBloqueioUsuarioService.buscarPorUsuario(usuario));
	}
	
	public void desbloquearUsuario(){
		getDefaultEntity().getUltimoBloqueio().setBloqueado(Boolean.FALSE);
		controleBloqueioUsuarioService.save(getDefaultEntity().getUltimoBloqueio());
		JSFUtil.addGlobalMessage("msg_usuario_desbloqueado", FacesMessage.SEVERITY_INFO);
	}
	
	protected ActionSearch<Usuario> searchAction() {
		return new UsuarioActionSearch(usuarioService);
	}

	protected ActionSave<Usuario> saveAction() {
		return new UsuarioActionSave(usuarioService);
	}
	
	public ActionRemove<Usuario> removeAction() {
		return new DefaultActionRemove<Usuario>(usuarioService);
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	public void setPermissaoIPService(PermissaoIPService permissaoIPService) {
		this.permissaoIPService = permissaoIPService;
	}

	public void setControleSenhaService(ControleSenhaService controleSenhaService) {
		this.controleSenhaService = controleSenhaService;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public void setControleBloqueioUsuarioService(ControleBloqueioUsuarioService controleBloqueioUsuarioService) {
		this.controleBloqueioUsuarioService = controleBloqueioUsuarioService;
	}


	
}