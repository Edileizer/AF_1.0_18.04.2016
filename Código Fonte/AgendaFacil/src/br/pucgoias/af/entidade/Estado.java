package br.pucgoias.af.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe que representa os dados persistentes de uma cidade
 * @author Edileizer
 *
 */
@Entity
@Table(name="estado")
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 5152724164913423114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EST_PK_ID")
	private Integer estadoId;
	
	@OneToMany(mappedBy="estado", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Cidade> listaCidade;
	
	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}
	
	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((estadoId == null) ? 0 : estadoId.hashCode());
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
		Estado other = (Estado) obj;
		if (estadoId == null) {
			if (other.estadoId != null)
				return false;
		} else if (!estadoId.equals(other.estadoId))
			return false;
		return true;
	}		
}
