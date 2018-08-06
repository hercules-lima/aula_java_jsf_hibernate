package br.edu.ifsuldeminas.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Pessoa{
	@Temporal(TemporalType.DATE)
	private Calendar dtCadastro = Calendar.getInstance();

	public Calendar getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

}
