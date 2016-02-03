/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Viagem;
import br.com.bean.Utilitarios.CollectionDeserializer;
import br.com.bean.Utilitarios.CriadorJson;
import br.com.bean.Utilitarios.HibernateUtility;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guilherme
 */
@RestController
public class viagemController {

    @RequestMapping(value = "insere-viagem", method = RequestMethod.POST)
    @ResponseBody
    public String post(@RequestBody String viagem) throws HibernateException {
        Gson gson = new Gson();
        Viagem v = gson.fromJson(viagem, Viagem.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            v.setAtivo(1);
            sessao.save(v);
            String jsonMensagemSucesso = CriadorJson.criaJsonSucesso("Dados Salvos");
            return jsonMensagemSucesso;
        } catch (ConstraintViolationException c) {
            transacao.rollback();
            String jsonMensagemErro = CriadorJson.criaJsonErro(c, "Registro Esta Sendo utilizado");
            return jsonMensagemErro;
        } catch (HibernateException e) {
            transacao.rollback();
            String jsonMensagemErro = CriadorJson.criaJsonErro(e, null);
            return jsonMensagemErro;
        } finally {
            transacao.commit();
            sessao.close();
        }
    }

    @RequestMapping(value = "atualiza-viagem", method = RequestMethod.POST)
    public String put(@RequestBody String viagem) {
        Gson gson = new Gson();
        Viagem vnew = gson.fromJson(viagem, Viagem.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Viagem vold = buscaViagemParaOperacao(vnew.getViagemId());
            vold.setDataIda(vnew.getDataIda());
            vold.setDataVolta(vnew.getDataVolta());
            vold.setDescricao(vnew.getDescricao());
            vold.setDestino(vnew.getDestino());
            vold.setFoto(vnew.getFoto());
            vold.setHotel(vnew.getHotel());
            vold.setOrigem(vnew.getOrigem());
            vold.setPreco(vnew.getPreco());
            vold.setVooComEscala(vnew.getVooComEscala());
            sessao.update(vold);
            String jsonMensagemSucesso = CriadorJson.criaJsonSucesso("Dados Salvos");
            return jsonMensagemSucesso;
        } catch (ConstraintViolationException c) {
            transacao.rollback();
            String jsonMensagemErro = CriadorJson.criaJsonErro(c, "Registro Esta Sendo utilizado");
            return jsonMensagemErro;
        } catch (HibernateException e) {
            transacao.rollback();
            String jsonMensagemErro = CriadorJson.criaJsonErro(e, null);
            return jsonMensagemErro;
        } finally {
            transacao.commit();
            sessao.close();
        }
    }

    @RequestMapping(value = "buscaPorId-viagem", method = RequestMethod.GET)
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Viagem v = (Viagem) sessao.get(Viagem.class, id);
            Gson gson = new Gson();
            return gson.toJson(v, Viagem.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-viagens", method = RequestMethod.GET)
    public String get() throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Viagem.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.add(ativo);
            List<Viagem> listaDeViagens = query.list();
            Type TipolistaDeViagens = new TypeToken<List<Viagem>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            return gson.toJson(listaDeViagens, TipolistaDeViagens);
        } catch (HibernateException e) {
            transacao.rollback();
        } finally {
            sessao.close();
        }
        return null;
    }

    @RequestMapping(value = "deleta-viagem", method = RequestMethod.GET)
    public String delete(long id) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Viagem v = buscaViagemParaOperacao(id);
            v.setAtivo(0);
            sessao.update(v);
            String jsonMensagemSucesso = CriadorJson.criaJsonSucesso("Dados Deletados Com Sucesso");
            transacao.commit();
            return jsonMensagemSucesso;
        } catch (ConstraintViolationException c) {
            transacao.rollback();
            String jsonMensagemErro = CriadorJson.criaJsonErro(c, "Registro Esta Sendo utilizado");
            return jsonMensagemErro;
        } catch (HibernateException e) {
            transacao.rollback();
            String jsonMensagemErro = CriadorJson.criaJsonErro(e, null);
            return jsonMensagemErro;
        } finally {
            sessao.close();
        }
    }

    public Viagem buscaViagemParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Viagem v = (Viagem) sessao.get(Viagem.class, id);
            return v;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }

}
