package br.com.lancamento.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.lancamento.entidades.Lancamento;
import br.com.lancamento.repository.IDaoLancamento;

@Named
public class IDaoLancamentoImpl implements IDaoLancamento, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	@Override
	public List<Lancamento> consultar(Long idPessoa) {
		
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		lancamentos = entityManager.createQuery("select l from Lancamento l where l.pessoa.id = " + idPessoa).getResultList();
				
		transaction.commit();		
		return lancamentos;
	}
	
}
