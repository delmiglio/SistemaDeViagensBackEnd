/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Classes_Mapeamentos;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Guilherme
 */
@Embeddable
public class AlunoxVendaID implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendaId", nullable = false)
    private Venda venda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoaId", nullable = false)
    private Aluno aluno;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlunoxVendaID other = (AlunoxVendaID) obj;

        if (this.aluno != other.aluno && (this.aluno == null || !this.aluno.equals(other.aluno))) {
            return false;
        }

        if (this.venda != other.venda && (this.venda == null || !this.venda.equals(other.venda))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.aluno != null ? this.aluno.hashCode() : 0);
        hash = 61 * hash + (this.venda != null ? this.venda.hashCode() : 0);
        return hash;
    }

    public AlunoxVendaID(Aluno aluno, Venda venda) {
        this.aluno = aluno;
        this.venda = venda;

    }

    public AlunoxVendaID() {

    }

    /**
     * @return the venda
     */
    public Venda getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

}
