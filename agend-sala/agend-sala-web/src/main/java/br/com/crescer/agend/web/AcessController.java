/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.web;

import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.service.UsuarioServico;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Henrique
 */
@Controller
public class AcessController {

    @Autowired
    UsuarioServico userService;

    @RequestMapping("/login")
    String login(Model model, @AuthenticationPrincipal User user) {

        if (user != null) {
            return "redirect:home";
        }

        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);

        model.addAttribute("user", new Usuario());
        return "login";
    }

    @RequestMapping("/logout")
    String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    }
}
