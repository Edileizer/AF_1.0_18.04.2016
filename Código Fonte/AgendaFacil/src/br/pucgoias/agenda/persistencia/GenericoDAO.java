package br.pucgoias.agenda.persistencia;

import java.io.Serializable;
import java.util.List;

import br.pucgoias.af.util.AfException;

/**
 * Interface que define as operacoes da camada de persistencia generica
 * @author Edileizer
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
	 * @throws AfException
	 */
	public T incluir(T object) throws AfException;
	
	/**
	 * Altera um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws AfException
	 */
	public T alterar(T object) throws AfException;
	
	/**
	 * Consulta um objeto T da base de dados
	 * @param id
	 * @return
	 * @throws AfException
	 */
	public T consultar(Integer id) throws AfException;
	
	/**
	 * Exclui um objeto T  da base de dados
	 * @param id
	 * @throws AfException
	 */
	public void excluir(Integer id) throws AfException;
	
	/**
	 * Lista os objetos T da base de dados
	 * @return
	 * @throws AfException
	 */
	public List<T> listar() throws AfException;
}
