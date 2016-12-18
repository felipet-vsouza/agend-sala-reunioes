/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.web;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.service.AgendamentoServico;
import br.com.crescer.agend.service.EquipamentoServico;
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
    ParticipanteServico participanteServico;

    @Autowired
    EquipamentoServico equipamentoServico;

    @RequestMapping(value = {"/home", "/"})
    public String home(Model model, @AuthenticationPrincipal User user) {
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
        model.addAttribute("salas", salas);
        return "fragments :: form-agendamento";
    }
}
