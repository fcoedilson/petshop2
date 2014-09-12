package br.com.petshop.base.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.ControleBloqueioUsuario;
import br.com.petshop.base.entity.Usuario;

@Service
public class ControleBloqueioUsuarioService extends BaseService<ControleBloqueioUsuario>{
	
	public ControleBloqueioUsuario buscarControleBloqueioUsuario(Usuario usuario){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cbu FROM ControleBloqueioUsuario cbu ");
		sql.append(" WHERE cbu.usuario = :usuario ");
		sql.append(" ORDER BY id DESC ");
		
		try{
			return (ControleBloqueioUsuario) entityManager.createQuery(sql.toString())
														  .setParameter("usuario", usuario)
														  .setMaxResults(1)
														  .getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
	
	public List<ControleBloqueioUsuario> buscarPorUsuario(Usuario usuario){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cbu FROM ControleBloqueioUsuario cbu ");
		sql.append(" WHERE cbu.usuario = :usuario ");
		sql.append(" ORDER BY id DESC ");
		return entityManager.createQuery(sql.toString(), ControleBloqueioUsuario.class)
							.setParameter("usuario", usuario)
							.setMaxResults(10)
							.getResultList();
	}
}