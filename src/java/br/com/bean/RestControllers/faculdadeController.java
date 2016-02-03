/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Faculdade;
import br.com.bean.Classes_Mapeamentos.Turma;
import br.com.bean.Classes_Mapeamentos.Usuario;
import br.com.bean.Utilitarios.CollectionDeserializer;
import br.com.bean.Utilitarios.CriadorJson;
import br.com.bean.Utilitarios.HibernateProxyTypeAdapter;
import br.com.bean.Utilitarios.HibernateUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
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
public class faculdadeController {

    @RequestMapping(value = "insere-faculdade", method = RequestMethod.POST)
    public String post(@RequestBody String faculdade) throws HibernateException {
        Gson gson = new Gson();
        Faculdade f = gson.fromJson(faculdade, Faculdade.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            f.setAtivo(1);
            sessao.save(f);
            Usuario u = new Usuario();
            u.setNivelPermissao(3);
            u.setSenha(f.getSenha());
            u.setEmail(f.getEmail());
            u.setEndereco(f.getEndereco());
            u.setComplemento(f.getComplemento());
            u.setTelefone(f.getTelefone());
            u.setCep(f.getCep());
            u.setCidade(f.getCidade());
            u.setEstado(f.getEstado());
            u.setNome(f.getNome());
            u.setAtivo(1);
            sessao.save(u);
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

    @RequestMapping(value = "atualiza-faculdade", method = RequestMethod.POST)
    public String put(@RequestBody String faculdade) {
        Gson gson = new Gson();
        Faculdade fnew = gson.fromJson(faculdade, Faculdade.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Faculdade fold = buscaFaculdadeParaOperacao(fnew.getFaculdadeId());
            String jsonUsuarioAtualizacao = usuarioController.atualizaUsuario(fold.getEmail(), fold.getSenha(), fnew.getEmail(), fnew.getSenha());
            fold.setDiretor(fnew.getDiretor());
            fold.setCep(fnew.getCep());
            fold.setCidade(fnew.getCidade());
            fold.setComplemento(fnew.getComplemento());
            fold.setInformacoes(fnew.getInformacoes());
            fold.setEmail(fnew.getEmail());
            fold.setSenha(fnew.getSenha());
            fold.setEndereco(fnew.getEndereco());
            fold.setEstado(fnew.getEstado());
            fold.setNome(fnew.getNome());
            fold.setTelefone(fnew.getTelefone());
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

    @RequestMapping(value = "buscaPorId-faculdade", method = RequestMethod.GET)
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Faculdade f = (Faculdade) sessao.get(Faculdade.class, id);
            Gson gson = new Gson();
            f.setTurmas(null);
            return gson.toJson(f, Faculdade.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-faculdades", method = RequestMethod.GET)
    public String get() throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Faculdade.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
            query.add(ativo);
            List<Faculdade> listaDeFaculdades = query.list();
            // Retira Referencia Circular
            for (Faculdade f : listaDeFaculdades) {
                f.setTurmas(null);
            }
            Type TipolistaDeFaculdades = new TypeToken<List<Faculdade>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            String faculdades = gson.toJson(listaDeFaculdades, TipolistaDeFaculdades);
            return faculdades;
        } catch (HibernateException e) {
            transacao.rollback();
            return null;
        } catch (Exception e) {
            String erro = e.getMessage();
            return null;
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-faculdade-email-senha", method = RequestMethod.GET)
    @ResponseBody
    public static String buscaFaculdade(@RequestParam String email, @RequestParam String senha) throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Faculdade.class)
                    .add(Restrictions.eq("email", email))
                    .add(Restrictions.eq("senha", senha))
                    .add(Restrictions.eq("ativo", 1));

            Faculdade f = (Faculdade) query.uniqueResult();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            f.setTurmas(null);
            return gson.toJson(f, Faculdade.class);
        } catch (HibernateException e) {
            transacao.rollback();
            return null;
        }
    }

    @RequestMapping(value = "deleta-faculdade", method = RequestMethod.GET)
    public String delete(long id) throws SQLException, ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Faculdade f = buscaFaculdadeParaOperacao(id);
            f.setAtivo(0);
            for (Turma t : f.getTurmas()) {
                t.setAtivo(0);
            }
            sessao.update(f);
            String u = usuarioController.buscaUsuarioNomeSenha(f.getEmail(), f.getSenha());
            if (u != null) {
                Usuario usuario = new Gson().fromJson(u, Usuario.class);
                usuario.setAtivo(0);
                sessao.update(usuario);
            }
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

    public static Faculdade buscaFaculdadeParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Faculdade f = (Faculdade) sessao.get(Faculdade.class, id);
            return f;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }

}
