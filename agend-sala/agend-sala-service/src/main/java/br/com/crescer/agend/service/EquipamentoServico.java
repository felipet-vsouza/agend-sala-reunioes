/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.repository.EquipamentoRepositorio;
import java.awt.print.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author henrique.ostermann
 */
@Service
public class EquipamentoServico {

    @Autowired
    EquipamentoRepositorio equipamentoRepositorio;

//     public List<Equipamento> list() {
//        Equipamento equipamento = new Equipamento();
//        return Stream.of(equipamento).collect(Collectors.toList());
//    }
    public Page<Equipamento> findAll(Pageable pgbl) {
        return equipamentoRepositorio.findAll(pgbl);
    }

    public Iterable<Equipamento> findAll() {
        return equipamentoRepositorio.findAll();
    }

    public Equipamento save(Equipamento equipamento) {
        return equipamentoRepositorio.save(equipamento);
    }

    public void delete(Long id) {
        equipamentoRepositorio.delete(id);
    }

    public Equipamento findOne(Long id) {
        return equipamentoRepositorio.findOne(id);
    }
}
