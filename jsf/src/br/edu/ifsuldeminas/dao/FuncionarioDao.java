package br.edu.ifsuldeminas.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Funcionario2;
import br.edu.ifsuldeminas.util.Utils;

public class FuncionarioDao {
	
	public Funcionario2 buscarPorLoginESenha(String login, String senha) {
		Funcionario2 usuario;
		
		String jpql = "SELECT DISTINCT f FROM Funcionario2 f "
				+ "LEFT JOIN FETCH f.grupo g LEFT JOIN FETCH g.funcionalidades "
				+ "WHERE f.login = :pLogin AND f.senha = :pSenha";
				
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Funcionario2> query = em.createQuery(jpql, Funcionario2.class);
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", Utils.toMD5(senha));
		
		try {
			usuario = query.getSingleResult();
	    } catch (NoResultException ex) {
	        usuario = null;
	    }
		
		em.close();
		
		return usuario;
	}
	
//	public static void main(String[] args) {
//		Funcionario f = new FuncionarioDao().buscarPorEmailESenha("aline.valle", "123");
//		System.out.println(f.getNome() + ": " + f.getLogin() + ", " + f.getSenha());
//		System.out.println(f.getGrupo().getNome());
//		for (Funcionalidade fu : f.getGrupo().getFuncionalidades()) {
//			System.out.println(fu.getNome() + ", " + fu.getPagina());
//		}
//	}
}
