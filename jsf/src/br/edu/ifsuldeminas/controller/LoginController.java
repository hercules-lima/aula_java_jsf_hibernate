package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.FuncionarioDao;
import br.edu.ifsuldeminas.modelo.Funcionalidade;
import br.edu.ifsuldeminas.modelo.Funcionario2;

@ManagedBean
@ViewScoped
public class LoginController {
	private Funcionario2 usuario = new Funcionario2();
	
	public Funcionario2 getUsuario() {
		return usuario;
	}
	
	public String logar(){
		usuario = new FuncionarioDao().buscarPorLoginESenha(
				usuario.getLogin(), usuario.getSenha());
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (usuario != null) { // logou
			// inclui usuario na sessao
			context.getExternalContext().getSessionMap()
			.put("usuarioLogado", usuario);
			
			List<Funcionalidade> lista = 
					usuario.getGrupo().getFuncionalidades(); 
			
			for (Funcionalidade f : lista) {
				context.getExternalContext().getSessionMap()
				.put(f.getPagina(), f);
			}
			
			return "index?faces-redirect=true";
		} else { // não logou
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, 
					new FacesMessage("Login e/ou senha incorretos."));
			
			return "login?faces-redirect=true";
		}
	}
	
	public String deslogar(){
		FacesContext context = FacesContext.getCurrentInstance();
//		context.getExternalContext().getSessionMap().
//		remove("usuarioLogado");
		
		context.getExternalContext().getSessionMap().clear();
		
		return "login?faces-redirect=true";
	}
	
	public boolean temAcesso(String nomePagina){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Funcionalidade f = (Funcionalidade)
		context.getExternalContext().getSessionMap().
		get("/" + nomePagina + ".xhtml");
		
		if (f == null){
			return false;
		} else {
			return true;
		}
	}

}
