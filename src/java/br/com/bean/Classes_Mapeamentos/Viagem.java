/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Classes_Mapeamentos;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "Viagem")
public class Viagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo")
    private long viagemId;

    @Column(name = "Ativo", nullable = false)
    private Integer ativo;

    @Column(name = "Origem", nullable = false)
    private String origem;

    @Column(name = "Destino", nullable = false)
    private String destino;

    @Column(name = "DataIda")
    private String dataIda;

    @Column(name = "DataVolta")
    private String dataVolta;

    @Column(name = "VooComEscala", nullable = false)
    private short vooComEscala;

    @Column(name = "Preco", precision = 10, scale = 2, nullable = false)
    private float preco;

    @Column(name = "Hotel", nullable = false)
    private String hotel;

    @Column(name = "Descricao", nullable = true)
    private String descricao;

    @Column(name = "Foto", nullable = false)
    private String foto;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Venda.class, optional = true)
    @JoinColumn(name = "vendaId", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    private Venda venda;

    public Viagem(String origem, String destino, String dataIda, String dataVolta, short vooComEscala,
            float preco, String hotel, String descricao, String foto) throws ParseException {
        this.origem = origem;
        this.destino = destino;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.vooComEscala = vooComEscala;
        this.preco = preco;
        this.hotel = hotel;
        this.descricao = descricao;
        this.foto = foto;
        this.ativo = 1;
    }

    public Viagem() {

    }

    /**
     * @return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the dataIda
     */
    public String getDataIda() {
        return dataIda;
    }

    /**
     * @param dataIda the dataIda to set
     */
    public void setDataIda(String dataIda) {
        this.dataIda = dataIda;
    }

    /**
     * @return the dataVolta
     */
    public String getDataVolta() {
        return dataVolta;
    }

    /**
     * @param dataVolta the dataVolta to set
     */
    public void setDataVolta(String dataVolta) {
        this.dataVolta = dataVolta;
    }

    /**
     * @return the vooComEscala
     */
    public short getVooComEscala() {
        return vooComEscala;
    }

    /**
     * @param vooComEscala the vooComEscala to set
     */
    public void setVooComEscala(short vooComEscala) {
        this.vooComEscala = vooComEscala;
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
     * @return the hotel
     */
    public String getHotel() {
        return hotel;
    }

    /**
     * @param hotel the hotel to set
     */
    public void setHotel(String hotel) {
        this.hotel = hotel;
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
     * @return the viagemId
     */
    public long getViagemId() {
        return viagemId;
    }

    /**
     * @param viagemId the viagemId to set
     */
    public void setViagemId(long viagemId) {
        this.viagemId = viagemId;
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
