package br.com.bean.RestControllers;

import br.com.bean.Classes_Mapeamentos.Pagamento;
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
public class pagamentoController {

    @RequestMapping(value = "insere-pagamento", method = RequestMethod.POST)
    @ResponseBody
    public String post(@RequestBody String pagamento) throws HibernateException {
        Gson gson = new Gson();
        Pagamento p = gson.fromJson(pagamento, Pagamento.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            p.setAtivo(1);
            sessao.save(p);
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

    @RequestMapping(value = "atualiza-pagamento", method = RequestMethod.POST)
    @ResponseBody
    public String put(@RequestBody String pagamento) {
        Gson gson = new Gson();
        Pagamento pnew = gson.fromJson(pagamento, Pagamento.class);
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Pagamento pold = buscaPagamentoParaOperacao(pnew.getPagamentoId());
            pold.setBandeira(pnew.getBandeira());
            pold.setDataVencimento(pnew.getDataVencimento());
            pold.setNumeroCartao(pnew.getNumeroCartao());
            sessao.update(pold);
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

    @RequestMapping(value = "buscaPorId-pagamento", method = RequestMethod.GET)
    @ResponseBody
    public String get(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Pagamento p = (Pagamento) sessao.get(Pagamento.class, id);
            Gson gson = new Gson();
            return gson.toJson(p, Pagamento.class);
        } catch (HibernateException e) {
            return CriadorJson.criaJsonErro(e, "Verificar");
        } finally {
            sessao.close();
        }
    }

    @RequestMapping(value = "busca-pagamentos", method = RequestMethod.GET)
    @ResponseBody
    public String get() throws ParseException {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria query = sessao.createCriteria(Pagamento.class);
            Criterion ativo = Restrictions.eq("ativo", 1);
            query.add(ativo);
            List<Pagamento> listaDePagamentos = query.list();
            Type TipolistaDePagamentos = new TypeToken<List<Pagamento>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Collection.class, new CollectionDeserializer())
                    .create();
            return gson.toJson(listaDePagamentos, TipolistaDePagamentos);
        } catch (HibernateException e) {
            transacao.rollback();
            return null;
        }
    }

    @RequestMapping(value = "deleta-pagamento", method = RequestMethod.GET)
    @ResponseBody
    public String delete(long id) {
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        try {
            Pagamento p = buscaPagamentoParaOperacao(id);
            p.setAtivo(0);
            sessao.update(p);
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

    public static Pagamento buscaPagamentoParaOperacao(long id) throws HibernateException {
        Session sessao = HibernateUtility.getSession();
        try {
            Pagamento p = (Pagamento) sessao.get(Pagamento.class, id);
            return p;
        } catch (HibernateException e) {

        } finally {
            sessao.close();
        }
        return null;
    }

}
