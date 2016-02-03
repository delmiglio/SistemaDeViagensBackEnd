/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Classes_Mapeamentos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "Pagamento")
public class Pagamento implements Serializable {

    @Id
    @Column(name = "Codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pagamentoId;

    @Column(name = "NumeroCartao", nullable = false)
    private String numeroCartao;

    @Column(name = "Bandeira", nullable = false)
    private Integer bandeira;

    @Column(name = "Ativo", nullable = false)
    private Integer ativo;

    @Column(name = "DataVencimento", nullable = false)
    private String dataVencimento;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Aluno.class)
    @JoinColumn(name = "pessoaId", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    private Aluno aluno;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Venda venda;

    public Pagamento() {

    }

    public Pagamento(String numeroCartao, int bandeira, String dataVencimento) {
        this.numeroCartao = numeroCartao;
        this.bandeira = bandeira;
        this.dataVencimento = dataVencimento;
        this.ativo = 1;
    }

    /**
     * @return the numeroCartao
     */
    public String getNumeroCartao() {
        return numeroCartao;
    }

    /**
     * @param numeroCartao the numeroCartao to set
     */
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    /**
     * @return the bandeira
     */
    public Integer getBandeira() {
        return bandeira;
    }

    /**
     * @param bandeira the bandeira to set
     */
    public void setBandeira(Integer bandeira) {
        this.bandeira = bandeira;
    }

    /**
     * @return the dataVencimento
     */
    public String getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
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
     * @return the pagamentoId
     */
    public long getPagamentoId() {
        return pagamentoId;
    }

    /**
     * @param pagamentoId the pagamentoId to set
     */
    public void setPagamentoId(long pagamentoId) {
        this.pagamentoId = pagamentoId;
    }

    /**
     * @return the ativo
     */
    public Integer getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

}
