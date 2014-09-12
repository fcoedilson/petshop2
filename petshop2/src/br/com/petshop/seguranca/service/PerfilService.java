package br.com.petshop.seguranca.service;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.Perfil;

@Service
public class PerfilService extends BaseService<Perfil> {
	
	@SuppressWarnings("unchecked")
	public List<Perfil> findByFilter(Map<String, Object> filter, Integer first, Integer rows) {
		
		String nome = (String) filter.get("nome");
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT per FROM Perfil per ");
		sql.append("LEFT JOIN FETCH per.permissoes ");
		sql.append("WHERE 1 = 1 ");
		
		if(StringUtils.isNotBlank(nome)){
			sql.append("AND UPPER(per.nome) LIKE UPPER(:nome) ");
		}
		
		sql.append("ORDER BY per.nome ASC ");
		
		Query query = entityManager.createQuery(sql.toString());
		
		if(StringUtils.isNotBlank(nome)){
			query.setParameter("nome", "%" + nome + "%");
		}
		
		if(first != null){
			query.setFirstResult(first);
		}
		
		if(rows != null){
			query.setMaxResults(rows);
		}

		return query.getResultList();
	}
	
	public Integer countByFilter(Map<String, Object> filter) {
		String nome = (String) filter.get("nome");

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(per) FROM Perfil per ");
		sql.append("WHERE 1 = 1 ");

		if(StringUtils.isNotBlank(nome)){
			sql.append("AND UPPER(per.nome) LIKE UPPER(:nome) ");
		}

		Query query = entityManager.createQuery(sql.toString());

		if(StringUtils.isNotBlank(nome)){
			query.setParameter("nome", "%" + nome + "%");
		}

		return ((Number)query.getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Perfil> findAllAtivo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT per FROM Perfil per ");
		sql.append("WHERE per.ativo = :ativo ");
		sql.append("ORDER BY per.nome ASC ");
		
		return  entityManager.createQuery(sql.toString())
							 .setParameter("ativo", true)
							 .getResultList();
	}

}
