package br.com.lancamento.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lancamento.dao.DAOGeneric;
import br.com.lancamento.entidades.Lancamento;
import br.com.lancamento.entidades.Pessoa;
import br.com.lancamento.repository.IDaoLancamento;
import br.com.lancamento.util.JsfUtil;

@ViewScoped
@Named(value = "lancamentoBean")
public class LancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAOGeneric<Lancamento> daoGeneric;
	
	@Inject
	private IDaoLancamento daoLancamento;
	
	private Lancamento lancamento = new Lancamento();
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	
	
	
	public void novo() {
		lancamento = new Lancamento();
	}

	public void salvar() {
		lancamento.setPessoa(getUsuarioSessao());
		lancamento = daoGeneric.salvar(lancamento);
		carregarLancamentos();
		novo();
		JsfUtil.addSucessMessage("Lançamento salvo com sucesso");
	}

	public String buscarPorId() {
		lancamento = daoGeneric.bucarPorId(Lancamento.class, lancamento.getId());
		return "";
	}

	public void deletar() {
		buscarPorId();
		daoGeneric.deletePorId(lancamento, lancamento.getId());
		carregarLancamentos();
		lancamento = new Lancamento();
		JsfUtil.addSucessMessage("Lançamento excluido com sucesso");
	}

	@PostConstruct
	public void carregarLancamentos() {
		lancamentos = daoLancamento.consultar(getUsuarioSessao().getId());
	}

	public Pessoa getUsuarioSessao() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		return (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public DAOGeneric<Lancamento> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DAOGeneric<Lancamento> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public IDaoLancamento getDaoLancamento() {
		return daoLancamento;
	}

	public void setDaoLancamento(IDaoLancamento daoLancamento) {
		this.daoLancamento = daoLancamento;
	}

}
