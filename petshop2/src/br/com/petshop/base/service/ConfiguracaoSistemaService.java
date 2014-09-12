package br.com.petshop.base.service;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.ConfiguracaoSistema;

@Service
public class ConfiguracaoSistemaService extends BaseService<ConfiguracaoSistema> {
		
	public ConfiguracaoSistema configuracaoAtiva(){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cs FROM ConfiguracaoSistema cs ");
		sql.append(" WHERE cs.ativo =:ativo ");
		
		Query query = entityManager.createQuery(sql.toString())
								   .setParameter("ativo", true);
		
		return (ConfiguracaoSistema) query.getSingleResult();
	}
	
}