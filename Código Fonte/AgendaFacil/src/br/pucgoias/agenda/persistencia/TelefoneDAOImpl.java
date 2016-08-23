package br.pucgoias.agenda.persistencia;

import org.springframework.stereotype.Repository;

import br.pucgoias.af.entidade.Telefone;

/**
 * Classe que define as operacoes da camada de persistencia de Telefone
 * @author Edileizer
 *
 */


@Repository
public class TelefoneDAOImpl extends GenericoDAOImpl<Telefone, Integer> implements
		TelefoneDAO {

}

