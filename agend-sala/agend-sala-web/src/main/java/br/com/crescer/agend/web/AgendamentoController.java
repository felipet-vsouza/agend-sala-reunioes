/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.web;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Email;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Status;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.service.AgendamentoServico;
import br.com.crescer.agend.service.EmailServico;
import br.com.crescer.agend.service.ParticipanteServico;
import br.com.crescer.agend.service.SalaServico;
import br.com.crescer.agend.service.UsuarioServico;
import br.com.crescer.agend.utils.EmailUtils;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Henrique
 */
@Controller
public class AgendamentoController {

    @Autowired
    SalaServico salaServico;

    @Autowired
    ParticipanteServico participanteServico;

    @Autowired
    UsuarioServico usuarioServico;

    @Autowired
    EmailServico emailServico;

    @Autowired
    AgendamentoServico agendamentoServico;

    @RequestMapping(value = {"/agendamento/adicionar"}, method = RequestMethod.POST)
    public String adicionarAgendamento(Model model, long idSala, String descricao,
            Date dataInicial, Date dataFinal, @RequestParam(value="usuarios[]", required = false) List<Long> idsUsuarios) {

        List<Usuario> usuarios = idsUsuarios.stream()
                .map(id -> usuarioServico.findOne(id))
                .collect(Collectors.toList());
        
        Usuario usuario = usuarioServico.obterUsuarioDaSessao();

        Sala sala = salaServico.findOne(idSala);

        Agendamento agendamento = new Agendamento();
        agendamento.setCriador(usuario);
        agendamento.setDescricao(descricao);
        agendamento.setSala(sala);

        List<Participante> participantes = agendamentoServico.save(usuarios, agendamento, dataInicial, dataFinal, sala);

        model.addAttribute("sucesso", true);
        
        return "fragments :: agendamentomensagem";
    }

    @RequestMapping(value = {"/agendamento/detalhes/{id_detalhamento}"}, method = RequestMethod.GET)
    public String detalhesAgendamento(@PathVariable(value = "id_detalhamento") long idDetalhamento, Model model) {

        Agendamento agendamento = agendamentoServico.findOne(idDetalhamento);

        List<Participante> participantes = participanteServico.findByAgendamento(agendamento);

        List<Participante> confirmados = participanteServico.obterParticipantesPorStatus(Status.CONFIRMADO, participantes);
        List<Participante> pendentes = participanteServico.obterParticipantesPorStatus(Status.PENDENTE, participantes);
        List<Participante> recusados = participanteServico.obterParticipantesPorStatus(Status.RECUSADO, participantes);

        boolean ehCriadorDoAgendamento = agendamentoServico.ehCriadorDoAgendamento(usuarioServico.obterUsuarioDaSessao(), agendamento);

        model.addAttribute("agendamento", agendamento);
        model.addAttribute("confirmados", confirmados);
        model.addAttribute("pendentes", pendentes);
        model.addAttribute("recusados", recusados);
        model.addAttribute("ehCriadorDoAgendamento", ehCriadorDoAgendamento);

        return "fragments :: agendamento-detalhes";
    }

    @RequestMapping(value = {"/agendamento/cancelar/{id_agendamento}"}, method = RequestMethod.POST)
    public String cancelarAgendamento(@PathVariable(value = "id_agendamento") long idAgendamento, Model model) {

        Agendamento agendamento = agendamentoServico.findOne(idAgendamento);

        boolean ehCriadorDoAgendamento = agendamentoServico.
                ehCriadorDoAgendamento(usuarioServico.obterUsuarioDaSessao(), agendamento);

        if (ehCriadorDoAgendamento) {
            agendamentoServico.cancelarAgendamento(agendamento.getParticipantes(), agendamento);
            agendamentoServico.delete(idAgendamento);
            model.addAttribute("sucesso", true);
        } else {
            model.addAttribute("sucesso", false);
        }
        return "fragments :: cancelamentomensagem";
    }

    @RequestMapping("/aceitarparticipacao/{hash}")
    public String aceitarParticipao(@PathVariable(value = "hash") String hash) {

        Email email = emailServico.findByHash(hash);

        if (emailServico.hashEhValido(email)) {
            email.getParticipante().setStatus(Status.CONFIRMADO);
            emailServico.salvar(email);
        }

        return "home";
    }

    @RequestMapping("/recusarparticipacao/{hash}")
    public String recusarParticipao(@PathVariable(value = "hash") String hash) {

        Email email = emailServico.findByHash(hash);

        if (emailServico.hashEhValido(email)) {
            email.getParticipante().setStatus(Status.RECUSADO);
            emailServico.salvar(email);
        }

        return "home";
    }
}
