/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.repository.EquipamentoRepositorio;
import br.com.crescer.agend.repository.SalaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author henrique.ostermann
 */
@Service
public class SalaServico {

    @Autowired
    SalaRepositorio salaRepositorio;

    @Autowired
    EquipamentoRepositorio equipamentoRepositorio;

    public Page<Sala> findAll(Pageable pgbl) {
        return salaRepositorio.findAll(pgbl);
    }

    public Iterable<Sala> findAll() {
        return salaRepositorio.findAll();
    }

    public Sala save(Sala sala) {
        return salaRepositorio.save(sala);
    }

    public void delete(Long id) {
        salaRepositorio.delete(id);
    }

    public Sala findOne(Long id) {
        return salaRepositorio.findOne(id);
    }

    public List<Sala> FiltroSalas(Date dataInicial, Date dataFinal, int capacidadeMinima, List<Long> ids) {
        List<Sala> conflituosas = salaRepositorio.findByIntervalo(dataInicial, dataFinal, null);
        List<Sala> validas = IteratorUtils.toList(findAll().iterator())
                .stream()
                .filter(s -> !conflituosas.contains(s))
                .filter(s -> s.getCapacidade() >= capacidadeMinima)
                .collect(Collectors.toList());
        if (ids != null) {
            List<Equipamento> equipamentos = ids.stream()
                    .map(id -> equipamentoRepositorio.findOne(id))
                    .collect(Collectors.toList());
            return filtrarEquipamentos(validas, equipamentos);
        } else {
            return validas;
        }
    }

    private List<Sala> filtrarEquipamentos(List<Sala> salas, List<Equipamento> equipamentos) {

        List<Sala> salasFiltradas = new ArrayList<>();

        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).getEquipamento().containsAll(equipamentos)) {
                salasFiltradas.add(salas.get(i));
            }
        }

        return salasFiltradas;
    }
}
