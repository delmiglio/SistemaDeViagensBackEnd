/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Pagamento;
import br.com.bean.Classes_Mapeamentos.Venda;
import br.com.bean.Utilitarios.CollectionDeserializer;
import br.com.bean.Utilitarios.CriadorJson;
import br.com.bean.Utilitarios.HibernateUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Delmiglio
 */
@RestController
public class vendaController {
    
    @RequestMapping(value = "insere-venda", method = RequestMethod.POST)
    public String post(@RequestBody String venda) throws HibernateException {
        Gson gson = new Gson();
        Venda v = gson.fromJson(venda, Venda.class);
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

    @RequestMapping(value = "buscaPorId-venda", method = RequestMethod.GET)
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Venda v = (Venda) sessao.get(Pagamento.class, id);
            Gson gson = new Gson();
            return gson.toJson(v, Venda.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-vendas", method = RequestMethod.GET)
    public String get() throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Venda.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.add(ativo);
            List<Venda> listaDeVendas = query.list();
            Type TipolistaDeVendas = new TypeToken<List<Venda>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            return gson.toJson(listaDeVendas, TipolistaDeVendas);
        } catch (HibernateException e) {
            transacao.rollback();
            return null;
        }
    }

    @RequestMapping(value = "deleta-venda", method = RequestMethod.GET)
    public String delete(long id) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Venda v = buscaVendaParaOperacao(id);
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

    public static Venda buscaVendaParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Venda v = (Venda) sessao.get(Venda.class, id);
            return v;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }
    
}
