/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.Utilitarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;

/**
 *
 * @author Guilherme Manipulador de Cookies para Pegar Por Nome e Apagar Cookies
 * do Servidor Caso Necessário
 */
public class ManipuladorCookies {

    public static Cookie pegaCookiePorNome(String nomeCookie, HttpServletRequest request) {
        Cookie[] lista = request.getCookies();
        for (Cookie c : lista) {
            if (c.getName().equals(nomeCookie)) {
                return c;
            }
        }
        return null;
    }

    public static HttpServletResponse apagaCookie(String nomeCookie, HttpServletResponse response) {
        Cookie c = new Cookie("loginCookie", "");
        Cookie c2 = new Cookie("senhaCookie", "");
        response.addCookie(c);
        response.addCookie(c2);
        return response;
    }

}
