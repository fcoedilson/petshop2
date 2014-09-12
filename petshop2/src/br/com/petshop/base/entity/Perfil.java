package br.com.petshop.base.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import br.com.petshop.architecture.entity.BaseEntity;
import br.com.petshop.architecture.service.Disabilitable;

@Entity
@SequenceGenerator(name = "SEQUENCE", sequenceName = "perfil_id_seq")
public class Perfil extends BaseEntity implements Disabilitable {

	private static final long serialVersionUID = -4575097016251699694L;

	@Column(nullable = false, length = 40)
	private String nome;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Permissao> permissoes;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "perfis")
	private List<Usuario> usuarios;
	
	@Column(nullable = false)
	private Boolean ativo;

	public Perfil() {
		super();
		this.ativo = true;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
