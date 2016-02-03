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
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "Faculdade")
public class Faculdade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo")
    private long faculdadeId;

    @Column(name = "Nome", nullable = false)
    private String nome;

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

    @Column(name = "Email", nullable = false)
    private String email;
    
    @Column(name = "Senha", nullable = false)
    private String senha;

    @Column(name = "Telefone", nullable = false)
    private String telefone;

    @Column(name = "Diretor", nullable = true)
    private String diretor;

    @Column(name = "Informacoes", nullable = true)
    private String informacoes;
    
    @Column(name = "Ativo", nullable = false)
    private Integer ativo;

    @OneToMany(mappedBy = "faculdade", fetch = FetchType.EAGER, targetEntity = Turma.class)
    private Set<Turma> turmas = new HashSet<Turma>();

    public Faculdade() {

    }

    public Faculdade(String nome, String cep, String endereco, String complemento,
            String cidade, String estado, String email,String senha, String telefone, String diretor,
            String informacoes) {
        this.nome = nome;
        this.cep = cep;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.diretor = diretor;
        this.informacoes = informacoes;
        this.ativo = 1;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the diretor
     */
    public String getDiretor() {
        return diretor;
    }

    /**
     * @param diretor the diretor to set
     */
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    /**
     * @return the informacoes
     */
    public String getInformacoes() {
        return informacoes;
    }

    /**
     * @param informacoes the informacoes to set
     */
    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    /**
     * @return the turmas
     */
    public Set<Turma> getTurmas() {
        return turmas;
    }

    /**
     * @param turmas the turmas to set
     */
    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
    }

    /**
     * @return the faculdadeId
     */
    public long getFaculdadeId() {
        return faculdadeId;
    }

    /**
     * @param faculdadeId the faculdadeId to set
     */
    public void setFaculdadeId(long faculdadeId) {
        this.faculdadeId = faculdadeId;
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

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
