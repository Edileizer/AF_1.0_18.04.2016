package br.pucgoias.af.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe que representa os dados persistentes de um telefone
 * @author Gilcimar
 *
 */
@Entity
@Table(name="telefone")
public class TipoServico implements Serializable {
	
	private static final long serialVersionUID = 5152724164913423114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idTelefone")
	private Integer idTelefone;

	@Column(name="dsNumero")
	private String dsNumero;

	@Column(name="clTipo")
	private String clTipo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPessoa", referencedColumnName="idPessoa", nullable=false)
	private Servicos pessoa;
	
	public Servicos getPessoa() {
		return pessoa;
	}
	public void setPessoa(Servicos pessoa) {
		this.pessoa = pessoa;
	}
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTelefone == null) ? 0 : idTelefone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoServico other = (TipoServico) obj;
		if (idTelefone == null) {
			if (other.idTelefone != null)
				return false;
		} else if (!idTelefone.equals(other.idTelefone))
			return false;
		return true;
	}
	public void setClTipo(String clTipo) {
		this.clTipo = clTipo;
	}
		
}
