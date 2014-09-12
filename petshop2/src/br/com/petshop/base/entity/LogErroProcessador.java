package br.com.petshop.base.entity;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@Table(name = "log_erro_processador")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "log_erro_processador_id_seq")
public class LogErroProcessador extends BaseEntity {

	private static final long serialVersionUID = 6575790809888967919L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date data;

	@Column(name = "nome_arquivo", nullable = false, length = 255)
	private String nomeArquivo;

	@Column(nullable = false)
	private String erro;

	@Transient
	private File file;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}