/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Classes_Mapeamentos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Festa")
public class Festa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo")
    private long festaId;

    @Column(name = "CEP", nullable = false)
    private String cep;

    @Column(name = "Endereco", nullable = false)
    private String endereco;

    @Column(name = "Complemento", nullable = true)
    private String complemento;

    @Column(name = "Cidade", nullable = false)
    private String cidade;

    @Column(name = "Estado", nullable = false)
    private String estado;

    @Column(name = "DataEvento", nullable = true)
    private String dataDoEvento;

    @Column(name = "TipoEvento", length = Integer.MAX_VALUE)
    private Integer tipoEvento;

    @Column(name = "Preco", precision = 10, scale = 2, nullable = false)
    private float preco;

    @Column(name = "Descricao", nullable = true)
    private String descricao;

    @Column(name = "Foto", nullable = true)
    private String foto;
    
    @Column(name = "Ativo", nullable = false)
    private Integer ativo;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Venda.class, optional = true)
    @JoinColumn(name = "vendaId", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    private Venda venda;

    public Festa() {

    }

    public Festa(String cep, String endereco, String complemento,
            String cidade, String estado, String dataDoEvento, Integer tipoEvento,
            float preco, String descricao, String foto) {
        this.cep = cep;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.dataDoEvento = dataDoEvento;
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
        this.preco = preco;
        this.foto = foto;
        this.ativo = 1;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the dataDoEvento
     */
    public String getDataDoEvento() {
        return dataDoEvento;
    }

    /**
     * @param dataDoEvento the dataDoEvento to set
     */
    public void setDataDoEvento(String dataDoEvento) {
        this.dataDoEvento = dataDoEvento;
    }

    /**
     * @return the tipoEvento
     */
    public Integer getTipoEvento() {
        return tipoEvento;
    }

    /**
     * @param tipoEvento the tipoEvento to set
     */
    public void setTipoEvento(Integer tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    /**
     * @return the preco
     */
    public float getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
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
     * @return the festaId
     */
    public long getFestaId() {
        return festaId;
    }

    /**
     * @param festaId the festaId to set
     */
    public void setFestaId(long festaId) {
        this.festaId = festaId;
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
