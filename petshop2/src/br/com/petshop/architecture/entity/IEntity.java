package br.com.petshop.architecture.entity;

public interface IEntity {

	public Long getId();

	public void setId(Long id);

	public boolean isNew();

	public int hashCode();

	public boolean equals(Object object);

}
