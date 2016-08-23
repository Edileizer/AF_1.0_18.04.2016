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
 * Classe que representa os dados persistentes de um tipo de servico
 * @author Edileizer
 *
 */
@Entity
@Table(name="tipoServico")
public class TipoServico implements Serializable {
	
	private static final long serialVersionUID = 5152724164913423114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TSE_PK_ID")
	private Integer tipoServicoId;

	@Column(name="TSE_DESCRICAO")
	private String tseDescricao;
	
	public Integer getTipoServicoId() {
		return tipoServicoId;
	}
	public void setTipoTelefoneId(Integer tipoServicoId) {
		this.tipoServicoId = tipoServicoId;
	}
	public String getTseDescricao() {
		return tseDescricao;
	}
	public void setTseDescricao(String tseDescricao) {
		this.tseDescricao = tseDescricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tipoServicoId == null) ? 0 : tipoServicoId.hashCode());
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
		if (tipoServicoId == null) {
			if (other.tipoServicoId != null)
				return false;
		} else if (!tipoServicoId.equals(other.tipoServicoId))
			return false;
		return true;
	}		
}
