/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.repository.SalaRepositorio;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author henrique.ostermann
 */
@Service
public class SalaServico {

    @Autowired
    SalaRepositorio salaRepositorio;

//     public List<Sala> list() {
//        Sala sala = new Sala();
//        return Stream.of(sala).collect(Collectors.toList());
//    }
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

    public List<Sala> findAllSala(@Param("DATAINICIAL") Date dataInicial, @Param("DATAFINAL") Date dataFinal,
            @Param("EQUIPAMENTOSELECIONADO") Long equipamentoSelecionado,
            @Param("QUANTIDADESELECIONADO") Long quantidadeSelecionado){
        return salaRepositorio.findAllSala(dataInicial, dataFinal, equipamentoSelecionado, quantidadeSelecionado);
    }
}
