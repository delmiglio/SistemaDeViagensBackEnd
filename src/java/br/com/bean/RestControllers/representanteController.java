/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Representante;
import br.com.bean.Classes_Mapeamentos.Usuario;
import br.com.bean.Utilitarios.CollectionDeserializer;
import br.com.bean.Utilitarios.CriadorJson;
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
public class representanteController {

    @RequestMapping(value = "insere-representante", method = RequestMethod.POST)
    public String post(@RequestBody String representante) throws HibernateException {
        Gson gson = new Gson();
        Representante r = gson.fromJson(representante, Representante.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            r.setAtivo(1);
            sessao.save(r);

            Usuario u = new Usuario(4);
            u.setNivelPermissao(4);
            u.setSenha(r.getSenha());
            u.setEmail(r.getEmail());
            u.setEndereco(r.getEndereco());
            u.setComplemento(r.getComplemento());
            u.setTelefone(r.getTelefone());
            u.setCep(r.getCep());
            u.setCidade(r.getCidade());
            u.setEstado(r.getEstado());
            u.setNome(r.getNome());
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

    @RequestMapping(value = "atualiza-representante", method = RequestMethod.POST)
    public String put(@RequestBody String representante) {
        Gson gson = new Gson();
        Representante rnew = gson.fromJson(representante, Representante.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Representante rold = buscaRepresentanteParaOperacao(rnew.getPessoaId());
            String jsonUsuarioAtualizacao = usuarioController.atualizaUsuario(rold.getEmail(), rold.getSenha(), rnew.getEmail(), rnew.getSenha());
            rold.setCelular(rnew.getCelular());
            rold.setCep(rnew.getCep());
            rold.setCidade(rnew.getCidade());
            rold.setComplemento(rnew.getComplemento());
            rold.setCpf(rnew.getCpf());
            rold.setDataNascimento(rnew.getDataNascimento());
            rold.setEmail(rnew.getEmail());
            rold.setEndereco(rnew.getEndereco());
            rold.setEstado(rnew.getEstado());
            rold.setNome(rnew.getNome());
            rold.setRg(rnew.getRg());
            rold.setTelefone(rnew.getTelefone());
            rold.setCTPS(rnew.getCTPS());
            rold.setDataDeAdmissao(rnew.getDataDeAdmissao());
            rold.setSalario(rnew.getSalario());
            rold.setSenha(rnew.getSenha());
            rold.setAtivo(1);
            sessao.update(rold);
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

    @RequestMapping(value = "buscaPorId-representante", method = RequestMethod.GET)
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Representante r = (Representante) sessao.get(Representante.class, id);
            Gson gson = new Gson();
            return gson.toJson(r, Representante.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-representantes", method = RequestMethod.GET)
    public String get() throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Representante.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.add(ativo);
            List<Representante> listaDeRepresentantes = query.list();
            Type TipolistaDeRepresentantes = new TypeToken<List<Representante>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            return gson.toJson(listaDeRepresentantes, TipolistaDeRepresentantes);
        } catch (HibernateException e) {
            transacao.rollback();
            return null;
        }
    }

    @RequestMapping(value = "deleta-representante", method = RequestMethod.GET)
    public String delete(long id) throws SQLException, ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Representante r = buscaRepresentanteParaOperacao(id);
            r.setAtivo(0);
            sessao.update(r);
            String u = usuarioController.buscaUsuarioNomeSenha(r.getEmail(), r.getSenha());
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

    public static Representante buscaRepresentanteParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Representante r = (Representante) sessao.get(Representante.class, id);
            return r;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }

}
