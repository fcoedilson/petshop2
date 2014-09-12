package br.com.petshop.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@Table(name = "controle_bloqueio_usuario")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "controle_bloqueio_usuario_id_seq")
public class ControleBloqueioUsuario extends BaseEntity {

	private static final long serialVersionUID = 665358985968271626L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@Column(nullable = false)
	private Integer tentativa;

	@Column(name = "data_bloqueio", nullable = false)
	private Date dataBloqueio;

	@Column(nullable = false)
	private Boolean bloqueado;
	
	@Column(name="login_sucesso")
	private Boolean loginSucesso;
	
	public ControleBloqueioUsuario() {
		super();
	}
	
	public ControleBloqueioUsuario(Usuario usuario) {
		super();
		this.usuario = usuario;
		this.dataBloqueio = new DateTime().toDate();
		this.bloqueado = Boolean.FALSE;
		this.tentativa = 1;
		this.loginSucesso = Boolean.FALSE;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getTentativa() {
		return tentativa;
	}

	public void setTentativa(Integer tentativa) {
		this.tentativa = tentativa;
	}

	public Date getDataBloqueio() {
		return dataBloqueio;
	}

	public void setDataBloqueio(Date dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Boolean getLoginSucesso() {
		return loginSucesso;
	}

	public void setLoginSucesso(Boolean loginSucesso) {
		this.loginSucesso = loginSucesso;
	}
}