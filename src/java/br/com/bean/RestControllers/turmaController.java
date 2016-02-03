/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Faculdade;
import br.com.bean.Classes_Mapeamentos.Turma;
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
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Delmiglio
 */
@RestController
public class turmaController {

    @RequestMapping(value = "insere-turma", method = RequestMethod.POST)
    public String post(@RequestBody String turma, @RequestParam long faculdadeId) throws HibernateException {
        Gson gson = new Gson();
        Turma t = gson.fromJson(turma, Turma.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Faculdade f = faculdadeController.buscaFaculdadeParaOperacao(faculdadeId);
            t.setFaculdade(f);
            f.getTurmas().add(t);
            t.setAtivo(1);
            sessao.saveOrUpdate(t);
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

    @RequestMapping(value = "atualiza-turma", method = RequestMethod.POST)
    public String put(@RequestBody String turma, @RequestParam long faculdadeId) {
        Gson gson = new Gson();
        Turma tnew = gson.fromJson(turma, Turma.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Turma told = buscaTurmaParaOperacao(tnew.getTurmaId());
            told.setDescricao(tnew.getDescricao());
            told.setQuantidadeAlunos(tnew.getQuantidadeAlunos());
            told.setSerie(tnew.getSerie());
            sessao.update(told);
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

    @RequestMapping(value = "buscaPorId-turma", method = RequestMethod.GET)
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Turma t = (Turma) sessao.get(Turma.class, id);
            Gson gson = new Gson();
            t.setFaculdade(null);
            return gson.toJson(t, Turma.class);

        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-turmas", method = RequestMethod.GET)
    @ResponseBody
    public String buscaTurmas(long faculdadeId) throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria turmaCrit = sessao.createCriteria(Turma.class);
            turmaCrit.add(Restrictions.eq("ativo", 1));
            Criteria faculdadeCrit = turmaCrit.createCriteria("faculdade");
            faculdadeCrit.add(Restrictions.eq("faculdadeId", faculdadeId));
            turmaCrit.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
            List<Turma> listaDeTurmas = turmaCrit.list();
            for (Turma t : listaDeTurmas) {
                t.setFaculdade(null);
            }
            Type TipolistaDeTurmas = new TypeToken<List<Turma>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            return gson.toJson(listaDeTurmas, TipolistaDeTurmas);
        } catch (HibernateException e) {
            transacao.rollback();
            return null;
        }
        finally{
            sessao.close();
        }
    }

    @RequestMapping(value = "deleta-turma", method = RequestMethod.GET)
    public String delete(long id) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Turma t = buscaTurmaParaOperacao(id);
            t.setAtivo(0);
            sessao.update(t);
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

    public static Turma buscaTurmaParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Turma t = (Turma) sessao.get(Turma.class, id);
            return t;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }

}
