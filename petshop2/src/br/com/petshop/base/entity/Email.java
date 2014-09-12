package br.com.petshop.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.petshop.architecture.entity.BaseEntity;
import br.com.petshop.base.enumeration.TipoEmail;

@Entity
@SequenceGenerator(name = "SEQUENCE", sequenceName = "email_id_seq")
public class Email extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "data_envio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvio;

	@NotNull
	@Length(max = 255)
	private String titulo;

	@NotNull
	private String corpo;

	@NotNull
	@Length(max = 255)
	private String destinatario;

	@NotNull
	@Column(name = "tipo_email")
	@Enumerated(EnumType.ORDINAL)
	private TipoEmail tipoEmail;

	@NotNull
	private Boolean enviado;
	
	@NotNull
	private Integer reenviado;
	
	public Email() {
		super();
		reenviado = 0;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public TipoEmail getTipoEmail() {
		return tipoEmail;
	}

	public void setTipoEmail(TipoEmail tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public Integer getReenviado() {
		return reenviado;
	}

	public void setReenviado(Integer reenviado) {
		this.reenviado = reenviado;
	}

}