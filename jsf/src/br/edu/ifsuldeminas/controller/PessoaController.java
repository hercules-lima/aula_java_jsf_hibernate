package br.edu.ifsuldeminas.controller;

import java.util.Arrays;
import java.util.List;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Pessoa;
import br.edu.ifsuldeminas.modelo.Sexo;

public class PessoaController {
	
	public List<Sexo> getSexos(){
		return Arrays.asList(Sexo.values());
	}
	
	public void remover(Pessoa p) {
		new DAO<Pessoa>(Pessoa.class).remove(p.getId());
	}
	
	public void gravar(Pessoa p){
		if (p.getId() == null) {
			new DAO<Pessoa>(Pessoa.class).adiciona(p);
		} else {
			new DAO<Pessoa>(Pessoa.class).atualiza(p);
		}
	}

}
