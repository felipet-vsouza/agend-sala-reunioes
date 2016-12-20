/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.web;

import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.service.EquipamentoServico;
import br.com.crescer.agend.service.ParticipanteServico;
import br.com.crescer.agend.service.SalaServico;
import br.com.crescer.agend.service.UsuarioServico;
import br.com.crescer.agend.utils.EmailUtils;
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
    ParticipanteServico participanteServico;

    @Autowired
    EquipamentoServico equipamentoServico;

    @RequestMapping(value = {"/home", "/"})
    public String home(Model model, @AuthenticationPrincipal User user) {
        
//        EmailUtils email = new EmailUtils();
//        String teste = email.testeTemplate();
        
        Usuario atual = usuarioServico.findByEmail(user.getUsername());

        Iterable<Sala> salas = salaServico.findAll();

        List<Participante> participacoes = participanteServico.obterParticipantesDeAgendamentos(atual);

        model.addAttribute("salas", salas);
        model.addAttribute("sessao", atual);
        model.addAttribute("participacoes", participacoes);
        return "home";
    }

    @RequestMapping(value = "/home/salas")
    public String filtraSalas(Model model) {
        Iterable<Equipamento> equipamentos = equipamentoServico.findAll();
        model.addAttribute("equipamentos", equipamentos);
        return "fragments :: form-salas";
    }

    @RequestMapping(value = "/home/agendamento")
    public String agendamentoSalas(Model model) {
        Iterable<Sala> salas = salaServico.findAll();
        Iterable<Usuario> usuarios = usuarioServico.findAll();

        model.addAttribute("salas", salas);
        model.addAttribute("usuarios", usuarios);

        return "fragments :: form-agendamento";
    }

    @RequestMapping(value = "/home/confirmation")
    public String confirmacao() {
        return "fragments :: confirmacao";
    }

    @RequestMapping(value = "/home/reunioes")
    public String reunioes(Model model, @AuthenticationPrincipal User user) {
        Usuario atual = usuarioServico.findByEmail(user.getUsername());
        List<Participante> participacoes = participanteServico.obterParticipantesDeAgendamentos(atual);
        model.addAttribute("participacoes", participacoes);
        return "fragments :: reunioes";
    }
}
