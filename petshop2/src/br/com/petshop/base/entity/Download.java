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

import br.com.petshop.architecture.entity.BaseEntity;
import br.com.petshop.base.enumeration.TipoArquivo;

@Entity
@SequenceGenerator(name = "SEQUENCE", sequenceName = "download_id_seq")
public class Download extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "quantidade_registros")
	private Integer quantidadeRegistro;
	
	@Column(name = "data_geracao")
	private Date dataGeracao;
	
	@Column(name = "nome_arquivo")
	private String nomeArquivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Usuario usuario;
	
	@Column(name = "tipo_arquivo")
	@Enumerated(EnumType.ORDINAL)
	private TipoArquivo tipoArquivo;
	
	private Long nsa;

	public Integer getQuantidadeRegistro() {
		return quantidadeRegistro;
	}

	public void setQuantidadeRegistro(Integer quantidadeRegistro) {
		this.quantidadeRegistro = quantidadeRegistro;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoArquivo getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(TipoArquivo tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public Long getNsa() {
		return nsa;
	}

	public void setNsa(Long nsa) {
		this.nsa = nsa;
	}
}