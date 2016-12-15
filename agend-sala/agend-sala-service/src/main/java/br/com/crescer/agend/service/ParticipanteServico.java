/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.repository.ParticipanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Participante> findAll(Pageable pgbl) {
        return participanteRepositorio.findAll(pgbl);
    }

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
}
