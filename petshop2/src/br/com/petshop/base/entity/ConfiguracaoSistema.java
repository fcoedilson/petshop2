package br.com.petshop.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.petshop.architecture.entity.BaseEntity;


@Entity
@Table(name="configuracao_sistema")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "configuracao_sistema_id_seq")
public class ConfiguracaoSistema extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name="url_web_serv_envio_cat_conc" , nullable=false)
	private String URLWebServiceEnvioCategoriaParaConciliador;
	
	@Column
	private Boolean ativo;

	public Boolean getAtivo() {
		return ativo;
	}

	public String getURLWebServiceEnvioCategoriaParaConciliador() {
		return URLWebServiceEnvioCategoriaParaConciliador;
	}

}
