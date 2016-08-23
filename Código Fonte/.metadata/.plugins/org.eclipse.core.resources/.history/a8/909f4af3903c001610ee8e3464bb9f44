package br.pucgoias.agenda.negocio;

import java.util.List;

import br.pucgoias.agenda.entidade.Pessoa;
import br.pucgoias.util.AgendaException;

/**
 * Interface que define as operacoes da camada de negocio de Pessoa
 * @author Gilcimar
 *
 */
public interface PessoaService {
	
	/**
	 * Inclui uma pessoa
	 * @param pessoa
	 * @return
	 * @throws AgendaException
	 */
	public Pessoa incluir(Pessoa pessoa) throws AgendaException;
	
	/**
	 * Altera uma pessoa
	 * @param pessoa
	 * @return
	 * @throws AgendaException
	 */
	public Pessoa alterar(Pessoa pessoa) throws AgendaException;
	
	/**
	 * Exclui uma pessoa
	 * @param id
	 * @throws AgendaException
	 */
	public void excluir(Integer id) throws AgendaException;
	
	/**
	 * Consulta uma pessoa pelo identificador
	 * @param id
	 * @return
	 * @throws AgendaException
	 */
	public Pessoa consultar(Integer id) throws AgendaException;
	
	/**
	 * Lista todas as pessoas cadastradas
	 * @return
	 * @throws AgendaException
	 */
	public List<Pessoa> listar() throws AgendaException;

}
