/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Festa;
import br.com.bean.Utilitarios.CollectionDeserializer;
import br.com.bean.Utilitarios.CriadorJson;
import br.com.bean.Utilitarios.HibernateUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guilherme
 */
@RestController
public class festaController {

    @RequestMapping(value = "insere-festa", method = RequestMethod.POST)
    public String post(@RequestBody String festa) throws HibernateException {
        Gson gson = new Gson();
        Festa f = gson.fromJson(festa, Festa.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            f.setAtivo(1);
            sessao.save(f);
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

    @RequestMapping(value = "atualiza-festa", method = RequestMethod.POST)
    public String put(@RequestBody String festa) {
        Gson gson = new Gson();
        Festa fnew = gson.fromJson(festa, Festa.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Festa fold = buscaFestaParaOperacao(fnew.getFestaId());
            fold.setCep(fnew.getCep());
            fold.setCidade(fnew.getCidade());
            fold.setComplemento(fnew.getComplemento());
            fold.setDataDoEvento(fnew.getDataDoEvento());
            fold.setFoto(fnew.getFoto());
            fold.setDescricao(fnew.getDescricao());
            fold.setEndereco(fnew.getEndereco());
            fold.setEstado(fnew.getEstado());
            fold.setTipoEvento(fnew.getTipoEvento());
            fold.setPreco(fnew.getPreco());
            sessao.update(fold);
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

    @RequestMapping(value = "buscaPorId-festa", method = RequestMethod.GET)
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Festa f = (Festa) sessao.get(Festa.class, id);
            Gson gson = new Gson();
            return gson.toJson(f, Festa.class);
        } catch (HibernateException e) {
            return e.getMessage();
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-festas", method = RequestMethod.GET)
    public String get() {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Festa.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.add(ativo);
            List<Festa> listaDeFestas = query.list();
            Type TipolistaDeFestas = new TypeToken<List<Festa>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer()).create();

            return gson.toJson(listaDeFestas, TipolistaDeFestas);
        } catch (HibernateException e) {
            transacao.rollback();
            return CriadorJson.criaJsonErro(e, null);
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "deleta-festa", method = RequestMethod.GET)
    public String delete(long id) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Festa f = buscaFestaParaOperacao(id);
            f.setAtivo(0);
            sessao.update(f);
            transacao.commit();
            String jsonMensagemSucesso = CriadorJson.criaJsonSucesso("Dados Deletados Com Sucesso");
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

    public static Festa buscaFestaParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Festa f = (Festa) sessao.get(Festa.class, id);
            return f;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }
}
