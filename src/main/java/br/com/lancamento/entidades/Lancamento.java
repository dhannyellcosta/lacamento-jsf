package br.com.lancamento.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

@Entity
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O campo nota físcal é obrigatório")
	@Column(name = "numero_nota_fiscal")
	private String numeroNotaFiscal;

	@NotEmpty(message = "O campo natureza da operação é obrigatório")
	@Column(name = "natureza_operacao")
	private String naturezaOperacao;

	@NotEmpty(message = "O campo empresa origem é obrigatório")
	@Column(name = "empresa_origem")
	private String empresaOrigem;

	@NotEmpty(message = "O campo empresa destino é obrigatório")
	@Column(name = "empresa_destino")
	private String empresaDestino;

	@ManyToOne(optional = false)
	@ForeignKey(name = "usuario_fk")
	private Pessoa pessoa;

	@NotNull(message = "O campo data inicial é obrigatório")
	@Column(name = "dt_emissao")
	private Date dataEmissao;

	@NotNull(message = "O campo data final é obrigatório")
	@Column(name = "dt_saida")
	private Date dataSaida;

	@Column(name = "dt_cadastro", updatable = false)
	private Date dataCadastro;

	@Column(name = "dt_alteracao")
	private Date dataAlteracao;

	@Column
	@NotEmpty(message = "O campo tipo da nota é obrigatório")
	private String tipo;

	@Column(name = "valor_nota")
	@NotNull(message = "O campo valor da notal é obrigatório")
	
	private Double valorDaNota;

	@PrePersist
	public void prePersist() {
		setDataCadastro(new Date());
	}

	@PreUpdate
	public void preUpdate() {
		setDataAlteracao(new Date());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public String getEmpresaOrigem() {
		return empresaOrigem;
	}

	public void setEmpresaOrigem(String empresaOrigem) {
		this.empresaOrigem = empresaOrigem;
	}

	public String getEmpresaDestino() {
		return empresaDestino;
	}

	public void setEmpresaDestino(String empresaDestino) {
		this.empresaDestino = empresaDestino;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValorDaNota() {
		return valorDaNota;
	}

	public void setValorDaNota(Double valorDaNota) {
		this.valorDaNota = valorDaNota;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", numeroNotaFiscal=" + numeroNotaFiscal + ", naturezaOperacao="
				+ naturezaOperacao + ", empresaOrigem=" + empresaOrigem + ", empresaDestino=" + empresaDestino
				+ ", pessoa=" + pessoa + ", dataEmissao=" + dataEmissao + ", dataSaida=" + dataSaida + ", dataCadastro="
				+ dataCadastro + ", dataAlteracao=" + dataAlteracao + ", tipo=" + tipo + ", valorDaNota=" + valorDaNota
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(id, other.id);
	}

}
