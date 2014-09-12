package br.com.petshop.base.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.ControleSenha;
import br.com.petshop.base.entity.Usuario;

@Service
public class ControleSenhaService extends BaseService<ControleSenha> {
	
	@SuppressWarnings("unchecked")
	public List<ControleSenha> buscarUltimosRegistros(Usuario usuario, Integer limite){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cs FROM ControleSenha cs ");
		sql.append(" WHERE cs.usuario = :usuario ");
		sql.append(" ORDER BY id DESC ");
		
		try{
			return entityManager.createQuery(sql.toString())
					            .setParameter("usuario", usuario)
					            .setMaxResults(limite)
					            .getResultList();
		} catch (NoResultException e){
			return null;
		}
	}
}