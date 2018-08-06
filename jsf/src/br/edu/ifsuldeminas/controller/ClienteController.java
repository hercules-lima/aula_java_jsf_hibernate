package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.xml.bind.ValidationException;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Cliente;

@ManagedBean
@ViewScoped
public class ClienteController extends PessoaController {
	private Cliente cliente = new Cliente();
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void gravar(){
		gravar(cliente);
		cliente = new Cliente();
	}
	
	public List<Cliente> getTodosClientes(){
		return new DAO<Cliente>(Cliente.class).listaTodos();
	}
	
	public void carregar(Cliente c){
		cliente = c;
	}
	
	public void ehEmail(FacesContext fc, UIComponent component, 
			Object value) throws ValidatorException{
		String email = value.toString();
		if (!email.contains("@")){
			throw new ValidatorException(
					new FacesMessage("E-mail inválido."));
		}
	}
	
	
	
	
	
}
