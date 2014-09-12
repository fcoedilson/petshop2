package br.com.petshop.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@Table(name = "estado")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "estado_id_seq")
public class Estado extends BaseEntity {

	private static final long serialVersionUID = 1707713975552124518L;

	@Column(nullable = false, length = 30)
	private String descricao;
	
	@Column(nullable = false, length = 2)
	private String uf;
	
	public Estado() {
		super();
	}
	
	public Estado(Long id) {
		super();
		setId(id);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}