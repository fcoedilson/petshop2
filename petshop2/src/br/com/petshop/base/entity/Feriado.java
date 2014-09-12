package br.com.petshop.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@Table(name = "feriado")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "feriado_id_seq")
public class Feriado extends BaseEntity {
	
	private static final long serialVersionUID = 853787830876480431L;
	
	@Column(nullable = false)
	private Integer dia;
	
	@Column(nullable = false)
	private Integer mes;
	
	private Integer ano;
	
	@Column(length = 255, nullable = false)
	private String descricao;

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}