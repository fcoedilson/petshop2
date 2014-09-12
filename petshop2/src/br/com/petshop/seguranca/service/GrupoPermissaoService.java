package br.com.petshop.seguranca.service;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petshop.architecture.entity.BaseEntityComparator;
import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.GrupoPermissao;
import br.com.petshop.base.entity.Perfil;
import br.com.petshop.base.entity.Permissao;

@Service
public class GrupoPermissaoService extends BaseService<GrupoPermissao> {
	
	@Override
	@Transactional
	public GrupoPermissao save(GrupoPermissao grupoPermissao) {
		resetIdNegativo(grupoPermissao.getPermissoes());
		return super.save(grupoPermissao);
	}
	
	private void resetIdNegativo(List<Permissao> permissoes) {
		for (Permissao permissao : permissoes) {
			if(permissao.getId() < 0){
				permissao.setId(null);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoPermissao> findAllWithPermissoes(Perfil perfil) {
		
		StringBuilder jpQL = new StringBuilder();
		jpQL.append("SELECT DISTINCT grp FROM GrupoPermissao grp ");
		jpQL.append("LEFT JOIN FETCH grp.permissoes per ");
		jpQL.append("ORDER BY grp.nome ASC");
		
		List<GrupoPermissao> listGrupoPermissao = entityManager.createQuery(jpQL.toString())
															   .getResultList();
		if(BaseEntityComparator.isEmpty(perfil)){
			return listGrupoPermissao;
		}
		
		for (GrupoPermissao grupoPermissao : listGrupoPermissao) {
			for (Permissao permissao : grupoPermissao.getPermissoes()) {
				if(perfil.getPermissoes().contains(permissao)){
					permissao.setChecado(true);
				}
			}
		}
		
		return listGrupoPermissao;
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoPermissao> findByFilter(Map<String, Object> filter, Integer first, Integer rows) {
		
		String nome = (String) filter.get("nome");
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT gp FROM GrupoPermissao gp ");
		sql.append("LEFT JOIN FETCH gp.permissoes ");
		sql.append("WHERE 1 = 1 ");
		
		if(StringUtils.isNotBlank(nome)){
			sql.append("AND UPPER(gp.nome) LIKE UPPER(:nome) ");
		}
		
		sql.append("ORDER BY gp.nome ASC ");
		
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
		sql.append("SELECT COUNT(gp) FROM GrupoPermissao gp ");
		sql.append("WHERE 1 = 1 ");

		if(StringUtils.isNotBlank(nome)){
			sql.append("AND UPPER(gp.nome) LIKE UPPER(:nome) ");
		}

		Query query = entityManager.createQuery(sql.toString());

		if(StringUtils.isNotBlank(nome)){
			query.setParameter("nome", "%" + nome + "%");
		}

		return ((Number)query.getSingleResult()).intValue();
	}

}
