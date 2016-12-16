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

    public List<Agendamento> obterAgendamentos(Usuario atual) {
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.clear(Calendar.HOUR_OF_DAY);
        dataAtual.clear(Calendar.AM_PM);
        dataAtual.clear(Calendar.MINUTE);
        dataAtual.clear(Calendar.SECOND);
        dataAtual.clear(Calendar.MILLISECOND);
        
        Date dateInicial = dataAtual.getTime();
//        dataAtual.add(Calendar.DAY_OF_MONTH, 1);
//        dataAtual.add(Calendar.SECOND, -1);
//        Date dateFinal = dataAtual.getTime();

        List<Agendamento> agendamentos = atual.getParticipantes().stream()
                .sorted((e1, e2) -> e1.getAgendamento().getDataInicio().compareTo(e2.getAgendamento().getDataInicio()))
                .filter(p -> p.getAgendamento().getDataInicio().after(dateInicial))
//                          && p.getAgendamento().getDataInicio().before(dateFinal))
                .map((Participante p) -> p.getAgendamento())
                .collect(Collectors.toList());

        return agendamentos;
    }
}
