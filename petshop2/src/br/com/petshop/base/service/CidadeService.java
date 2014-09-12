package br.com.petshop.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.Cidade;
import br.com.petshop.base.entity.Estado;

@Service
public class CidadeService extends BaseService<Cidade> {
	
	@Transactional(readOnly = true)
	public List<Cidade> findByEstado(Estado estado){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cid FROM Cidade cid WHERE cid.estado = :estado ");
		return entityManager.createQuery(sql.toString(), Cidade.class).setParameter("estado", estado).getResultList();
	}
	
	public List<Cidade> findByNameMaisEstado(String nome, Estado estado){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cid FROM Cidade cid WHERE sem_acentos(UPPER(cid.descricao)) = sem_acentos(UPPER(:cidade)) AND cid.estado = :estado ");
		return entityManager.createQuery(sql.toString(), Cidade.class).setParameter("cidade", nome).setParameter("estado", estado).getResultList();
	}
}