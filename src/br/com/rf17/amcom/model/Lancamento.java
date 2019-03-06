package br.com.rf17.amcom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity
@Table(name = "lancamento", schema = "public")
@SequenceGenerator(name = "SEQ_LANCAMENTO", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_LANCAMENTO")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = -2293844909606885466L;

	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LANCAMENTO")
	private Long oid;

	@Column(name = "dt_inicial", columnDefinition = "date", nullable = false)
	private Date dataInicial;

	@Column(name = "dt_final", columnDefinition = "date", nullable = false)
	private Date dataFinal;

	@Column(name = "vl_total", precision = 8, scale = 2, nullable = false)
	private double valorTotal;

	@Column(name = "observacao", length = 1000)
	private String observacao;

	@OneToMany(mappedBy = "lancamento", targetEntity = LancamentoItem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LancamentoItem> lancamentoItems;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<LancamentoItem> getLancamentoItems() {
		return lancamentoItems;
	}

	public void setLancamentoItems(List<LancamentoItem> lancamentoItems) {
		this.lancamentoItems = lancamentoItems;
	}

}
