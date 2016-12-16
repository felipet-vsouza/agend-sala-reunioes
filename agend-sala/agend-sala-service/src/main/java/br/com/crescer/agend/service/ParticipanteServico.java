/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.repository.ParticipanteRepositorio;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

//     public List<Equipamento> list() {
//        Equipamento equipamento = new Equipamento();
//        return Stream.of(equipamento).collect(Collectors.toList());
//    }
    public Iterable<Participante> findAll() {
        return participanteRepositorio.findAll();
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
}
