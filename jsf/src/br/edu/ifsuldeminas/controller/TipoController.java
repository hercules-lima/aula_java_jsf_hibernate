package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Tipo;

@ManagedBean
@ViewScoped
public class TipoController {
	private Tipo tipo = new Tipo();
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void gravar(){
		if (tipo.getId() == null) {
			new DAO<Tipo>(Tipo.class).adiciona(tipo);
		} else {
			new DAO<Tipo>(Tipo.class).atualiza(tipo);
		}
		
		this.tipo = new Tipo();
	}

	public List<Tipo> getTodosTipos(){
		return new DAO<Tipo>(Tipo.class).listaTodos();
	}
	
	public void carregar(Tipo t){
		this.tipo = t;
	}
	
	public void remover(Tipo t){
		try{
			new DAO<Tipo>(Tipo.class).remove(t.getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
				"tipo", 
				new FacesMessage("Impossível remover: tipo "
						+ "associado a produto."));
		}
	}
}
