package br.edu.ifsuldeminas.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Grupo;

public class GrupoDao {
	
	public Grupo listaPorId(Grupo g) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT g FROM Grupo g "
				+ "LEFT JOIN FETCH g.funcionalidades "
				+ "WHERE g.id = :pId";
		
		TypedQuery<Grupo> query = em.createQuery(jpql, Grupo.class);
		query.setParameter("pId", g.getId());
		
		g = query.getSingleResult();
		
		em.close();
		
		return g;
	}
	

}
