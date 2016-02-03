/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Utilitarios;

import java.beans.PropertyEditorSupport;

/**
 *
 * @author Programador
 */
public class ConversorFloat extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            String valor = text.replace(',', '.');
            float numero = Float.parseFloat(valor);
            setValue(numero);
        } catch (Exception erro) {
            setValue(0);
        }
    }

    @Override
    public String getAsText() throws IllegalArgumentException {

        float valor = (float) getValue();
        return String.format("%.2f", valor);
    }
}
