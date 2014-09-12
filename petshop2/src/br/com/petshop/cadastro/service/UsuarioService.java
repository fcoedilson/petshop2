package br.com.petshop.cadastro.service;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.ControleBloqueioUsuario;
import br.com.petshop.base.entity.Permissao;
import br.com.petshop.base.entity.Usuario;

@Service
public class UsuarioService extends BaseService<Usuario>{
	
	public static final String IP = "ip";
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String NULL = "null";
	public static final String LOGIN = "login";
	public static final String SENHA = "senha";
	public static final String LIST_IP = "listIP";
	public static final String SENHA_NOVA = "senhaNova";
	public static final String USUARIO_ALL = "USUARIO_ALL";
	public static final String LISTA_PERFIL = "listaPerfil";
	public static final String ALTERA_SENHA = "alteraSenha";
	public static final String IP_REGISTRADO = "ip_registrado";
	public static final String SENHA_NOVA_CONFIRMA = "senhaNovaConfirma";
	
	public Usuario buscarPorUsuario(Usuario usuario){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT usu FROM Usuario usu ");
		sql.append(" LEFT JOIN FETCH usu.perfis per ");
		sql.append(" WHERE usu = :usuario  ");
		
		return (Usuario) entityManager.createQuery(sql.toString())
									  .setParameter(USUARIO, usuario)
									  .getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findByFilter(Map<String, Object> filter, Integer first, Integer rows) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT usu FROM Usuario usu ");
		sql.append(" LEFT JOIN FETCH usu.perfis per ");
		sql = getRestricao(sql, filter);
		return getQuery(sql, filter, first, rows).getResultList();
	}
	
	public Integer countByFilter(Map<String, Object> filter) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(usu) FROM Usuario usu ");
		sql = getRestricao(sql, filter);
		return ((Number)getQuery(sql, filter, null, null).getSingleResult()).intValue();
	}
	
	private StringBuilder getRestricao(StringBuilder sql, Map<String, Object> filter){
		
		Object ativo = filter.get(ATIVO);
		String login = (String) filter.get(LOGIN);
		String nome = (String) filter.get(NOME);
	
		sql.append(" WHERE 1 = 1 ");
		sql.append(!ativo.equals(NULL) ? " AND usu.ativo = :ativo " : "");
		sql.append(StringUtils.isNotBlank(login) ? " AND UPPER(usu.login) like UPPER(:login) " : "");
		sql.append(StringUtils.isNotBlank(nome) ? " AND UPPER(usu.nome) like UPPER(:nome) " : "");
		return sql;
	}
	
	private Query getQuery(StringBuilder sql, Map<String, Object> filter, Integer first, Integer rows){
		
		Object ativo = filter.get(ATIVO);
		String login = (String) filter.get(LOGIN);
		String nome = (String) filter.get(NOME);
		
		Query query = entityManager.createQuery(sql.toString());
		
		if(!ativo.equals(NULL)) {
			query.setParameter(ATIVO, Boolean.parseBoolean(ativo.toString()));
		}
		
		if(StringUtils.isNotBlank(login)) {
			query.setParameter(LOGIN, "%" + login + "%");
		}
		
		if(StringUtils.isNotBlank(nome)) {
			query.setParameter(NOME, "%" + nome + "%");
		}

		if(first != null){
			query.setFirstResult(first);
		}
		
		if(rows != null){
			query.setMaxResults(rows);
		}
		
		return query;
		
	}

	public Usuario findByLoginAtivo(String login) {
		try{
			return (Usuario) entityManager.createQuery("SELECT usu FROM Usuario usu WHERE usu.login = :login AND usu.ativo = :ativo")
										  .setParameter(LOGIN, login)
										  .setParameter(ATIVO, Boolean.TRUE)
										  .getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public Long findByLogin(String login) {
		try{
			return (Long) entityManager.createQuery("SELECT usu.id FROM Usuario usu WHERE usu.login = :login AND usu.ativo = :ativo")
					.setParameter(LOGIN, login)
					.setParameter(ATIVO, Boolean.TRUE)
					.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean isValidLogin(Usuario usuario){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT usu from Usuario usu WHERE UPPER(usu.login) LIKE UPPER(:login) ");
		Query query = entityManager.createQuery(sql.toString());
	    query.setParameter(LOGIN, usuario.getLogin());
		return !query.getResultList().isEmpty();
	}
	
	public boolean validarSenha(Usuario usuario, String senha){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT usu from Usuario usu WHERE UPPER(usu.login) LIKE UPPER(:login) AND senha = :senha ");
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(LOGIN, usuario.getLogin());
		query.setParameter(SENHA, senha);
		return !query.getResultList().isEmpty();
	}
	
	@Transactional
	public boolean alterarSenha(Usuario usuario, String senha, Boolean alteraSenha){
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE FROM Usuario SET alteraSenha = :alteraSenha, senha = :senha WHERE id = :id ");
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(ALTERA_SENHA, alteraSenha);
		query.setParameter(SENHA, senha);
		query.setParameter(ID, usuario.getId());
		return query.executeUpdate() >= 1 ? true : false;
	}
	
	@Transactional
	public boolean updateAtivo(Usuario usuario, Boolean value){
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE FROM Usuario SET ativo = :ativo WHERE id = :id");
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(ATIVO, value ? true : false);
		query.setParameter(ID, usuario.getId());
		return query.executeUpdate() >= 1 ? true : false;
	}
	
	@Override
	@Transactional
	public Usuario save(Usuario entity) {
		if(!entity.isNew() && StringUtils.isBlank(entity.getSenha())){
			Usuario usuarioAnterior = findByID(entity.getId());
			entity.setSenha(usuarioAnterior.getSenha());
		}
		super.save(entity);
		
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<Permissao> findPermissaoByUsuario(Usuario usuario) {
		StringBuilder jpQL = new StringBuilder();
		jpQL.append("SELECT perm FROM Perfil per ");
		jpQL.append("INNER JOIN per.permissoes perm ");
		jpQL.append("INNER JOIN per.usuarios usu ");
		jpQL.append("WHERE usu = :usuario");
		return entityManager.createQuery(jpQL.toString()).setParameter(USUARIO, usuario)  .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Permissao> findAllPermissao() {
		return entityManager.createQuery("SELECT per FROM Permissao per ORDER BY id DESC").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuarioAtivo(){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT usu FROM Usuario usu ");
		sql.append(" WHERE ativo = true  ");
		return entityManager.createQuery(sql.toString()).getResultList();
	}
	
	public ControleBloqueioUsuario buscarUltimoBloqueio(Usuario usuario){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT controle FROM ControleBloqueioUsuario controle ");
		sql.append(" WHERE usuario = :usuario ");
		sql.append(" ORDER BY id DESC ");
		try{
			return entityManager.createQuery(sql.toString(), ControleBloqueioUsuario.class)
								.setMaxResults(1)
								.setParameter("usuario", usuario)
								.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}

	
}