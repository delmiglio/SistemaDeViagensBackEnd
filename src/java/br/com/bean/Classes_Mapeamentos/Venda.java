/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Classes_Mapeamentos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "Venda")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo")
    private long vendaId;
    
    @Column(name = "Ativo", nullable = false)
    private Integer ativo;

    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER, targetEntity = Festa.class)
    private Set<Festa> festas = new HashSet<Festa>();

    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER, targetEntity = Viagem.class)
    private Set<Viagem> viagens = new HashSet<Viagem>();

    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER, targetEntity = Representante.class)
    private Set<Representante> representante = new HashSet<Representante>();
    
    @OneToOne(mappedBy = "venda",cascade = javax.persistence.CascadeType.ALL)
    private Pagamento pagamento;

    public Venda() {
        this.ativo = 1;
    }

    /**
     * @return the festas
     */
    public Set<Festa> getFestas() {
        return festas;
    }

    /**
     * @param festas the festas to set
     */
    public void setFestas(Set<Festa> festas) {
        this.festas = festas;
    }

    /**
     * @return the viagens
     */
    public Set<Viagem> getViagens() {
        return viagens;
    }

    /**
     * @param viagens the viagens to set
     */
    public void setViagens(Set<Viagem> viagens) {
        this.viagens = viagens;
    }

    /**
     * @return the representante
     */
    public Set<Representante> getRepresentante() {
        return representante;
    }

    /**
     * @param representante the representante to set
     */
    public void setRepresentante(Set<Representante> representante) {
        this.representante = representante;
    }

    /**
     * @return the pagamento
     */
    public Pagamento getPagamento() {
        return pagamento;
    }

    /**
     * @param pagamento the pagamento to set
     */
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    /**
     * @return the vendaId
     */
    public long getVendaId() {
        return vendaId;
    }

    /**
     * @param vendaId the vendaId to set
     */
    public void setVendaId(long vendaId) {
        this.vendaId = vendaId;
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
