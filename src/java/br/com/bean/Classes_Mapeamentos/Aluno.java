/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Classes_Mapeamentos;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Guilherme
 */
public class Aluno extends Pessoa {

   
    private String numeroCarteiraFaculdade;
    private String curso;
    private Integer semestre;
    private Turma turma;
    private Set<Pagamento> pagamentos = new HashSet<Pagamento>();
    private Set<Dependente> dependentes = new HashSet<Dependente>();
    
    public Aluno(){
        
    }
    
    public Aluno(String numeroCarteiraFaculdade, String curso, Integer semestre){
        this.numeroCarteiraFaculdade = numeroCarteiraFaculdade;
        this.semestre = semestre;
        this.curso = curso;
    }

  

    /**
     * @return the numeroCarteiraFaculdade
     */
    public String getNumeroCarteiraFaculdade() {
        return numeroCarteiraFaculdade;
    }

    /**
     * @param numeroCarteiraFaculdade the numeroCarteiraFaculdade to set
     */
    public void setNumeroCarteiraFaculdade(String numeroCarteiraFaculdade) {
        this.numeroCarteiraFaculdade = numeroCarteiraFaculdade;
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * @return the semestre
     */
    public Integer getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    /**
     * @return the turma
     */
    public Turma getTurma() {
        return turma;
    }

    /**
     * @param turma the turma to set
     */
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    /**
     * @return the pagamentos
     */
    public Set<Pagamento> getPagamentos() {
        return pagamentos;
    }

    /**
     * @param pagamentos the pagamentos to set
     */
    public void setPagamentos(Set<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    /**
     * @return the dependentes
     */
    public Set<Dependente> getDependentes() {
        return dependentes;
    }

    /**
     * @param dependentes the dependentes to set
     */
    public void setDependentes(Set<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    

   
}
