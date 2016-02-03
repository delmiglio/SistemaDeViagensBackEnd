/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Classes_Mapeamentos;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "AlunoxVenda")
public class AlunoxVenda implements Serializable {

    @EmbeddedId
    private AlunoxVendaID alunoxVendaId;

    @Column(name = "Parcelas", length = Integer.MAX_VALUE)
    private Integer parcelas;

    @Column(name = "Preco", precision = 10, scale = 2, nullable = false)
    private float valorTotal;

    public AlunoxVenda(AlunoxVendaID alunoxvenda, int parcelas, float valorTotal) {
        this.alunoxVendaId = alunoxvenda;
        this.parcelas = parcelas;
        this.valorTotal = valorTotal;
    }

    public AlunoxVenda() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlunoxVenda other = (AlunoxVenda) obj;
        if (this.alunoxVendaId != other.alunoxVendaId && (this.alunoxVendaId == null || !this.alunoxVendaId.equals(other.alunoxVendaId))) {
            return false;
        }
        if (!Objects.equals(this.parcelas, other.parcelas)) {
            return false;
        }

        if (this.valorTotal != other.valorTotal) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.alunoxVendaId != null ? this.alunoxVendaId.hashCode() : 0);
        hash = 89 * hash + this.parcelas;
        hash = 89 * hash + Integer.class.cast(this.valorTotal);
        return hash;
    }

    /**
     * @return the alunoxVendaId
     */
    public AlunoxVendaID getAlunoxVendaId() {
        return alunoxVendaId;
    }

    /**
     * @param alunoxVendaId the alunoxVendaId to set
     */
    public void setAlunoxVendaId(AlunoxVendaID alunoxVendaId) {
        this.alunoxVendaId = alunoxVendaId;
    }

    /**
     * @return the parcelas
     */
    public Integer getParcelas() {
        return parcelas;
    }

    /**
     * @param parcelas the parcelas to set
     */
    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    /**
     * @return the valorTotal
     */
    public float getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

}
