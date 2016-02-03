/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Utilitarios;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Programador
 * O objetivo desta classe é ensinar o spring como converter um string no tipo java.util.date e vice-versa
 
 */
public class ConversorData extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
             DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             LocalDate ld = LocalDate.parse(text, formatador);                         
             setValue(ld);
        } catch (Exception erro) {
            setValue(null);
        }       
    }

    @Override
    public String getAsText() throws IllegalArgumentException {        
        
        LocalDate data = (LocalDate)(getValue());                 
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");                
        return data.format(formatador);
    }
    
}

