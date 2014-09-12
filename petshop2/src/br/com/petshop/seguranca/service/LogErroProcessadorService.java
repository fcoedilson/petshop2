package br.com.petshop.seguranca.service;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.LogErroProcessador;
import br.com.petshop.base.entity.Usuario;

@Service
public class LogErroProcessadorService extends BaseService<LogErroProcessador> {


	@SuppressWarnings("unchecked")
	public List<LogErroProcessador> findByFilter(Map<String, Object> filter, Integer first, Integer rows) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT usu FROM LogErroProcessador usu ");
		return getQuery(sql, filter, first, rows).getResultList();
	}
	
	public Integer countByFilter(Map<String, Object> filter) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(usu) FROM LogErroProcessador usu ");
		return ((Number)getQuery(sql, filter, null, null).getSingleResult()).intValue();
	}

	private Query getQuery(StringBuilder sql, Map<String, Object> filter, Integer first, Integer rows){
		
		Query query = entityManager.createQuery(sql.toString());
		return query;
		
	}
}
