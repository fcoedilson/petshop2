package br.com.petshop.base.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.WordUtils;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.petshop.architecture.entity.BaseEntity;
import br.com.petshop.architecture.util.JSFUtil;

@Entity
@SequenceGenerator(name = "SEQUENCE", sequenceName = "usuario_id_seq")
public class Usuario extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -8932553654853495055L;

	@NotNull
	private String nome;

	@NotNull
	private Boolean ativo;

	@Column(unique = true)
	@NotNull
	private String login;

	@NotNull
	private String senha;

	@Column(name = "altera_senha")
	@NotNull
	private Boolean alteraSenha;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private List<Perfil> perfis;
	
	@NotNull
	private Boolean root;
	
	@NotNull
	@Email(message="Email invalido")
	private String email;

	@Transient
	private Collection<GrantedAuthority> authorities;

	@Transient
	private List<LogAcesso> logAcessos;
	
	@Transient
	private String senhaEmail;
	
	@Transient
	private ControleBloqueioUsuario ultimoBloqueio;
	
	public Usuario() {
		super();
		this.ativo = false;
		this.alteraSenha = true;
		this.root = false;
		this.perfis = new ArrayList<Perfil>();
		this.logAcessos = new ArrayList<LogAcesso>();
	}
	
	public Usuario(Long id) {
		super();
		this.alteraSenha = true;
		this.ativo = true;
		this.root = false;
		this.perfis = new ArrayList<Perfil>();
		this.logAcessos = new ArrayList<LogAcesso>();
		setId(id);
	}
	
	public Usuario(Long id, String login, String senha) {
		super();
		this.alteraSenha = true;
		this.ativo = true;
		this.root = false;
		this.perfis = new ArrayList<Perfil>();
		this.logAcessos = new ArrayList<LogAcesso>();
		setId(id);
		this.login = login;
		this.senha = senha;
	}

	public String getNome() {
		return WordUtils.capitalizeFully(nome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAlteraSenha() {
		return alteraSenha;
	}

	public void setAlteraSenha(Boolean alteraSenha) {
		this.alteraSenha = alteraSenha;
	}

	public boolean hasPermission(String chave) {
		for (GrantedAuthority grantedAuthority : getAuthorities()) {
			if (grantedAuthority.getAuthority().equals(chave)) {
				return true;
			}
		}
		return false;
	}
	
	public void setPermissoes(List<Permissao> listPermissaoUsuario) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for (Permissao permissao : listPermissaoUsuario) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permissao.getChave()));
        }
        
        this.setAuthorities(grantedAuthorities);
		
	}

	public Collection<GrantedAuthority> getAuthorities() {
		if (this.authorities == null) {
			this.authorities = new ArrayList<GrantedAuthority>();
		}
		return this.authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return this.senha;
	}

	public String getUsername() {
		return this.login;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnabled() {
		return getAtivo();
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Boolean getRoot() {
		return root;
	}

	public void setRoot(Boolean root) {
		this.root = root;
	}

	public List<LogAcesso> getLogAcessos() {
		return logAcessos;
	}

	public void setLogAcessos(List<LogAcesso> logAcessos) {
		this.logAcessos = logAcessos;
	}

	public String getEmail() {
		if(email == null){
			return email;
		}
		return email.toLowerCase();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhaEmail() {
		return senhaEmail;
	}

	public void setSenhaEmail(String senhaEmail) {
		this.senhaEmail = senhaEmail;
	}
	
	public String getUltimoAcesso(){
		if(!logAcessos.isEmpty()){
			Collections.sort(logAcessos);
			LogAcesso logAcesso = logAcessos.get(logAcessos.size() - 1);
			return JSFUtil.getMessageFromBundle("lbl_ultimo_acesso") + logAcesso.getData().toString();
		}
		return null;
	}
	
	public String getDiaDaSemana(){
		if(!logAcessos.isEmpty()){
			Collections.sort(logAcessos);
			LogAcesso logAcesso = logAcessos.get(logAcessos.size() - 1);
			return new DateTime(logAcesso.getDataAcesso()).dayOfWeek().getAsText();
		}
		return null;
	}
	
	public String getHoraAcesso(){
		if(!logAcessos.isEmpty()){
			Collections.sort(logAcessos);
			LogAcesso logAcesso = logAcessos.get(logAcessos.size() - 1);
			return logAcesso.getHora();
		}
		return null;
	}
	
	public Boolean getNaoRenderizarLogAcesso(){
		return getUltimoAcesso() == null || getDiaDaSemana() == null || getHoraAcesso() == null;
	}

	public ControleBloqueioUsuario getUltimoBloqueio() {
		return ultimoBloqueio;
	}

	public void setUltimoBloqueio(ControleBloqueioUsuario ultimoBloqueio) {
		this.ultimoBloqueio = ultimoBloqueio;
	}
	
	public String getDataBloqueio(){
		if(getUltimoBloqueio() == null){
			return "";
		}
		return new DateTime(getUltimoBloqueio().getDataBloqueio()).toString("dd/MM/yyyy");
	}
	
	public String getHoraBloqueio(){
		if(getUltimoBloqueio() == null){
			return "";
		}
		return new DateTime(getUltimoBloqueio().getDataBloqueio()).toString("HH:mm");
	}
	
	public String getTentativaBloqueio(){
		if(getUltimoBloqueio() == null){
			return "";
		}
		return getUltimoBloqueio().getTentativa().toString();
	}


}