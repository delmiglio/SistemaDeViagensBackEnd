/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Classes_Mapeamentos;

/**
 *
 * @author Guilherme
 */
public class Usuario extends Pessoa {

    private Integer nivelPermissao;

    public Usuario() {

    }

    public Usuario(Integer nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }

    /**
     * @return the nivelPermissao
     */
    public Integer getNivelPermissao() {
        return nivelPermissao;
    }

    /**
     * @param nivelPermissao the nivelPermissao to set
     */
    public void setNivelPermissao(Integer nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }

}
