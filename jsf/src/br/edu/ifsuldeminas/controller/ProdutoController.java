package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Produto;
import br.edu.ifsuldeminas.modelo.Tipo;

@ManagedBean
@ViewScoped
public class ProdutoController {
	private Produto produto = new Produto();
	private Integer tipoId;

	public Produto getProduto() {
		return produto;
	}

	public Integer getTipoId() {
		return tipoId;
	}

	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}

	public void gravar() {
		if (tipoId == null) {
			FacesContext.getCurrentInstance().addMessage(
					"tipo",
					new FacesMessage("Tipo obrigatório"));
			return;
		}
		
		Tipo t = 
			new DAO<Tipo>(Tipo.class).listaPorId(this.tipoId);
		produto.setTipo(t);
		
		// não tem id - insere
		if (this.produto.getId() == null) {
			new DAO<Produto>(Produto.class).adiciona(produto);
		} else { // tem id - atualiza
			new DAO<Produto>(Produto.class).atualiza(produto);
		}
		// limpa formulário
		this.produto = new Produto();
		this.tipoId = 0;
	}

	public List<Produto> getTodosProdutos() {
		return new DAO<Produto>(Produto.class).listaTodos();
	}

	public void remover(Produto p) {
		new DAO<Produto>(Produto.class).remove(p.getId());
	}

	public void carregar(Produto p) {
		this.produto = p;
		this.tipoId = p.getTipo().getId(); // combo
	}

	public List<Tipo> getTodosTipos() {
		return new DAO<Tipo>(Tipo.class).listaTodos();
	}

}
