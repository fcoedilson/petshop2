package br.com.petshop.seguranca.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.PermissaoIP;
import br.com.petshop.base.entity.Usuario;

@Service
public class PermissaoIPService extends BaseService<PermissaoIP> {

	@SuppressWarnings("unchecked")
	public List<PermissaoIP> findByUsuario(Usuario usuario) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT pip FROM PermissaoIP pip WHERE pip.usuario = :usuario");

			Query query = entityManager.createQuery(sql.toString());
			query.setParameter("usuario", usuario);
			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<PermissaoIP>();
		}
	}
	
	public PermissaoIP findByIp(String ip){
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT pip FROM PermissaoIP pip WHERE pip.ip = :ip");

			Query query = entityManager.createQuery(sql.toString());
			query.setParameter("ip", ip.trim());
			return (PermissaoIP) query.getSingleResult();
		} catch (NoResultException e) {
			return new PermissaoIP();
		}
	}
}