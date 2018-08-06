package br.edu.ifsuldeminas.modelo;

public enum Sexo {
	FEMININO("Feminino"), MASCULINO("Masculino");
	
	private String descricao;
	
	private Sexo(String desc) {
		this.descricao = desc;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
