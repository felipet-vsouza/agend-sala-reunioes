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
import br.com.crescer.agend.entity.Status;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.service.AgendamentoServico;
import br.com.crescer.agend.service.EquipamentoServico;
import br.com.crescer.agend.service.ParticipanteServico;
import br.com.crescer.agend.service.SalaServico;
import br.com.crescer.agend.service.UsuarioServico;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    AgendamentoServico agendamentoServico;

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
        model.addAttribute("confirmado", Status.CONFIRMADO);
        model.addAttribute("pendente", Status.PENDENTE);
        model.addAttribute("recusado", Status.RECUSADO);
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
        model.addAttribute("confirmado", Status.CONFIRMADO);
        model.addAttribute("pendente", Status.PENDENTE);
        model.addAttribute("recusado", Status.RECUSADO);
        return "fragments :: reunioes";
    }
    
    @RequestMapping(value = "/home/salas/list")
    public String salas(Model model) {
        List<Sala> salas = IteratorUtils.toList(salaServico.findAll().iterator());
        List<Integer> preenchimentoSalas = salas.stream()
                .map(s -> agendamentoServico.findTempoOcupadoByAgendamentos(s))
                .collect(Collectors.toList());
        model.addAttribute("salas", salas);
        model.addAttribute("preenchimentoSalas", preenchimentoSalas);
        return "fragments :: salas";
    }

    @RequestMapping(value = {"/home/alteracao/{id}"})
    public String alteracaoAgendamentoSala(Model model, @PathVariable(value = "id") Long idAgendamento) {
        Iterable<Usuario> usuarios = usuarioServico.findAll();
        Agendamento agendamento = agendamentoServico.findOne(idAgendamento);
        List<Long> participantes = agendamento.getParticipantes()
                .stream()
                .map(p -> p.getUsuario().getId())
                .collect(Collectors.toList());
        Iterable<Sala> salas = salaServico.findAll();

        model.addAttribute("salas", salas);

        model.addAttribute("agendamento", agendamento);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("participantes", participantes);
        return "fragments :: form-alterar";
    }
        

    @RequestMapping(value = "home/agendamento/sala/{idSala}")
    public String agendamentoDeSala(Model model, @PathVariable(value = "idSala") Long idSala) {
        Iterable<Sala> salas = salaServico.findAll();
        Iterable<Usuario> usuarios = usuarioServico.findAll();
        Sala sala = salaServico.findOne(idSala);

        model.addAttribute("salas", salas);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("saladefinida", sala);
        return "fragments :: form-agendamento";
    }
}
