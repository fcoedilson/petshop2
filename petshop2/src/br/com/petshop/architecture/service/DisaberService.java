package br.com.petshop.architecture.service;



public interface DisaberService{
	public <T extends Disabilitable> void disable(T entity);
	
}
