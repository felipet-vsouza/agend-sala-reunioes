/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.agend.repository.AgendamentoRepositorio;
import br.com.crescer.agend.repository.ParticipanteRepositorio;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author henrique.ostermann
 */
@Service
public class AgendamentoServico {

    @Autowired
    AgendamentoRepositorio reservaRepositorio;

    @Autowired
    ParticipanteRepositorio participanteRepositorio;

//     public List<Equipamento> list() {
//        Equipamento equipamento = new Equipamento();
//        return Stream.of(equipamento).collect(Collectors.toList());
//    }
    public Page<Agendamento> findAll(Pageable pgbl) {
        return reservaRepositorio.findAll(pgbl);
    }

    public Iterable<Agendamento> findAll() {
        return reservaRepositorio.findAll();
    }

    public Agendamento save(Agendamento reserva) {
        return reservaRepositorio.save(reserva);
    }

    public void delete(Long id) {
        reservaRepositorio.delete(id);
    }

    public Agendamento findOne(Long id) {
        return reservaRepositorio.findOne(id);
    }
}
