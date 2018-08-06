package br.edu.ifsuldeminas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Comanda;
import br.edu.ifsuldeminas.modelo.ItemComanda;

public class ItemComandaDao {
	
	public List<ItemComanda> listaPorComanda(Comanda c) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT i FROM ItemComanda i WHERE i.comanda = :pComanda";
		
		TypedQuery<ItemComanda> query = em.createQuery(jpql, ItemComanda.class);
		query.setParameter("pComanda", c);
		
		List<ItemComanda> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}

}
