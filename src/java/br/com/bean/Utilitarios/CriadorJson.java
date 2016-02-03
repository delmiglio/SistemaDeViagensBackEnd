/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Utilitarios;

import com.google.gson.*;

/**
 *
 * @author Guilherme
 */
public class CriadorJson {

    public static String criaJsonErro(Exception e, String mensagem) {
        JsonObject jsonMensagemErro = new JsonObject();
        jsonMensagemErro.addProperty("successo", "false");
        jsonMensagemErro.addProperty("mensagem", mensagem);
        jsonMensagemErro.addProperty("exception", e.getMessage());
        Gson gsonCorreto = new Gson();
        return gsonCorreto.toJson(jsonMensagemErro);
    }

    public static String criaJsonSucesso(String mensagem) {
        JsonObject jsonMensagemSucesso = new JsonObject();
        jsonMensagemSucesso.addProperty("successo", "true");
        jsonMensagemSucesso.addProperty("mensagem", mensagem);
        Gson gsonCorreto = new Gson();
        return gsonCorreto.toJson(jsonMensagemSucesso);
    }
    
    
}
