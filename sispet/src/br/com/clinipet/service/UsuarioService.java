package br.com.clinipet.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.clinipet.entity.Usuario;

@Stateless
public class UsuarioService {

	@PersistenceContext()
	EntityManager em;
	
	public List<Usuario> list(){
		Query query = em.createQuery("select u from Usuario");
		List<Usuario> result = (List<Usuario>)query.getResultList();
		return result;
	}

	public Usuario find(long id) {
		Usuario user = em.find(Usuario.class, id);
		return user;
	}

	public void delete(long id) {
		Usuario user = em.getReference(Usuario.class, id);
		if (user != null)
			em.remove(user);
	}

	public void update(Usuario user) {
		em.merge(user);
	}

	public void add(Usuario user) {
		em.persist(user);
	}

}
