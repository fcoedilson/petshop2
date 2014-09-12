package br.com.petshop.base.service;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.Estado;

@Service
public class EstadoService extends BaseService<Estado> {
	
	public Estado buscaPorCodigo(String uf){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT est ");
		sql.append("FROM Estado est ");
		sql.append("WHERE est.uf = :uf");
		
		Query query = entityManager.createQuery(sql.toString())
									.setParameter("uf", uf);
		try{
			return (Estado) query.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
}