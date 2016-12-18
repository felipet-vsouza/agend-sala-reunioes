/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.agend.repository.AgendamentoRepositorio;
import br.com.crescer.agend.repository.SalaRepositorio;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author henrique.ostermann
 */
@Service
public class AgendamentoServico {

    @Autowired
    AgendamentoRepositorio agendamentoRepositorio;
    
    @Autowired
    ParticipanteServico participanteServico;
    
    @Autowired
    SalaRepositorio salaRepositorio;

    public Page<Agendamento> findAll(Pageable pgbl) {
        return agendamentoRepositorio.findAll(pgbl);
    }

    public Iterable<Agendamento> findAll() {
        return agendamentoRepositorio.findAll();
    }

    public Agendamento save(Agendamento reserva) {
        return agendamentoRepositorio.save(reserva);
    }

    public void delete(Long id) {
        agendamentoRepositorio.delete(id);
    }

    public Agendamento findOne(Long id) {
        return agendamentoRepositorio.findOne(id);
    }
    
    public List<Participante> save(List<Usuario> usuarios, Agendamento agendamento, Date dataInicial, Date dataFinal, Sala sala){
        
        if (salaEstaDisponivel(sala, dataInicial, dataFinal, usuarios.size())) {
            agendamento.setDataFinal(dataFinal);
            agendamento.setDataInicio(dataInicial);
            agendamentoRepositorio.save(agendamento);
            
            return participanteServico.save(usuarios, agendamento);
        } else{
            return null;
        }
    }
    
    private boolean salaEstaDisponivel(Sala sala, Date dataInicial, Date dataFinal, long capacidade){
        List<Sala> salas = salaRepositorio.filtroDeSalas(dataInicial, dataFinal, capacidade);
        return salas.contains(sala);
    }
}
