package br.com.petshop.architecture.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.petshop.architecture.entity.BaseEntity;
import br.com.petshop.architecture.entity.IEntity;
import br.com.petshop.architecture.util.StaticFilter;

@Component
public abstract class BaseService<T extends IEntity> extends StaticFilter{
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseService() {
		if(this.getClass().getGenericSuperclass() instanceof ParameterizedType){
			entityClass = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
	}
	
	@SuppressWarnings("unused")
	public List<T> findByFilter(Map<String, Object> filter, Integer first, Integer rows) {throw new NotImplementedException();}

	@SuppressWarnings("unused")
	public Integer countByFilter(Map<String, Object> filter) {throw new NotImplementedException();}
	
	@SuppressWarnings("unchecked")
	public <E extends Disabilitable> void disable(E entity){
		entity.setAtivo(false);
		save((T) entity);
	}
	
	@SuppressWarnings("unchecked")
	public BaseEntity buscarGenerico(Object descricao, Class<? extends BaseEntity> entity){
		StringBuilder jpQL = new StringBuilder();
		
		jpQL.append("FROM ");
		jpQL.append(entity.getSimpleName());
		
		if(descricao instanceof String){
			jpQL.append(" WHERE sem_acentos(UPPER(descricao)) = sem_acentos(UPPER(:descricao)) ");
		}else{
			jpQL.append(" WHERE descricao = :descricao ");
		}
		
		List<BaseEntity> result = entityManager.createQuery(jpQL.toString())
					             	    	   .setParameter("descricao", descricao)
					             	    	   .getResultList();
		
		return result.isEmpty() ? null : result.get(0);
	}
	
	@Transactional
	public void remove(IEntity object){
		object = findByID(object.getId());
		entityManager.remove(object);
	}

	@Transactional
	public T save(T entity){
		if(entity.isNew()){
			entityManager.persist(entity);
		}else{
			entityManager.merge(entity);
		}
		return entity;
	}

	public T findByID(Long id){
		return entityManager.find(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(int first, int pageSize){
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append(" FROM ");
        sbQuery.append(entityClass.getSimpleName());
        sbQuery.append(" ORDER BY id DESC ");
        
        return entityManager.createQuery(sbQuery.toString())
			                .setFirstResult(first)
			                .setMaxResults(pageSize)
			                .getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findAll(){
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append(" FROM ");
        sbQuery.append(entityClass.getSimpleName());
        
        try{
	        entityClass.getDeclaredField("descricao");
	        sbQuery.append(" ORDER BY descricao ASC ");
        }catch(Exception e){}
        
        return entityManager.createQuery(sbQuery.toString()).getResultList();
    }
    
    public Long countAll(){
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT COUNT(*) FROM ");
        sbQuery.append(entityClass.getSimpleName());
        return (Long) entityManager.createQuery(sbQuery.toString()).getSingleResult();
    }
    
    @SuppressWarnings("unchecked")
	public static boolean checkIsNotNull(Object checkedObject) {
		boolean response = checkedObject!= null;

		if(checkedObject instanceof List<?>){
			response  = !((((List<IEntity>)checkedObject).isEmpty()) || (((List<IEntity>)checkedObject).get(0) == null));
		}
		
		return response;
	}
    
    public boolean checkMenosUm(List<?> list){
    	return list.get(0).equals("-1") ? true : false;
    }
    
    public boolean checkIsNull(Object value){
    	return !checkIsNotNull(value);
    }
    
	public String getFiltroStatus(Map<String, Object> filter){
		int status = Integer.parseInt((String)filter.get(TIPO));
		
		switch (status) {
			case 1:
				return STATUS_VALIDAS;
			case 2:
				return STATUS_EM_ABERTO;
			case 3:
				return STATUS_CANCELADAS;
			default:
				return "";
		}
	}
    
    public static String convertListToString(List<? extends IEntity> list){
		
		if(list == null || list.isEmpty() || list.get(0) == null){
			return "";
		}
		
		if(list.get(0).getId() == -1L){
			list.remove(0);
		}
		
		List<IEntity> copy = new ArrayList<IEntity>();
		copy.addAll(list);
		
		CollectionUtils.transform(copy, new BeanToPropertyValueTransformer("id"));
		return copy.toString().replace("[", "").replace("]", "");
	}

	public boolean filtroDataNaoNulo(Map<String, Object> filter){
		return filter.get(DATA_INICIAL) != null && filter.get(DATA_FINAL) != null;
	}
	
}