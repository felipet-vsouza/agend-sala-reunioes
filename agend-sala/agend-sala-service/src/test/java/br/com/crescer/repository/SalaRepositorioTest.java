/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.repository;

import br.com.crescer.TestRun;
import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.repository.SalaRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import org.springframework.transaction.annotation.Transactional;
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
    
    Agendamento agendamento;

    @Before
    public void criarAgendamento() throws ParseException {
        entityManager.clear();
        agendamento = new Agendamento();
        agendamento.setDataFinal(getDateByString("25/12/2016 17:30"));
        agendamento.setDataInicio(getDateByString("25/12/2016 13:30"));
        agendamento.setSala(criarSala());
        entityManager.persist(agendamento);
    }

    private Sala criarSala() {
        final Sala sala = new Sala();
        sala.setCapacidade(30);
        sala.setNome("Sala de testes");
        entityManager.persist(sala);
        return sala;
    }

    @Test
    public void testaPorDataDeInicioAnteriorConflitante() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 13:00");
        Date dataFinal = getDateByString("25/12/2016 14:00");
        assertEquals(1, salaRepositorio.findByIntervalo(dataInicio, dataFinal, null).size());
    }

    @Test
    public void testaPorDataDeInicioPosteriorConflitante() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 17:00");
        Date dataFinal = getDateByString("25/12/2016 18:00");
        assertEquals(1, salaRepositorio.findByIntervalo(dataInicio, dataFinal, null).size());
    }

    @Test
    public void testaPorDataIntermediariaMenorConflitante() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 15:01");
        Date dataFinal = getDateByString("25/12/2016 15:02");
        assertEquals(1, salaRepositorio.findByIntervalo(dataInicio, dataFinal, null).size());
    }

    @Test
    public void testaPorDataIntermediariaMaiorConflitante() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 13:00");
        Date dataFinal = getDateByString("25/12/2016 18:00");
        assertEquals(1, salaRepositorio.findByIntervalo(dataInicio, dataFinal, null).size());
    }
    
    @Test
    public void testaPorDataCoincidenteComDataInicial() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 13:00");
        Date dataFinal = getDateByString("25/12/2016 13:30");
        assertEquals(0, salaRepositorio.findByIntervalo(dataInicio, dataFinal, null).size());
    }

    @Test
    public void testaPorDataCoincidenteComDataFinal() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 17:30");
        Date dataFinal = getDateByString("25/12/2016 18:00");
        assertEquals(0, salaRepositorio.findByIntervalo(dataInicio, dataFinal, null).size());
    }

    @Test
    public void testaPorDataNaoCoincidenteAnterior() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 12:00");
        Date dataFinal = getDateByString("25/12/2016 13:00");
        assertEquals(0, salaRepositorio.findByIntervalo(dataInicio, dataFinal, null).size());
    }

    @Test
    public void testaPorDataNaoCoincidentePosterior() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 18:00");
        Date dataFinal = getDateByString("25/12/2016 19:00");
        assertEquals(0, salaRepositorio.findByIntervalo(dataInicio, dataFinal, null).size());
    }

    @Test
    public void testaPorAgendamentoComIdIgual() throws ParseException {
        Date dataInicio = getDateByString("25/12/2016 18:00");
        Date dataFinal = getDateByString("25/12/2016 19:00");
        assertEquals(0, salaRepositorio.findByIntervalo(dataInicio, dataFinal, agendamento.getId()).size());
    }

    private static Date getDateByString(final String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
    }
}