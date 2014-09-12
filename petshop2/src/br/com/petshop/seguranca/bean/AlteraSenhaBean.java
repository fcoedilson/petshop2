package br.com.petshop.seguranca.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;

import br.com.petshop.architecture.modules.AbstractFacadeBean;
import br.com.petshop.architecture.modules.DefaultStateBean;
import br.com.petshop.architecture.service.AuthenticationService;
import br.com.petshop.architecture.util.Digest;
import br.com.petshop.architecture.util.JSFUtil;
import br.com.petshop.base.entity.ControleSenha;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.base.exception.PetShopSystemException;
import br.com.petshop.base.service.ControleSenhaService;
import br.com.petshop.cadastro.service.UsuarioService;
import br.com.petshop.cadastro.util.UsuarioUtil;

@ManagedBean
@ViewScoped
public class AlteraSenhaBean extends AbstractFacadeBean<Usuario>{
	
	public static final String ERRO_SENHA_ATUAL = "erroSenhaAtual";
	public static final String ERRO_SENHAS_IGUAIS = "erroSenhasIguais";
	public static final String ERRO_ULTIMAS_SENHAS = "erroUltimasSenhas";
	public static final String ERRO_LETRAS_NUMEROS = "erroLetrasNumeros";
	public static final String ERRO_SETE_CARACTERES = "erroSeteCaracteres";

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService usuarioService;
	
	@ManagedProperty(value="#{controleSenhaService}")
	private ControleSenhaService controleSenhaService;
	
	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService;
	
	private Map<String, Object> mapErros = new HashMap<String, Object>();
	
	public boolean isSearching() {
		return false;
	}
	
	public boolean isUpdating() {
		return false;
	}

	protected String getPageTitle() {
		return "titulo_alterar_senha_usuario";
	}

	protected Class<Usuario> getDefaultEntityClass() {
		return Usuario.class;
	}
	
	public void initBean(){
		stateBean.setCurrentState(DefaultStateBean.STATE_EDIT);
	}
	
	public String atualizaSenha(){
		String senhaNova = Digest.MD5digest(getBeanProperties().get(UsuarioService.SENHA_NOVA).toString());
		Usuario usuario = AuthenticationService.getLoggedUser();
		validar();
		if(comErros()){
			return null;
		}
		try{
			if(usuarioService.alterarSenha(AuthenticationService.getLoggedUser(), senhaNova, false)){
				AuthenticationService.getLoggedUser().setSenha(senhaNova);
				AuthenticationService.getLoggedUser().setAlteraSenha(false);
				getBeanProperties().clear();
				controleSenhaService.save(persistirControleSenha(senhaNova, usuario));
				JSFUtil.addGlobalMessage("senha_alterada_sucesso", FacesMessage.SEVERITY_INFO);
				authenticationService.logout();
				return "/pages/inicial/login.xhtml";
			}
			JSFUtil.addGlobalMessage("erro_encriptar_senha", FacesMessage.SEVERITY_ERROR);
		} catch (PetShopSystemException e){
			e.printStackTrace();
			JSFUtil.addGlobalMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		} catch (Exception e){
			e.printStackTrace();
			JSFUtil.addGlobalMessage("error_excessao", FacesMessage.SEVERITY_WARN);
		}
		return null;
	}
	
	private void validar(){
		String senhaNova = Digest.MD5digest(getBeanProperties().get(UsuarioService.SENHA_NOVA).toString());
		mapErros.put(ERRO_SENHA_ATUAL, usuarioService.validarSenha(AuthenticationService.getLoggedUser(), Digest.MD5digest(getBeanProperties().get(UsuarioService.SENHA).toString())));
		mapErros.put(ERRO_ULTIMAS_SENHAS, validarUltimasSenhas(AuthenticationService.getLoggedUser(), senhaNova));
		mapErros.put(ERRO_SETE_CARACTERES, UsuarioUtil.validarSenhaSeteCaracteres(getBeanProperties().get(UsuarioService.SENHA_NOVA).toString()));
		mapErros.put(ERRO_LETRAS_NUMEROS, UsuarioUtil.validarLetrasNumeros(getBeanProperties().get(UsuarioService.SENHA_NOVA).toString()));
		mapErros.put(ERRO_SENHAS_IGUAIS, UsuarioUtil.validarSenhasIguais(getBeanProperties().get(UsuarioService.SENHA_NOVA).toString(), getBeanProperties().get(UsuarioService.SENHA_NOVA_CONFIRMA).toString()));
	}
	
	private Boolean validarUltimasSenhas(Usuario usuario, String senha){
		List<ControleSenha> controleSenhas = controleSenhaService.buscarUltimosRegistros(usuario, 4);
		for(ControleSenha controleSenha : controleSenhas){
			if(controleSenha.getSenha().equals(senha)){
				return false;
			}
		}
		return true;
	}
	
	private ControleSenha persistirControleSenha(String senha, Usuario usuario){
		ControleSenha controleSenha = new ControleSenha();
		controleSenha.setSenha(senha);
		controleSenha.setData(new DateTime().toDate());
		controleSenha.setUsuario(usuario);
		return controleSenha;
	}
	
	private Boolean comErros(){
		Boolean erro = false;
		for(String str : mapErros.keySet()){
			if(!(Boolean) mapErros.get(str)){
				erro = true;
			}
		}
		return erro;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public void setControleSenhaService(ControleSenhaService controleSenhaService) {
		this.controleSenhaService = controleSenhaService;
	}

	public Map<String, Object> getMapErros() {
		return mapErros;
	}

	public void setMapErros(Map<String, Object> mapErros) {
		this.mapErros = mapErros;
	}
}