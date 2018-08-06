package br.edu.ifsuldeminas.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Comanda;

public class ComandaDao {
	
	public Comanda listaPorId(Comanda c) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT c FROM Comanda c LEFT JOIN FETCH c.itens WHERE c.id = :pId";
		
		TypedQuery<Comanda> query = em.createQuery(jpql, Comanda.class);
		query.setParameter("pId", c.getId());
		
		c = query.getSingleResult();
		
		em.close();
		
		return c;
	}

}
