/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.web;

import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.service.AgendamentoServico;
import br.com.crescer.agend.service.ParticipanteServico;
import br.com.crescer.agend.service.SalaServico;
import br.com.crescer.agend.service.UsuarioServico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Henrique
 */
@Controller
public class HomeController {
    
    @Autowired
    UsuarioServico usuarioServico;
    
    @Autowired
    SalaServico salaServico;

    @Autowired
    AgendamentoServico agendamentoServico;
    
    @Autowired
    ParticipanteServico participanteServico;
    
    @RequestMapping(value = {"/home", "/"})
    public String home(Model model, @AuthenticationPrincipal User user) {
        Usuario atual = usuarioServico.findByEmail(user.getUsername());
        
        Iterable<Sala> salas = salaServico.findAll();
        
        Iterable<Usuario> participantes = usuarioServico.findAll();
        
        model.addAttribute("salas", salas);
        model.addAttribute("sessao", atual);
        return "home";
    }
}
