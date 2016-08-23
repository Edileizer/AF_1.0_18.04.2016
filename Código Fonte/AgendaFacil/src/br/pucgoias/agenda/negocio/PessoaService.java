package br.pucgoias.agenda.negocio;

import java.util.List;

import br.pucgoias.af.entidade.Pessoa;
import br.pucgoias.af.util.AfException;

/**
 * Interface que define as operacoes da camada de negocio de Pessoa
 * @author Edileizer
 *
 */
public interface PessoaService {
	
	/**
	 * Inclui uma pessoa
	 * @param pessoa
	 * @return
	 * @throws AfException
	 */
	public Pessoa incluir(Pessoa pessoa) throws AfException;
	
	/**
	 * Altera uma pessoa
	 * @param pessoa
	 * @return
	 * @throws AfException
	 */
	public Pessoa alterar(Pessoa pessoa) throws AfException;
	
	/**
	 * Exclui uma pessoa
	 * @param id
	 * @throws AfException
	 */
	public void excluir(Integer id) throws AfException;
	
	/**
	 * Consulta uma pessoa pelo identificador
	 * @param id
	 * @return
	 * @throws AfException
	 */
	public Pessoa consultar(Integer id) throws AfException;
	
	/**
	 * Lista todas as pessoas cadastradas
	 * @return
	 * @throws AfException
	 */
	public List<Pessoa> listar() throws AfException;

}
