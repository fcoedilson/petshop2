package br.com.petshop.cadastro.service;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.Feriado;

@Service
public class FeriadoService extends BaseService<Feriado>{
	
	@SuppressWarnings("unchecked")
	public List<Feriado> findByFilter(Map<String, Object> filter, Integer first, Integer rows) {
		
		String descricao = (String)filter.get("descricao");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT fe FROM Feriado fe ");
		sql.append(descricao != null ? " WHERE sem_acentos(UPPER(fe.descricao)) LIKE sem_acentos(UPPER(:descricao))" : "");
		sql.append(" ORDER BY fe.ano DESC");
		
		Query query = entityManager.createQuery(sql.toString());

		if(descricao != null){
			query.setParameter("descricao", "%" + filter.get("descricao").toString() + "%");
		}
		
		return query.getResultList();
	}
	
	public boolean diaEUtil(DateTime data){
		
		if((DateTimeConstants.SATURDAY == data.getDayOfWeek()) || (DateTimeConstants.SUNDAY == data.getDayOfWeek())){
			return false;
		}
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(fer.id) FROM Feriado fer ");
		query.append("WHERE fer.dia = :dia ");
		query.append("AND fer.mes = :mes ");
		query.append("AND (fer.ano IS NULL ");
		query.append("OR fer.ano = :ano) ");
		
		return ((Number)entityManager.createQuery(query.toString())
									 .setParameter("dia", data.getDayOfMonth())
									 .setParameter("mes", data.getMonthOfYear())
									 .setParameter("ano", data.getYear())
									 .getSingleResult())
									 .longValue() == 0L;
		
	}
	
}
