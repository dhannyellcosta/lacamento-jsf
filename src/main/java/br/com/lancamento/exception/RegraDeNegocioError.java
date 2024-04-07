package br.com.lancamento.exception;

public class RegraDeNegocioError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegraDeNegocioError(String message) {
		super(message);
	}
}
