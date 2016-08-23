package br.pucgoias.agenda.controle;

import org.springframework.stereotype.Component;

/**
 * Classe que representa a parte de telefone no formulario web de Pessoa
 * @author Gilcimar
 *
 */
@Component
public class TelefoneBean {

	private Integer idTelefone;
	private String dsNumero;
	private String clTipo;
	
	public Integer getIdTelefone() {
		return idTelefone;
	}
	public void setIdTelefone(Integer idTelefone) {
		this.idTelefone = idTelefone;
	}
	public String getDsNumero() {
		return dsNumero;
	}
	public void setDsNumero(String dsNumero) {
		this.dsNumero = dsNumero;
	}
	public String getClTipo() {
		return clTipo;
	}
	public void setClTipo(String clTipo) {
		this.clTipo = clTipo;
	}
	
	
	
	
}
