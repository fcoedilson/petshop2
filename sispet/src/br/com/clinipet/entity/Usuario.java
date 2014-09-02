package br.com.clinipet.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.clinipet.type.StatusUsuario;

@Entity
@DiscriminatorValue(value="U")
@PrimaryKeyJoinColumn(name="pessoa_id")
public class Usuario extends Pessoa{

	@NotNull(message="login não poder ser nullo")
	@Length(min=5, message="login deve ter pelos menos 5 caracteres")
	private String login;
	
	@NotNull(message="senha não poder ser nullo")
	@Length(min=6, message="senha deve ter pelos menos 6 caracteres")
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="status não pode ficar em branco")
	private StatusUsuario status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new Date();

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}
	
	
}
