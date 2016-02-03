/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Delmiglio
 */
@RestController
public class lookupController {

    @RequestMapping("nivel-acesso")
    @ResponseBody
    public String nivelAcesso() {
        Map<Integer, String> niveisAcesso = new HashMap<Integer, String>();
        niveisAcesso.put(1, "Administrador");
        niveisAcesso.put(2, "Aluno");
        niveisAcesso.put(3, "Faculdade");
        niveisAcesso.put(4, "Representante");
        niveisAcesso.put(5, "Dependente");
        JsonElement jsonNiveis = new Gson().toJsonTree(niveisAcesso);
        return jsonNiveis.toString();
    }

    @RequestMapping("vooComEscala")
    @ResponseBody
    public String vooComEscala() {
        Map<Integer, String> tiposVoo = new HashMap<Integer, String>();
        tiposVoo.put(1, "Com Escala");
        tiposVoo.put(2, "Sem Escala");
        JsonElement jsonTipos = new Gson().toJsonTree(tiposVoo);
        return jsonTipos.toString();

    }

    @RequestMapping("grauParentesco")
    @ResponseBody
    public String grauParentesco() {

        Map<Integer, String> grausParentesco = new HashMap<Integer, String>();
        grausParentesco.put(1, "Mãe");
        grausParentesco.put(2, "Pai");
        grausParentesco.put(3, "Tio(a)");
        grausParentesco.put(4, "Irmã/Irmão");
        grausParentesco.put(5, "Primo");
        grausParentesco.put(6, "Vó/Vô");
        grausParentesco.put(7, "Outro");
        JsonElement grausDeParentesco = new Gson().toJsonTree(grausParentesco);
        return grausDeParentesco.toString();

    }

    @RequestMapping("tiposEvento")
    @ResponseBody
    public String tiposEvento() {

        Map<Integer, String> tiposEvento = new HashMap<Integer, String>();
        tiposEvento.put(1, "Formatura");
        tiposEvento.put(2, "Aventura");
        tiposEvento.put(3, "Debutante");
        JsonElement jsonEventos = new Gson().toJsonTree(tiposEvento);
        return jsonEventos.toString();
    }

    @RequestMapping("bandeiras")
    @ResponseBody
    public String bandeiras() {

        Map<Integer, String> tiposBandeira = new HashMap<Integer, String>();
        tiposBandeira.put(1, "MasterCard");
        tiposBandeira.put(2, "Visa");
        tiposBandeira.put(3, "DinnersClub");
        tiposBandeira.put(4, "OroCred");
        JsonElement jsonBandeiras = new Gson().toJsonTree(tiposBandeira);
        return jsonBandeiras.toString();
    }

}
