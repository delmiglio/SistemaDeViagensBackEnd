/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Utilitarios;

import br.com.bean.Classes_Mapeamentos.Aluno;
import br.com.bean.Classes_Mapeamentos.AlunoxVenda;
import br.com.bean.Classes_Mapeamentos.AlunoxVendaID;
import br.com.bean.Classes_Mapeamentos.Dependente;
import br.com.bean.Classes_Mapeamentos.Faculdade;
import br.com.bean.Classes_Mapeamentos.Festa;
import br.com.bean.Classes_Mapeamentos.Pagamento;
import br.com.bean.Classes_Mapeamentos.Pessoa;
import br.com.bean.Classes_Mapeamentos.Representante;
import br.com.bean.Classes_Mapeamentos.Turma;
import br.com.bean.Classes_Mapeamentos.Usuario;
import br.com.bean.Classes_Mapeamentos.Venda;
import br.com.bean.Classes_Mapeamentos.Viagem;
import java.text.ParseException;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Classe de Testes do Hibernate , Necessario Apenas Modificar o Conteudo
 *
 * @author pro7223
 */
public class TestaHibernate {
//
    public static void Teste() throws ParseException {
        //incluiAluno();
        //incluiRepresentante();
        incluiUsuario();
        //incluiDependente();
        //incluiFaculdadeTurmaAluno();
        //incluiVendaFestaViagemRepresentantePagamento();
        //incluiAlunoVenda();
    }

//    public static void incluiAluno() throws ParseException {
//
//        System.out.println("entrou");
//        Session sessao = HibernateUtility.getSession();
//        Transaction transacao = sessao.beginTransaction();
//
//        Aluno a = new Aluno("99999999", "Analise e Desenvolvimento de Sistemas", 2);
//        a.setCelular("95476-9088");
//        a.setCep("09991100");
//        a.setCidade("Diadema");
//        a.setComplemento("Teste");
//        a.setCpf("44385902801");
//        a.setDataNascimento("27/12/1994");
//        a.setEmail("guidelmiglio@hotmail.com");
//        a.setEndereco("rua tukanos n°57");
//        a.setEstado("São Paulo");
//        a.setNome("Guilherme Delmiglio");
//        a.setRg("487395207");
//        a.setTelefone("9080-9080");
//
//        sessao.saveOrUpdate(a);
//
//        transacao.commit();
//        sessao.close();
//
//    }
//
//    public static void incluiRepresentante() throws ParseException {
//
//        System.out.println("entrou");
//        Session sessao = HibernateUtility.getSession();
//        Transaction transacao = sessao.beginTransaction();
//
//        Representante r = new Representante("19/08/1972", "0099029", 2200);
//        r.setCelular("95476-9088");
//        r.setCep("09991100");
//        r.setCidade("SBC");
//        r.setComplemento("Próximo  a Nada");
//        r.setCpf("12345678909");
//        r.setDataNascimento("20/06/1970");
//        r.setEmail("dernival.alan@gmail.com");
//        r.setEndereco("rua alan n°57");
//        r.setEstado("São Paulo");
//        r.setNome("Alan Dernival");
//        r.setRg("487395207");
//        r.setTelefone("9080-9080");
//
//        sessao.saveOrUpdate(r);
//
//        transacao.commit();
//        sessao.close();
//
//    }
//
    public static void incluiUsuario() throws ParseException {

        System.out.println("entrou");
        Session sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();

        Usuario u = new Usuario(1);
        u.setCelular("95476-9088");
        u.setCep("09991100");
        u.setCidade("Diadema");
        u.setComplemento("Teste");
        u.setCpf("44385902801");
        u.setDataNascimento("27/12/1994");
        u.setEmail("guidelmiglio@hotmail.com");
        u.setSenha("123");
        u.setEndereco("rua tukanos n°57");
        u.setEstado("São Paulo");
        u.setNome("delmiglio");
        u.setRg("487395207");
        u.setTelefone("9080-9080");

        sessao.saveOrUpdate(u);

        transacao.commit();
        sessao.close();

    }
//
//    public static void incluiDependente() throws ParseException {
//
//        System.out.println("entrou");
//        Session sessao = HibernateUtility.getSession();
//        Transaction transacao = sessao.beginTransaction();
//
//        Dependente u = new Dependente(1);
//        u.setCelular("95476-9088");
//        u.setCep("09991100");
//        u.setCidade("Diadema");
//        u.setComplemento("Teste");
//        u.setCpf("44385902801");
//        u.setDataNascimento("27/12/1994");
//        u.setEmail("guidelmiglio@hotmail.com");
//        u.setEndereco("rua tukanos n°57");
//        u.setEstado("São Paulo");
//        u.setNome("Sandra da Silva");
//        u.setRg("487395207");
//        u.setTelefone("9080-9080");
//
//        sessao.saveOrUpdate(u);
//
//        transacao.commit();
//        sessao.close();
//
//    }
//
//    public static void incluiFaculdadeTurmaAluno() throws ParseException {
//
//        System.out.println("entrou");
//        Session sessao = HibernateUtility.getSession();
//        Transaction transacao = sessao.beginTransaction();
//
//        Aluno a = new Aluno("9273937", "Engenharia de Computação", 5);
//        a.setCelular("95476-9088");
//        a.setCep("09991100");
//        a.setCidade("Araguaia");
//        a.setComplemento("Teste");
//        a.setCpf("12345609876");
//        a.setDataNascimento("28/09/1990");
//        a.setEmail("guidelmiglio@gmail.com");
//        a.setEndereco("rua caetes n°57");
//        a.setEstado("Brasilia");
//        a.setNome("Fernanda Correira");
//        a.setRg("8308303999");
//        a.setTelefone("7282-8938");
//
//        Faculdade f = new Faculdade("Faculdade Termomêcanica", "09298839", "Rua Teste", "Próximo a Ford",
//                "São Bernardo", "São Paulo", "ftt@gmail.com", "9208-7464", "Wilson", "Referência em Educação");
//
//        Turma t = new Turma(40, 5, "ADS5");
//
//        a.setTurma(t);
//        t.setFaculdade(f);
//
//        t.getAlunos().add(a);
//        f.getTurmas().add(t);
//
//        sessao.saveOrUpdate(f);
//        sessao.saveOrUpdate(t);
//        sessao.saveOrUpdate(a);
//
//        transacao.commit();
//        sessao.close();
//
//    }
//
//    public static void incluiVendaFestaViagemRepresentantePagamento() throws ParseException {
//
//        short vooEscala = 1;
//        short vooSemEscala = 2;
//        System.out.println("entrou");
//        Session sessao = HibernateUtility.getSession();
//        Transaction transacao = sessao.beginTransaction();
//
//        Aluno a = new Aluno("9273937", "Engenharia de Computação", 5);
//        a.setCelular("95476-9088");
//        a.setCep("09991100");
//        a.setCidade("Araguaia");
//        a.setComplemento("Teste");
//        a.setCpf("12345609876");
//        a.setDataNascimento("28/09/1990");
//        a.setEmail("guidelmiglio@gmail.com");
//        a.setEndereco("rua caetes n°57");
//        a.setEstado("Brasilia");
//        a.setNome("Fernanda Correira");
//        a.setRg("8308303999");
//        a.setTelefone("7282-8938");
//
//        Representante r = new Representante("13/06/2013", "089283432", 2200);
//
//        Festa f = new Festa("09991100", "Rua Tucanos", "Próximo a Dom Pedro I", "Diadema", "SP",
//                "19/02/1972", 2, 1000, "Teste", "Teste");
//
//        Viagem vi = new Viagem("São Paulo", "Punta Del este - Argentina", "03/11/2015",
//                "03/11/2015", vooEscala, 15999, "Hotelaria Argentina", "Teste", "Teste");
//
//        Pagamento p = new Pagamento("1239083093893", 3, "12/06/2020");
//
//        Venda v = new Venda();
//
//        p.setAluno(a);
//        a.getPagamentos().add(p);
//
//        v.getRepresentante().add(r);
//        r.setVenda(v);
//
//        v.getFestas().add(f);
//        f.setVenda(v);
//
//        v.getViagens().add(vi);
//        vi.setVenda(v);
//
//        v.setPagamento(p);
//
//        sessao.saveOrUpdate(vi);
//        sessao.saveOrUpdate(f);
//        sessao.saveOrUpdate(a);
//        sessao.saveOrUpdate(p);
//        sessao.saveOrUpdate(v);
//
//        transacao.commit();
//        sessao.close();
//
//    }
//
//    public static void incluiAlunoVenda() throws ParseException {
//
//        short vooEscala = 1;
//        short vooSemEscala = 2;
//        System.out.println("entrou");
//        Session sessao = HibernateUtility.getSession();
//        Transaction transacao = sessao.beginTransaction();
//
//        Aluno a = new Aluno("9273937", "Engenharia de Computação", 5);
//        a.setCelular("95476-9088");
//        a.setCep("09991100");
//        a.setCidade("Araguaia");
//        a.setComplemento("Teste");
//        a.setCpf("12345609876");
//        a.setDataNascimento("28/09/1990");
//        a.setEmail("guidelmiglio@gmail.com");
//        a.setEndereco("rua caetes n°57");
//        a.setEstado("Brasilia");
//        a.setNome("Fernanda Correira");
//        a.setRg("8308303999");
//        a.setTelefone("7282-8938");
//
//        Representante r = new Representante("13/06/2013", "089283432", 2200);
//
//        Festa f = new Festa("09991100", "Rua Tucanos", "Próximo a Dom Pedro I", "Diadema", "SP",
//                "19/02/1972", 2, 1000, "Teste", "Teste");
//
//        Viagem vi = new Viagem("São Paulo", "Punta Del este - Argentina", "03/11/2015",
//                "03/11/2015", vooEscala, 15999, "Hotelaria Argentina", "Teste", "Teste");
//
//        Pagamento p = new Pagamento("1239083093893", 3, "12/06/2020");
//
//        Venda v = new Venda();
//
//        p.setAluno(a);
//        a.getPagamentos().add(p);
//
//        v.getRepresentante().add(r);
//        r.setVenda(v);
//
//        v.getFestas().add(f);
//        f.setVenda(v);
//
//        v.getViagens().add(vi);
//        vi.setVenda(v);
//
//        AlunoxVendaID alunoxvendaid = new AlunoxVendaID(a, v);
//        AlunoxVenda alunoxvenda = new AlunoxVenda(alunoxvendaid, 6, 10000);
//
//        sessao.saveOrUpdate(vi);
//        sessao.saveOrUpdate(f);
//        sessao.saveOrUpdate(a);
//        sessao.saveOrUpdate(v);
//        sessao.saveOrUpdate(p);
//        sessao.saveOrUpdate(alunoxvenda);
//        
//        transacao.commit();
//        sessao.close();
//
//    }

}
