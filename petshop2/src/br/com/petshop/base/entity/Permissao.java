package br.com.petshop.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@SequenceGenerator(name = "SEQUENCE", sequenceName = "permissao_id_seq")
public class Permissao extends BaseEntity {
	
	private static final long serialVersionUID = 5780200585136873750L;

	@Column(nullable = false, length = 80)
	private String chave;
	
	@Column(nullable = false, length = 80)
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private GrupoPermissao grupoPermissao;
	
	@Transient
	private Boolean checado;

	public Permissao() {
		super();
	}

	public Permissao(String chave) {
		this();
		this.chave = chave;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GrupoPermissao getGrupoPermissao() {
		return grupoPermissao;
	}

	public void setGrupoPermissao(GrupoPermissao grupoPermissao) {
		this.grupoPermissao = grupoPermissao;
	}

	public Boolean getChecado() {
		return checado;
	}

	public void setChecado(Boolean checado) {
		this.checado = checado;
	}
	
	public Permissao duplicate() {
		Permissao permissao = new Permissao();
		permissao.setChave(this.chave);
		permissao.setChecado(this.checado);
		permissao.setDescricao(this.descricao);
		permissao.setGrupoPermissao(this.grupoPermissao);
		permissao.setId(getId());
		return permissao;
	}
}
