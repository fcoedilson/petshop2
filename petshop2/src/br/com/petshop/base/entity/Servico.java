package br.com.petshop.base.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@Table(name = "estado")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "estado_id_seq")

public class Servico {
	
	

}
