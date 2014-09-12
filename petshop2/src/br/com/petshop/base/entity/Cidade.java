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
@Table(name = "cidade")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "cidade_id_seq")
public class Cidade extends BaseEntity {

	private static final long serialVersionUID = 1707713975552124518L;

	@Column(nullable = false, length = 30)
	private String descricao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}