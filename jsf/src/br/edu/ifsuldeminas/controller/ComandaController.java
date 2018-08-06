package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.ComandaDao;
import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.dao.ItemComandaDao;
import br.edu.ifsuldeminas.modelo.Comanda;
import br.edu.ifsuldeminas.modelo.ItemComanda;
import br.edu.ifsuldeminas.modelo.Produto;

@ManagedBean
@ViewScoped
public class ComandaController {
	private Comanda comanda = new Comanda();
	private Integer qtde;
	private Integer produtoId;

	public Comanda getComanda() {
		return comanda;
	}

	public Integer getQtde() {
		return qtde;
	}
	
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	
	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public List<Produto> getTodosProdutos(){
		return new DAO<Produto>(Produto.class).listaTodos();
	}
	
	public void gravarItem(){
		Produto p = new DAO<Produto>(Produto.class).listaPorId(produtoId);
		
		ItemComanda item = new ItemComanda();
		item.setProduto(p);
		item.setQtde(qtde);
		item.setValorUnitario(p.getValor());
		item.setComanda(comanda);
		
		comanda.add(item);
		qtde = null;
		produtoId = null;
	}
	
	public List<ItemComanda> getItensDaComanda() {
		return comanda.getItens();
	}
	
	public void removerItem(ItemComanda item){
		comanda.getItens().remove(item);
	}
	
	public void gravar(){
		if (comanda.getItens().isEmpty()) {
			FacesContext.getCurrentInstance().
			addMessage("tabelaItens", 
				new FacesMessage("Item obrigatório."));
			return;
		}
		
		if (this.comanda.getId() == null) {
			new DAO<Comanda>(Comanda.class).adiciona(comanda);
		} else {
			new DAO<Comanda>(Comanda.class).atualiza(comanda);
		}
		
		this.comanda = new Comanda();
	}
	
//	private void atualiza() {
//		List<ItemComanda> listaItens =
//		new ItemComandaDao().listaPorComanda(comanda);
//		
//		DAO<ItemComanda> dao = 
//				new DAO<ItemComanda>(ItemComanda.class);
//		for (ItemComanda i : listaItens) {
//			dao.remove(i.getId());
//		}
//		
//		new DAO<Comanda>(Comanda.class).atualiza(comanda);
//	}

	public List<Comanda> getTodasComandas(){
		return new DAO<Comanda>(Comanda.class).listaTodos();
	}
	
	public void remover(Comanda c){
		new DAO<Comanda>(Comanda.class).remove(c.getId());
	}
	
	public void carregar(Comanda c){
		c = new ComandaDao().listaPorId(c);
		comanda = c;
	}
}
