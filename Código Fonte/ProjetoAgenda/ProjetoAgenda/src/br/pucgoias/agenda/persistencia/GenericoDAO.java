package br.pucgoias.agenda.persistencia;

import java.io.Serializable;
import java.util.List;

import br.pucgoias.util.AgendaException;

/**
 * Interface que define as operacoes da camada de persistencia generica
 * @author Gilcimar
 *
 */
public interface GenericoDAO<T, ID extends Serializable> {
	
	/**
	 * Retorna a classe a ser persistida
	 * @return
	 */
	public Class<T> getObjectClass();
	
	/**
	 * Inclui um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws AgendaException
	 */
	public T incluir(T object) throws AgendaException;
	
	/**
	 * Altera um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws AgendaException
	 */
	public T alterar(T object) throws AgendaException;
	
	/**
	 * Consulta um objeto T da base de dados
	 * @param id
	 * @return
	 * @throws AgendaException
	 */
	public T consultar(Integer id) throws AgendaException;
	
	/**
	 * Exclui um objeto T  da base de dados
	 * @param id
	 * @throws AgendaException
	 */
	public void excluir(Integer id) throws AgendaException;
	
	/**
	 * Lista os objetos T da base de dados
	 * @return
	 * @throws AgendaException
	 */
	public List<T> listar() throws AgendaException;
}
