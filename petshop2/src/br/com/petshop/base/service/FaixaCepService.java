package br.com.petshop.base.service;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.FaixaCep;

@Service
public class FaixaCepService extends BaseService<FaixaCep>{

	public FaixaCep buscarPorFaixaCep(String faixaCep) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT fc ");
		sql.append(" FROM FaixaCep fc ");
		sql.append(" INNER JOIN FETCH fc.estado est ");
		sql.append(" WHERE fc.faixaInicial <= :faixaCep ");
		sql.append(" AND fc.faixaFinal >= :faixaCep ");
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter("faixaCep", faixaCep);
		return  (FaixaCep) query.getSingleResult();
	}	
}
