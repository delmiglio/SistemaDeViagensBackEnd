/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Aluno;
import br.com.bean.Classes_Mapeamentos.Viagem;
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
public class alunoController {

    @RequestMapping(value = "insere-aluno", method = RequestMethod.POST)
    public String post(@RequestBody String aluno) throws HibernateException {
        Gson gson = new Gson();
        Aluno a = gson.fromJson(aluno, Aluno.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            a.setAtivo(1);
            sessao.save(a);
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

    @RequestMapping(value = "atualiza-aluno", method = RequestMethod.POST)
    public String put(@RequestBody String aluno) {
        Gson gson = new Gson();
        Aluno anew = gson.fromJson(aluno, Aluno.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Aluno aold = buscaAlunoParaOperacao(anew.getPessoaId());
            aold.setCelular(anew.getCelular());
            aold.setCep(anew.getCep());
            aold.setCidade(anew.getCidade());
            aold.setComplemento(anew.getComplemento());
            aold.setCpf(anew.getCpf());
            aold.setDataNascimento(anew.getDataNascimento());
            aold.setEmail(anew.getEmail());
            aold.setEndereco(anew.getEndereco());
            aold.setEstado(anew.getEstado());
            aold.setNome(anew.getNome());
            aold.setNumeroCarteiraFaculdade(anew.getNumeroCarteiraFaculdade());
            aold.setRg(anew.getRg());
            aold.setSemestre(anew.getSemestre());
            aold.setTelefone(anew.getTelefone());
            sessao.update(aold);
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

    @RequestMapping(value = "buscaPorId-aluno", method = RequestMethod.GET)
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Aluno a = (Aluno) sessao.get(Aluno.class, id);
            Gson gson = new Gson();
            return gson.toJson(a, Aluno.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-alunos", method = RequestMethod.GET)
    public String get() throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Aluno.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.add(ativo);
            List<Aluno> listaDeAlunos = query.list();
            Type TipolistaDeAlunos = new TypeToken<List<Aluno>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            return gson.toJson(listaDeAlunos, TipolistaDeAlunos);
        } catch (HibernateException e) {
            transacao.rollback();
            return null;
        }
    }

    @RequestMapping(value = "deleta-aluno", method = RequestMethod.GET)
    public String delete(long id) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Aluno a = buscaAlunoParaOperacao(id);
            a.setAtivo(0);
            sessao.update(a);
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

    public static Aluno buscaAlunoParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Aluno a = (Aluno) sessao.get(Aluno.class, id);
            return a;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }
}
