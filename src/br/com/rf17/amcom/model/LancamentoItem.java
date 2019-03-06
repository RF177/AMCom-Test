package br.com.rf17.amcom.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "lancamentoitem", schema = "public")
@SequenceGenerator(name = "SEQ_LANCAMENTOITEM", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_LANCAMENTOITEM")
public class LancamentoItem implements Serializable {

	private static final long serialVersionUID = -6884622129057963023L;

	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LANCAMENTOITEM")
	private Long oid;

	@ManyToOne
	@JoinColumn(name = "oid_lancamento", referencedColumnName = "oid", nullable = false, foreignKey = @ForeignKey(name = "FK_LANCAMENTOITEM_LANCAMENTO"))
	private Lancamento lancamento;

	@ManyToOne
	@JoinColumn(name = "oid_item", referencedColumnName = "oid", nullable = false, foreignKey = @ForeignKey(name = "FK_LANCAMENTOITEM_ITEM"))
	private Item item;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
