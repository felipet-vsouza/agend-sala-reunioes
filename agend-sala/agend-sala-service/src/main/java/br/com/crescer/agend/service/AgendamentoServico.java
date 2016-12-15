/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.agend.repository.AgendamentoRepositorio;

/**
 *
 * @author henrique.ostermann
 */
public class AgendamentoServico {
    @Autowired
    AgendamentoRepositorio reservaRepositorio;

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
