package br.pucgoias.agenda.persistencia;

import org.springframework.stereotype.Repository;

import br.pucgoias.agenda.entidade.Telefone;

/**
 * Classe que define as operacoes da camada de persistencia de Telefone
 * @author Gilcimar
 *
 */
@Repository
public class TelefoneDAOImpl extends GenericoDAOImpl<Telefone, Integer> implements
		TelefoneDAO {

}
