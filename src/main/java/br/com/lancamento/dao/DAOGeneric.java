package br.com.lancamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Named
public class DAOGeneric<E> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	public E salvar(E entidade) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		E retorno = entityManager.merge(entidade);

		entityTransaction.commit();

		return retorno;
	}

	public List<E> getListEntity(Class<E> entidade) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();

		entityTransaction.commit();

		return retorno;
	}

	public E bucarPorId(Class<E> classe, Long id) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		E retorno = (E) entityManager.find(classe, id);

		entityTransaction.commit();

		return retorno;
	}

	public void deletePorId(E entidade, Long id) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		entityTransaction.commit();
	}
}
