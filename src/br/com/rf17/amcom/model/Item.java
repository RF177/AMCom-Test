package br.com.rf17.amcom.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "item", schema = "public")
@SequenceGenerator(name = "SEQ_ITEM", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_ITEM")
public class Item implements Serializable {
    
	private static final long serialVersionUID = 300867350815561606L;

	@Id
    @Column(name = "oid")   
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM")
    private Long oid;

    @Column(name = "observacao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "valor", precision = 8, scale = 2, nullable = false)
    private double valor;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
