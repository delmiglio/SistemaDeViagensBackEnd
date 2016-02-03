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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "Turma")
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo")
    private long turmaId;

    @Column(name = "QuantidadeDeAlunos", length = Integer.MAX_VALUE, nullable = true)
    private Integer quantidadeAlunos;

    @Column(name = "Serie", length = Integer.MAX_VALUE, nullable = false)
    private Integer serie;

    @Column(name = "Descricao", nullable = true)
    private String descricao;
    
    @Column(name = "Ativo", nullable = false)
    private Integer ativo;

    @OneToMany(mappedBy = "turma", fetch = FetchType.EAGER, targetEntity = Aluno.class)
    @Cascade(CascadeType.ALL)
    private Set<Aluno> alunos = new HashSet<Aluno>();

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Faculdade.class)
    @JoinColumn(name="faculdadeId", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    private Faculdade faculdade;

    public Turma() {

    }

    public Turma(Integer quantidadedeAlunos, Integer serie, String descricao) {
        this.quantidadeAlunos = quantidadedeAlunos;
        this.serie = serie;
        this.descricao = descricao;
    }

    

    /**
     * @return the quantidadeAlunos
     */
    public Integer getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    /**
     * @param quantidadeAlunos the quantidadeAlunos to set
     */
    public void setQuantidadeAlunos(Integer quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    /**
     * @return the serie
     */
    public Integer getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    /**
     * @return the alunos
     */
    public Set<Aluno> getAlunos() {
        return alunos;
    }

    /**
     * @param alunos the alunos to set
     */
    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    /**
     * @return the faculdade
     */
    public Faculdade getFaculdade() {
        return faculdade;
    }

    /**
     * @param faculdade the faculdade to set
     */
    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
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
     * @return the turmaId
     */
    public long getTurmaId() {
        return turmaId;
    }

    /**
     * @param turmaId the turmaId to set
     */
    public void setTurmaId(long turmaId) {
        this.turmaId = turmaId;
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
