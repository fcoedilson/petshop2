package br.com.petshop.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@Table(name = "faixa_cep")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "faixa_cep_id_seq")
public class FaixaCep extends BaseEntity {

	private static final long serialVersionUID = 6266573817413974352L;

	@Column(name = "faixa_inicial", nullable = false)
	private String faixaInicial;
	
	@Column(name = "faixa_final", nullable = false)
	private String faixaFinal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

	public String getFaixaInicial() {
		return faixaInicial;
	}

	public void setFaixaInicial(String faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	public String getFaixaFinal() {
		return faixaFinal;
	}

	public void setFaixaFinal(String faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}	
}
