package br.com.lancamento.repository;

import br.com.lancamento.entidades.Pessoa;

public interface IDaoPessoa {

	Pessoa consultarUsuario(String login, String senha);
	
}
