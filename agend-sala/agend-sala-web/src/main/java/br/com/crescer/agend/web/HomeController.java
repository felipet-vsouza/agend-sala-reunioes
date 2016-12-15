/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.web;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.service.AgendamentoServico;
import br.com.crescer.agend.service.ParticipanteServico;
import br.com.crescer.agend.service.SalaServico;
import br.com.crescer.agend.service.UsuarioServico;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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

        List<Agendamento> agendamentos = obterAgendamentos(atual);

        model.addAttribute("salas", salas);
        model.addAttribute("sessao", atual);
        model.addAttribute("agendamentos", agendamentos);
        return "home";
    }

    private List<Agendamento> obterAgendamentos(Usuario atual) {
        Date date = new Date();
        date.setDate(date.getDate() - 1);
        
        List<Agendamento> agendamentos = atual.getParticipantes().
                stream().sorted((e1, e2) -> e1.getAgendamento().getDataInicio().
                compareTo(e2.getAgendamento().getDataInicio())).
                filter(p -> p.getAgendamento().getDataInicio().after(date)
                        ||  p.getAgendamento().getDataInicio().after(new Date())).
                map((Participante p) -> p.getAgendamento()).collect(Collectors.toList());

        return agendamentos;
    }
}
