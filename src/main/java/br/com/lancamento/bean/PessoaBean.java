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
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.file.UploadedFile;

import br.com.lancamento.dao.DAOGeneric;
import br.com.lancamento.entidades.Pessoa;
import br.com.lancamento.repository.IDaoPessoa;
import br.com.lancamento.util.JsfUtil;
import br.com.lancamento.util.PasswordEncryptor;

@ViewScoped
@Named(value = "pessoaBean")
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAOGeneric<Pessoa> daoGeneric;

	@Inject
	private IDaoPessoa idaoPessoa;

	private Pessoa pessoa = new Pessoa();

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	private UploadedFile arquivoFoto;
	
	
	public void criptografarSenha() {
		if(pessoa.getId() != null) {
			Pessoa p = daoGeneric.bucarPorId(Pessoa.class, pessoa.getId());
			if(!pessoa.getSenha().equals(p.getSenha())) {
				pessoa.setSenha(PasswordEncryptor.encryptPassword(pessoa.getSenha()));
			}
		} else {
			pessoa.setSenha(PasswordEncryptor.encryptPassword(pessoa.getSenha()));
		}
	}

	public void salvar() {
		criptografarSenha();
		pessoa = daoGeneric.salvar(pessoa);
		carregarPessoas();
		novo();
		JsfUtil.addSucessMessage("Usuário salvo com sucesso");
	}

	public String buscarPorId() {
		pessoa = daoGeneric.bucarPorId(Pessoa.class, pessoa.getId());
		return "";
	}

	public void deletar() {
		buscarPorId();
		daoGeneric.deletePorId(pessoa, pessoa.getId());
		carregarPessoas();
		pessoa = new Pessoa();
		JsfUtil.addSucessMessage("Usuário excluido com sucesso");
	}

	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.getListEntity(Pessoa.class);
	}

	public void novo() {
		pessoa = new Pessoa();
	}

	public String logar() {

		Pessoa pessoaUser = null;

		try {
			pessoaUser = idaoPessoa.consultarUsuario(pessoa.getLogin(), PasswordEncryptor.encryptPassword(pessoa.getSenha()));
		} catch (NoResultException e) {
			e.printStackTrace();
			JsfUtil.addErroMessage("Login ou senha inválidos");
		}
		
		if(pessoaUser != null && !Boolean.TRUE.equals(pessoaUser.getAtivo())) {
			JsfUtil.addAlertMessage("Usuário inativo");
			return null;
		}

		if (pessoaUser != null && Boolean.TRUE.equals(pessoaUser.getAtivo())) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser);

			return "/index.jsf?faces-redirect=true";
		}

		return null;
	}

	public String logout() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");

		HttpServletRequest httServletRequest = (HttpServletRequest) context.getCurrentInstance().getExternalContext()
				.getRequest();
		httServletRequest.getSession().invalidate();

		return "/login.jsf?faces-redirect=true";
	}

	public boolean permiteAcesso(String perfil) {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");

		return pessoaUser.getPerfil().equals(perfil);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DAOGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DAOGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public UploadedFile getArquivoFoto() {
		return arquivoFoto;
	}

	public void setArquivoFoto(UploadedFile arquivoFoto) {
		this.arquivoFoto = arquivoFoto;
	}

}
