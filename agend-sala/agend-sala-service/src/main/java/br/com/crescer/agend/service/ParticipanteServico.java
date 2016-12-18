/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Email;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Status;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.repository.ParticipanteRepositorio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author henrique.ostermann
 */
@Service
public class ParticipanteServico {

    @Autowired
    ParticipanteRepositorio participanteRepositorio;

    @Autowired
    EmailServico emailServico;

    public Iterable<Participante> findAll() {
        return participanteRepositorio.findAll();
    }

    public List<Participante> save(List<Usuario> usuarios, Agendamento agendamento) {

        List<Participante> participantes = new ArrayList<>();
        
        for (int i = 0; i < usuarios.size(); i++) {
            Participante participante = new Participante(usuarios.get(i), agendamento, Status.PENDENTE);
            participantes.add(participante);
            participanteRepositorio.save(participante);

            Email email = new Email(participante, new Date(), obterToken());
            emailServico.salvar(email);
        }
        
        emailServico.enviarEmail(participantes, "Conteudo aqui!", "Você recebeu um convite para uma reunião.");
        
        return participantes;
    }

    public Participante save(Participante participante) {
        return participanteRepositorio.save(participante);
    }

    public void delete(Long id) {
        participanteRepositorio.delete(id);
    }

    public Participante findOne(Long id) {
        return participanteRepositorio.findOne(id);
    }

    public List<Participante> findByUsuario(Usuario usuario) {
        return participanteRepositorio.findByUsuario(usuario);
    }

    public List<Participante> obterParticipantesDeAgendamentos(Usuario atual) {
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.clear(Calendar.HOUR_OF_DAY);
        dataAtual.clear(Calendar.MINUTE);
        dataAtual.clear(Calendar.SECOND);
        dataAtual.clear(Calendar.MILLISECOND);

        Date dateInicial = dataAtual.getTime();

        List<Participante> participantes = participanteRepositorio
                .findByUsuario(atual)
                .stream()
                .sorted((e1, e2) -> e1.getAgendamento().getDataInicio().compareTo(e2.getAgendamento().getDataInicio()))
                .filter(p -> p.getAgendamento().getDataInicio().after(dateInicial)
                || p.getAgendamento().getDataInicio().equals(dateInicial))
                .collect(Collectors.toList());

        return participantes;
    }

    private String obterToken() {
        return UUID.randomUUID().toString();
    }
    
    
}
