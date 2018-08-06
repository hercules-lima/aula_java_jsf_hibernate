package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Funcionalidade;

@ManagedBean
@ViewScoped
public class FuncionalidadeController {
	private Funcionalidade funcionalidade = new Funcionalidade();
	
	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}
	
	public void gravar(){
		if (this.funcionalidade == null) {
			new DAO<Funcionalidade>(Funcionalidade.class).adiciona(this.funcionalidade);
		} else {
			new DAO<Funcionalidade>(Funcionalidade.class).atualiza(this.funcionalidade);
		}
		
		this.funcionalidade = new Funcionalidade();
	}
	
	public List<Funcionalidade> getTodasFuncionalidades(){
		return new DAO<Funcionalidade>(Funcionalidade.class).listaTodos();
	}
	
	public void carregar(Funcionalidade f){
		this.funcionalidade = f;
	}
	
	public void remover(Funcionalidade f){
		try{
			new DAO<Funcionalidade>(Funcionalidade.class).remove(f.getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("funcionaliddae", new FacesMessage("Impossível remover: Funcionalidade associada a grupo."));
		}
	}
}
