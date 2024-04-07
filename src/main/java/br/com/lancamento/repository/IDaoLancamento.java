package br.com.lancamento.repository;

import java.util.List;

import br.com.lancamento.entidades.Lancamento;

public interface IDaoLancamento {
	
	List<Lancamento> consultar(Long idUser);

}
