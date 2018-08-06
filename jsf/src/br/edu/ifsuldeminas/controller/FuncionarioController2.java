package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Funcionario2;
import br.edu.ifsuldeminas.modelo.Grupo;
import br.edu.ifsuldeminas.util.Utils;

@ManagedBean
@ViewScoped
public class FuncionarioController2 extends PessoaController {
	private Funcionario2 funcionario = new Funcionario2();
	private String senhaBd;
	private Integer grupoId;
	
	public Integer getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}

	public Funcionario2 getFuncionario() {
		return funcionario;
	}

	public String getSenhaBd() {
		return senhaBd;
	}

	public void gravar() {
		if (senhaBd == null || !senhaBd.equals(funcionario.getSenha())) {
			this.funcionario.setSenha(Utils.toMD5(this.funcionario.getSenha()));
		}
		
		Grupo g = new DAO<Grupo>(Grupo.class).listaPorId(this.grupoId);
		this.funcionario.setGrupo(g);

		try{
			super.gravar(funcionario);
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage("Login já existe."));
		}

		this.funcionario = new Funcionario2();
		this.grupoId = null;
		this.senhaBd = null;
	}

	public List<Grupo> getTodosGrupos() {
		return new DAO<Grupo>(Grupo.class).listaTodos();
	}

	public List<Funcionario2> getTodosFuncionarios() {
		return new DAO<Funcionario2>(Funcionario2.class).listaTodos();
	}

	public void carregar(Funcionario2 f) {
		this.senhaBd = f.getSenha();
		this.grupoId = f.getGrupo().getId();
		this.funcionario = f;
	}
}
