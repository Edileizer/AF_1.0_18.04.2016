package br.pucgoias.agenda.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe que representa os dados persistentes de pessoa
 * @author Gilcimar
 *
 */
@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 6487849002108370775L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPessoa")
	private Integer idPessoa;
	
	@Column(name="dsNome")
	private String dsNome;

	@Column(name="vlIdade")
	private String vlIdade;

	@OneToMany(mappedBy="pessoa", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Telefone> listaTelefone;
	
	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getDsNome() {
		return dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPessoa == null) ? 0 : idPessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (idPessoa == null) {
			if (other.idPessoa != null)
				return false;
		} else if (!idPessoa.equals(other.idPessoa))
			return false;
		return true;
	}

	public void setVlIdade(String vlIdade) {
		this.vlIdade = vlIdade;
	}

	public String getVlIdade() {
		return vlIdade;
	}

}
