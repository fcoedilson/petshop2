package br.com.petshop.base.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@SequenceGenerator(name = "SEQUENCE", sequenceName = "grupo_permissao_id_seq")
public class GrupoPermissao extends BaseEntity {

	private static final long serialVersionUID = 2909143488961963990L;
	
	@NotNull
	@Size(max=40)
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoPermissao", fetch = FetchType.LAZY)
	private List<Permissao> permissoes;
	
	public GrupoPermissao() {
		super();
		this.permissoes = new ArrayList<Permissao>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
}
