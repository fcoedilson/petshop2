package br.com.petshop.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@Table(name = "permissao_ip")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "permissao_ip_seq")
public class PermissaoIP extends BaseEntity{
	private static final long serialVersionUID = -8024020201512827465L;
	
	@Column(length=30)
	private String ip;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public PermissaoIP() {
		super();
	}
	
	public PermissaoIP(String ip, Usuario usuario) {
		super();
		this.ip = ip;
		this.usuario = usuario;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}