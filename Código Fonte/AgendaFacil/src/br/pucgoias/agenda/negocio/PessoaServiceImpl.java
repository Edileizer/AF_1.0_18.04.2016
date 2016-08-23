package br.pucgoias.agenda.negocio;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.pucgoias.af.entidade.Pessoa;
import br.pucgoias.af.entidade.Telefone;
import br.pucgoias.af.persistencia.PessoaDAO;
import br.pucgoias.af.persistencia.TelefoneDAO;
import br.pucgoias.af.util.AfException;

/**
 * Classe que define as operacoes da camada de negocio de Pessoa
 * @author Edileizer
 *
 */
@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

	//Interface da persistencia
	private PessoaDAO pessoaDAO;
	//Interface da persistencia
	private TelefoneDAO telefoneDAO;
	
	public TelefoneDAO getTelefoneDAO() {
		return telefoneDAO;
	}

	@Autowired
	public void setTelefoneDAO(TelefoneDAO telefoneDAO) {
		this.telefoneDAO = telefoneDAO;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	@Autowired
	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}

	/**
	 * Inclui uma pessoa
	 * @param pessoa
	 * @return
	 * @throws AfException
	 */
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public Pessoa incluir(Pessoa pessoa) throws AfException {
		return getPessoaDAO().incluir(pessoa);
	}

	/**
	 * Altera uma pessoa
	 * @param pessoa
	 * @return
	 * @throws AfException
	 */
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public Pessoa alterar(Pessoa pessoa) throws AfException {
		
		//exclui os itens da base que foram removidos da tela
		Pessoa pessoaExistente = this.consultar(pessoa.getPesId());
		for (Telefone telefone : pessoaExistente.getListaTelefone()) {
			if(!pessoa.getListaTelefone().contains(telefone)){
				getTelefoneDAO().excluir(telefone.getTelId());
			}
		}
		
		return getPessoaDAO().alterar(pessoa);
	}

	/**
	 * Exclui uma pessoa
	 * @param pessoa
	 * @throws AfException
	 */
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public void excluir(Integer id) throws AfException {
		
		//exclui todos os itens antes de excluir a pessoa
		Pessoa pessoaExistente = this.consultar(id);
		for (Telefone telefone : pessoaExistente.getListaTelefone()) {
			getTelefoneDAO().excluir(telefone.getTelId());
		}

		getPessoaDAO().excluir(id);
	}

	/**
	 * Consulta uma pessoa pelo identificador
	 * @param id
	 * @return
	 * @throws AfException
	 */
	@Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
	public Pessoa consultar(Integer id) throws AfException {
		Pessoa pessoa = getPessoaDAO().consultar(id);
		//Inicializa a lista de telefones
		Hibernate.initialize(pessoa.getListaTelefone());
		return pessoa;
	}

	/**
	 * Lista todas as pessoas cadastradas
	 * @return
	 * @throws AfException
	 */
	@Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
	public List<Pessoa> listar() throws AfException {
		return getPessoaDAO().listar();
	}

}
