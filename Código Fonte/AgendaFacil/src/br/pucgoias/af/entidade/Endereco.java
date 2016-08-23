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
 * Classe que representa os dados persistentes de endereco
 * @author Edileizer
 *
 */
@Entity
@Table(name="endereco")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 5152724164913423114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="END_PK_ID")
	private Integer enderecoId;

	@Column(name="END_DESCRICAO")
	private String endDescricao;
	
	@OneToMany(mappedBy="endereco", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Pessoa> listaPessoa;
	
	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cidadeId", referencedColumnName="pesId", nullable=false)
	private Pessoa pessoa;
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Integer enderecoId) {
		this.enderecoId = enderecoId;
	}

	public String getEndDescricao() {
		return endDescricao;
	}

	public void setEndDescricao(String endDescricao) {
		this.endDescricao = endDescricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((enderecoId == null) ? 0 : enderecoId.hashCode());
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
		Endereco other = (Endereco) obj;
		if (enderecoId == null) {
			if (other.enderecoId != null)
				return false;
		} else if (!enderecoId.equals(other.enderecoId))
			return false;
		return true;
	}
		
}
