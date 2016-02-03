package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Dependente;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Delmiglio
 */
@RestController
public class dependenteController {

    @RequestMapping(value = "insere-dependente", method = RequestMethod.POST)
    @ResponseBody
    public String post(@RequestBody String dependente) throws HibernateException {
        Gson gson = new Gson();
        Dependente d = gson.fromJson(dependente, Dependente.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            d.setAtivo(1);
            sessao.save(d);
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

    @RequestMapping(value = "atualiza-dependente", method = RequestMethod.POST)
    @ResponseBody
    public String put(@RequestBody String dependente) {
        Gson gson = new Gson();
        Dependente dnew = gson.fromJson(dependente, Dependente.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Dependente dold = buscaDependenteParaOperacao(dnew.getPessoaId());
            dold.setCelular(dnew.getCelular());
            dold.setCep(dnew.getCep());
            dold.setCidade(dnew.getCidade());
            dold.setComplemento(dnew.getComplemento());
            dold.setCpf(dnew.getCpf());
            dold.setDataNascimento(dnew.getDataNascimento());
            dold.setEmail(dnew.getEmail());
            dold.setEndereco(dnew.getEndereco());
            dold.setEstado(dnew.getEstado());
            dold.setNome(dnew.getNome());
            dold.setRg(dnew.getRg());
            dold.setTelefone(dnew.getTelefone());
            dold.setGrauParentesco(dnew.getGrauParentesco());
            sessao.update(dold);
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

    @RequestMapping(value = "buscaPorId-dependente", method = RequestMethod.GET)
    @ResponseBody
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Dependente d = (Dependente) sessao.get(Dependente.class, id);
            Gson gson = new Gson();
            return gson.toJson(d, Dependente.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-dependentes", method = RequestMethod.GET)
    @ResponseBody
    public String get() throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Dependente.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.add(ativo);
            List<Dependente> listaDeDependentes = query.list();
            Type TipolistaDeDependentes = new TypeToken<List<Dependente>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            return gson.toJson(listaDeDependentes, TipolistaDeDependentes);
        } catch (HibernateException e) {
            transacao.rollback();
            return null;
        }
    }

    @RequestMapping(value = "deleta-dependente", method = RequestMethod.GET)
    @ResponseBody
    public String delete(long id) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Dependente d = buscaDependenteParaOperacao(id);
            d.setAtivo(0);
            sessao.update(d);
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

    public static Dependente buscaDependenteParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Dependente d = (Dependente) sessao.get(Dependente.class, id);
            return d;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }
}
