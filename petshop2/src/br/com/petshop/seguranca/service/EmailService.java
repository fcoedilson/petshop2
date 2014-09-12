package br.com.petshop.seguranca.service;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.Email;

@Component
public class EmailService extends BaseService<Email> {
	
	public static final String ENVIADO = "enviado";
	public static final String REENVIO = "reenvio";
	
	@SuppressWarnings("unchecked")
	public List<Email> findByFilter(Map<String, Object> filter, Integer first, Integer rows) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT email FROM Email email ");
		sql = getRestricao(sql, filter);
		sql.append(" ORDER BY email.id DESC ");
		return getQuery(sql, filter, first, rows).getResultList();
	}
	
	public Integer countByFilter(Map<String, Object> filter) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(email) FROM Email email ");
		sql = getRestricao(sql, filter);
		return ((Number) getQuery(sql, filter, null, null).getSingleResult()).intValue();
	}
	
	private StringBuilder getRestricao(StringBuilder sql, Map<String, Object> filter){
		sql.append(" WHERE 1 = 1 ");
		sql.append(isDataNaoNulo(filter) ? " AND email.dataEnvio BETWEEN :dataInicial AND :dataFinal " : "");
		sql.append(checkIsNotNull(getStatus(filter)) ? " AND email.enviado = :enviado" : "");
		sql.append(checkIsNotNull(getReenvio(filter)) && getReenvio(filter)? " AND email.reenviado <> 0" : "");
		sql.append(checkIsNotNull(getReenvio(filter)) && !getReenvio(filter)? " AND email.reenviado = 0" : "");
		return sql;
	}
	
	private Query getQuery(StringBuilder sql, Map<String, Object> filter, Integer first, Integer rows){
		
		Query query = entityManager.createQuery(sql.toString());

		if(first != null && rows != null){
			query.setFirstResult(first);
			query.setMaxResults(rows);
		}
		
		if(checkIsNotNull(getStatus(filter))){
			query.setParameter(ENVIADO, getStatus(filter));
		}
		
		if(isDataNaoNulo(filter)){
			query.setParameter(DATA_INICIAL, new DateMidnight(filter.get(DATA_INICIAL)).toDate());
			query.setParameter(DATA_FINAL, new DateTime(filter.get(DATA_FINAL)).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toDate());
		}
		
		return query;
	}
	
	public Long countAllFalse() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(email) FROM Email email ");
		sql.append("WHERE enviado = false ");
		return ((Number)entityManager.createQuery(sql.toString()).getSingleResult()).longValue();
	}
	
	private Boolean getStatus(Map<String, Object> filter){
		if(!filter.get(STATUS).equals("0")){
			if(Boolean.parseBoolean(filter.get(STATUS).toString())){
				return true;
			}
			return false;
		}
		return null;
	}
	
	private Boolean getReenvio(Map<String, Object> filter){
		if(!filter.get(REENVIO).equals("0")){
			if(Boolean.parseBoolean(filter.get(REENVIO).toString())){
				return true;
			}
			return false;
		}
		return null;
	}
	
	private Boolean isDataNaoNulo(Map<String, Object> filter){
		return filter.get(DATA_INICIAL) != null && filter.get(DATA_FINAL) != null;
	}
}