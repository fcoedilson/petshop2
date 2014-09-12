package br.com.petshop.seguranca.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petshop.architecture.service.BaseService;
import br.com.petshop.base.entity.LogAcesso;
import br.com.petshop.base.entity.Usuario;
import br.com.petshop.base.vo.LogAcessoVO;

@Service
public class LogAcessoService extends BaseService<LogAcesso>{
	
	@SuppressWarnings("unchecked")
	public List<LogAcesso> findByFilter(Map<String, Object> filter, Integer first, Integer rows){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ace FROM LogAcesso ace ");
		sql.append(" INNER JOIN FETCH ace.usuario usu ");
		return getQuery(filter, sql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	private Query getQuery(Map<String, Object> filter, StringBuilder sql){
		
		
		Query query = entityManager.createQuery(sql.toString());
		
		return query;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<LogAcessoVO> buscarUsuarioSemAcesso(Map<String, Object> filter){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT 0 as quantidade, usu.nome as usuario, ");
		sql.append(" (SELECT lg.data_acesso FROM Log_acesso lg WHERE lg.usuario_id = usu.id ORDER BY id DESC LIMIT 1) as data ");
		sql.append(" FROM Usuario usu ");
		sql.append(" LEFT JOIN log_acesso la ON la.usuario_id = usu.id ");
		sql.append(" WHERE usu.id NOT IN (SELECT lg.usuario_id FROM Log_acesso lg WHERE lg.data_acesso BETWEEN :dataInicio AND :dataFim) ");
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query query = session.createSQLQuery(sql.toString());
		query.setParameter(DATA_INICIO, filter.get(DATA_INICIO));
		query.setParameter(DATA_FIM, new DateTime(filter.get(DATA_FIM)).withHourOfDay(23).withMinuteOfHour(59).toDate());
		query.setResultTransformer(Transformers.aliasToBean(LogAcessoVO.class));
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public LogAcesso buscarUltimoAcesso(Usuario usuario){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT log FROM LogAcesso log ");
		sql.append(" INNER JOIN FETCH log.usuario usu ");
		sql.append(" WHERE usu = :usuario ");
		sql.append(" ORDER BY log.id DESC ");
		List<LogAcesso> list = entityManager.createQuery(sql.toString()).setParameter(USUARIO, usuario).getResultList();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
	public List<LogAcesso> buscarUltimosAcessos(Usuario usuario){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT log FROM LogAcesso log ");
		sql.append(" WHERE log.usuario = :usuario ");
		sql.append(" ORDER BY log.id DESC ");
		return entityManager.createQuery(sql.toString(), LogAcesso.class).setParameter(USUARIO, usuario).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<LogAcesso> buscaPorUsuarioMaisPeriodo(Usuario usuario,Date dataInicial, Date dataFinal) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT la FROM LogAcesso la ")
		   .append("LEFT JOIN FETCH la.usuario usu ")
		   .append("WHERE usu = :usuario  ");
		if(dataInicial != null && dataFinal != null){
			sql.append(" AND la.dataAcesso BETWEEN :dataInicial AND :dataFinal ");
		}
		   sql.append("ORDER BY la.dataAcesso ASC ");

		Query query = entityManager.createQuery(sql.toString())
                .setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal)
				.setParameter("usuario", usuario);
        
		return query.getResultList();

	}
	
}