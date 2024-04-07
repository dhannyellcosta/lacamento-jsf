package br.com.lancamento.repository.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import br.com.lancamento.entidades.Pessoa;
import br.com.lancamento.jpautil.JPAUtil;
import br.com.lancamento.repository.IDaoPessoa;

@Named
public class IDaoPessoaImpl implements IDaoPessoa, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	@Override
	public Pessoa consultarUsuario(String login, String senha) throws NoResultException {

		Pessoa pessoa = null;
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		pessoa = (Pessoa) entityManager
				.createQuery("select p from Pessoa p where p.login = '" + login + "' and p.senha = '" + senha + "'")
				.getSingleResult();

		entityTransaction.commit();
		return pessoa;
	}

}
