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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = {"/agendamento", "/adicionar"}, method = RequestMethod.POST)
    public String adicionarAgendamento(long idSala, String descricao,
            Date dataInicial, Date dataFinal, List<Usuario> usuarios) {

        Usuario usuario = obterUsuarioDaSessao();

        Sala sala = salaServico.findOne(idSala);

        Agendamento agendamento = new Agendamento();
        agendamento.setCriador(usuario);
        agendamento.setDescricao(descricao);

        List<Participante> participantes = agendamentoServico.save(usuarios, agendamento, dataInicial, dataFinal, sala);
        
        emailServico.enviarEmail(participantes, 
                conteudoEmail(participantes.get(0).getAgendamento(), 
                        participantes.get(0).getEmail()), "Convite para uma reunião.");

        return "home";
    }

    private String conteudoEmail(Agendamento agendamento, Email email) {
        return "Olá <br/>"
                + "Voce recebeu um convite para uma reunião, enviado por" + obterUsuarioDaSessao().getNome() + ". <br/>"
                + "Detalhes <br/> Local: " + agendamento.getSala().getNome() + "<br/> "
                + "Hora de inicio: " + agendamento.getDataInicio().toString() + "<br/>"
                + "Hora de termino: " + agendamento.getDataFinal().toString() + "<br/><br/>"
                + "<a href=\"http://localhost:9090/aceitarparticipacao/" + email.getHash() + ".com\">Aceitar</a> |"
                + "<a href=\"http://localhost:9090/recusarparticipacao/" + email.getHash() + ".com\">Recusar</a> <br/></br>"
                + "Sua reposta é muito importante. Obrigado!";
    }

    private Usuario obterUsuarioDaSessao() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioServico.findByEmail(user.getUsername());
        return usuario;
    }
}
