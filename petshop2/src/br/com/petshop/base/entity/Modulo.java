package br.com.petshop.base.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import br.com.petshop.architecture.entity.BaseEntity;

@Entity
@SequenceGenerator(name = "SEQUENCE", sequenceName = "modulo_id_seq")
public class Modulo extends BaseEntity {


	private static final long serialVersionUID = -6907293640568751441L;

	
}