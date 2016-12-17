/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.repository;

import br.com.crescer.TestRun;
import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.repository.SalaRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Felps-Notebook
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestRun.class)
@Transactional
public class SalaRepositorioTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SalaRepositorio salaRepositorio;

    @Before
    public void setBefore() throws ParseException {
        Equipamento e1 = new Equipamento();
        e1.setNome("Televisão");
        Equipamento e2 = new Equipamento();
        e2.setNome("Telefone");
        Equipamento e3 = new Equipamento();
        e2.setNome("Cadeiras");
        entityManager.persist(e1);
        entityManager.persist(e2);
        entityManager.persist(e3);
        List<Equipamento> equipamentos = new ArrayList<>();
        equipamentos.add(e1);
        equipamentos.add(e2);
        equipamentos.add(e3);
        final Sala sala = new Sala();
        sala.setCapacidade(20);
        sala.setEquipamento(equipamentos);
        sala.setNome("Sala de testes");
        entityManager.persist(sala);
        Agendamento agendamento = new Agendamento();
        agendamento.setDataFinal(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 14:00"));
        agendamento.setDataInicio(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 16:00"));
        agendamento.setSala(sala);
        entityManager.persist(agendamento);
    }

    @Test
    public void testaPorDataDeInicioAnteriorConflitante() throws ParseException {
        Date dataInicio = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 13:00");
        Date dataFinal = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 15:00");
        List<Equipamento> equipamentos = new ArrayList<>();
        Equipamento e1 = new Equipamento();
        e1.setNome("Televisão");
        e1.setId(1l);
        Equipamento e2 = new Equipamento();
        e2.setNome("Telefone");
        e1.setId(2l);
        assertEquals(0, salaRepositorio.findAllSala(dataInicio, dataFinal, equipamentos, 20).size());
    }

    @Test
    public void testaPorDataDeInicioPosteriorConflitante() throws ParseException {
        Date dataInicio = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 15:00");
        Date dataFinal = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 17:00");
        List<Equipamento> equipamentos = new ArrayList<>();
        Equipamento e1 = new Equipamento();
        e1.setNome("Televisão");
        e1.setId(1l);
        Equipamento e2 = new Equipamento();
        e2.setNome("Telefone");
        e1.setId(2l);
        assertEquals(0, salaRepositorio.findAllSala(dataInicio, dataFinal, equipamentos, 20).size());
    }

    @Test
    public void testaPorDataIntermediariaMenorConflitante() throws ParseException {
        Date dataInicio = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 14:30");
        Date dataFinal = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 15:30");
        List<Equipamento> equipamentos = new ArrayList<>();
        Equipamento e1 = new Equipamento();
        e1.setNome("Televisão");
        e1.setId(1l);
        Equipamento e2 = new Equipamento();
        e2.setNome("Telefone");
        e1.setId(2l);
        assertEquals(0, salaRepositorio.findAllSala(dataInicio, dataFinal, equipamentos, 20).size());
    }

    @Test
    public void testaPorDataIntermediariaMaiorConflitante() throws ParseException {
        Date dataInicio = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 13:00");
        Date dataFinal = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 17:00");
        List<Equipamento> equipamentos = new ArrayList<>();
        Equipamento e1 = new Equipamento();
        e1.setNome("Televisão");
        e1.setId(1l);
        Equipamento e2 = new Equipamento();
        e2.setNome("Telefone");
        e1.setId(2l);
        assertEquals(0, salaRepositorio.findAllSala(dataInicio, dataFinal, equipamentos, 20).size());
    }

    @Test
    public void testaPorDataCoincidenteComDataInicial() throws ParseException {
        Date dataInicio = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 13:00");
        Date dataFinal = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 14:00");
        List<Equipamento> equipamentos = new ArrayList<>();
        Equipamento e1 = new Equipamento();
        e1.setNome("Televisão");
        e1.setId(1l);
        Equipamento e2 = new Equipamento();
        e2.setNome("Telefone");
        e1.setId(2l);
        assertEquals(1, salaRepositorio.findAllSala(dataInicio, dataFinal, equipamentos, 20).size());
    }

    @Test
    public void testaPorDataCoincidenteComDataFinal() throws ParseException {
        Date dataInicio = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 16:00");
        Date dataFinal = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("15/12/2016 17:00");
        List<Equipamento> equipamentos = new ArrayList<>();
        Equipamento e1 = new Equipamento();
        e1.setNome("Televisão");
        e1.setId(1l);
        Equipamento e2 = new Equipamento();
        e2.setNome("Telefone");
        e1.setId(2l);
        assertEquals(1, salaRepositorio.findAllSala(dataInicio, dataFinal, equipamentos, 20).size());
    }
}
