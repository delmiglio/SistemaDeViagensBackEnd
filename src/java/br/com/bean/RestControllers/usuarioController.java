/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Usuario;
import br.com.bean.Utilitarios.CollectionDeserializer;
import br.com.bean.Utilitarios.CriadorJson;
import br.com.bean.Utilitarios.HibernateUtility;
import br.com.bean.Utilitarios.TestaHibernate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
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
public class usuarioController {

    @RequestMapping(value = "insere-usuario", method = RequestMethod.POST)
    @ResponseBody
    public String post(@RequestBody String usuario) throws HibernateException {
        Gson gson = new Gson();
        Usuario u = gson.fromJson(usuario, Usuario.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
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

    @RequestMapping(value = "atualiza-usuario", method = RequestMethod.POST)
    @ResponseBody
    public String put(@RequestBody String usuario) {
        Gson gson = new Gson();
        Usuario unew = gson.fromJson(usuario, Usuario.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Usuario uold = buscaUsuarioParaOperacao(unew.getPessoaId());
            uold.setCelular(unew.getCelular());
            uold.setCep(unew.getCep());
            uold.setCidade(unew.getCidade());
            uold.setComplemento(unew.getComplemento());
            uold.setCpf(unew.getCpf());
            uold.setDataNascimento(unew.getDataNascimento());
            uold.setEmail(unew.getEmail());
            uold.setEndereco(unew.getEndereco());
            uold.setEstado(unew.getEstado());
            uold.setNivelPermissao(unew.getNivelPermissao());
            uold.setNome(unew.getNome());
            uold.setRg(unew.getRg());
            uold.setSenha(unew.getSenha());
            uold.setTelefone(unew.getTelefone());
            sessao.update(uold);
            transacao.commit();
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
            sessao.close();
        }
    }

    @RequestMapping(value = "buscaPorId-usuario", method = RequestMethod.GET)
    @ResponseBody
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Usuario u = (Usuario) sessao.get(Usuario.class, id);
            Gson gson = new Gson();
            return gson.toJson(u, Usuario.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Algo Aconteceu, Verificar");
        } finally {
            sessao.close();
        }

    }

    @RequestMapping(value = "buscaUsuario-nomeSenha", method = RequestMethod.GET)
    @ResponseBody
    public static String buscaUsuarioNomeSenha(String email, String senha) throws SQLException,ParseException {
        Session sessao = HibernateUtility.getSession();
        try {
            Criteria query = sessao.createCriteria(Usuario.class)
            .add(Restrictions.eq("email", email))
            .add(Restrictions.eq("senha", senha))
            .add(Restrictions.eq("ativo", 1));
            
            Usuario u = (Usuario) query.uniqueResult();
            Gson gson = new Gson();
            return gson.toJson(u, Usuario.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }

    }

    @RequestMapping(value = "busca-usuarios", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        Session sessao = HibernateUtility.getSession();
        try {
            Criteria query = sessao.createCriteria(Usuario.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.add(ativo);
            List<Usuario> listaDeUsuarios = query.list();
            Type TipolistaDeUsuarios = new TypeToken<List<Usuario>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer()).create();
            return gson.toJson(listaDeUsuarios, TipolistaDeUsuarios);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "deleta-usuario", method = RequestMethod.GET)
    @ResponseBody
    public String delete(long id) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Usuario u = buscaUsuarioParaOperacao(id);
            u.setAtivo(0);
            sessao.update(u);
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
            transacao.commit();
            sessao.close();
        }

    }

    @RequestMapping(value = "atualiza-usuario-nome-senha", method = RequestMethod.POST)
    @ResponseBody
    public static String atualizaUsuario(String emailAntigo, String senhaAntiga, String emailNovo, String senhaNova) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Usuario.class);
            Criterion usuarioex = Restrictions.eq("email", emailAntigo);
            Criterion senhaex = Restrictions.eq("senha", senhaAntiga);
            Criterion ativoex = Restrictions.eq("ativo", 1);
            Conjunction andEx = Restrictions.conjunction();
            andEx.add(usuarioex);
            andEx.add(senhaex);
            andEx.add(ativoex);
            query.add(andEx);
            Usuario u = (Usuario) query.uniqueResult();
            if (u != null) {
                u.setEmail(emailNovo);
                u.setSenha(senhaNova);
            }
            sessao.update(u);
            transacao.commit();
            String jsonMensagemSucesso = CriadorJson.criaJsonSucesso("Sucesso");
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

    public static Usuario buscaUsuarioParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Usuario u = (Usuario) sessao.get(Usuario.class, id);
            return u;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }

}
