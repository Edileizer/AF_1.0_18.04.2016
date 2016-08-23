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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe que representa os dados persistentes de servicos
 * @author Edileizer
 *
 */
@Entity
@Table(name="servico")
public class Servicos implements Serializable{

	private static final long serialVersionUID = 6487849002108370775L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SER_PK_ID")
	private Integer serId;
	
	@Column(name="SER_PRO_NOME")
	private String proNome;

	@Column(name="SER_CLI_NOME")
	private String cliNome;
	
	@Column(name="SER_DATA")
	private String data;
	
	@Column(name="SER_HORARIO")
	private String horario;
	
	@Column(name="SER_DESCRICAO")
	private String serDescricao;

	@OneToMany(mappedBy="servico", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<TipoServico> listaTipoServico;
	
	public List<TipoServico> getListaTipoServico() {
		return listaTipoServico;
	}

	public void setListaTipoServico(List<TipoServico> listaTipoServico) {
		this.listaTipoServico = listaTipoServico;
	}

	public Integer getSerId() {
		return serId;
	}

	public void setSerId(Integer serId) {
		this.serId = serId;
	}

	public String getProNome() {
		return proNome;
	}

	public void setProNome(String proNome) {
		this.proNome = proNome;
	}
	
	public String getCliNome() {
		return cliNome;
	}

	public void setCliNome(String cliNome) {
		this.cliNome = cliNome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getSerDescricao() {
		return serDescricao;
	}

	public void setSerDescricao(String serDescricao) {
		this.serDescricao = serDescricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serId == null) ? 0 : serId.hashCode());
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
		Servicos other = (Servicos) obj;
		if (serId == null) {
			if (other.serId != null)
				return false;
		} else if (!serId.equals(other.serId))
			return false;
		return true;
	}

}
