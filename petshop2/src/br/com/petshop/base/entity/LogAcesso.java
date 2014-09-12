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
@Table(name = "log_acesso")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "log_acesso_id_seq")
public class LogAcesso extends BaseEntity implements Comparable<LogAcesso> {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Usuario usuario;
	
	@Column(name = "data_acesso", nullable=false)
	private Date dataAcesso;
	
	@Column(nullable=false, length=80)
	private String ip;
	
	@Column(nullable=false, length=250)
	private String browser;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}
	
	public String getData(){
		return new DateTime(dataAcesso).toString("dd/MM/yyyy");
	}
	
	public String getHora(){
		return new DateTime(dataAcesso).toString("HH:mm");
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
	public int compareTo(LogAcesso o) {
		return getId().compareTo(o.getId().longValue());
	}
}