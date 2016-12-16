/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.service.AgendamentoServico;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.agend.repository.AgendamentoRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author henrique.ostermann
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendamentoServicoTest {

    @Mock
    private AgendamentoRepositorio agendamentoRepositorio;

    @InjectMocks
    private AgendamentoServico agendamentoServico;

    @Mock
    private Iterable<Agendamento> agendamentos;

    @Mock
    private Usuario usuario;
    
    @Mock
    private Pageable pageable;

    @Mock
    private Page<Agendamento> page;

    @Mock
    private Agendamento agendamento;

    @Before
    public void setUp() {
        when(agendamentoRepositorio.findAll()).thenReturn(agendamentos);
        when(agendamentoRepositorio.findAll(pageable)).thenReturn(page);
        when(agendamentoRepositorio.save(agendamento)).thenReturn(agendamento);
        when(agendamentoRepositorio.findOne(1l)).thenReturn(agendamento);
    }

    /**
     * Test of list method, of class PessoaService.
     */
//    @Test
//    public void testList() {
//        assertNotNull(salaServico.list());
//    }
    /**
     * Test of findAll method, of class PessoaService.
     */
    @Test
    public void testFindAll_Pageable() {
        assertEquals(page, agendamentoServico.findAll(pageable));
        verify(agendamentoRepositorio).findAll(pageable);
    }

    /**
     * Test of findAll method, of class PessoaService.
     */
    @Test
    public void testFindAll_0args() {
        assertEquals(agendamentos, agendamentoServico.findAll());
        verify(agendamentoRepositorio).findAll();
    }

    /**
     * Test of save method, of class PessoaService.
     */
    @Test
    public void testSave() {
        assertEquals(agendamento, agendamentoServico.save(agendamento));
        verify(agendamentoRepositorio).save(agendamento);
    }

    /**
     * Test of delete method, of class PessoaService.
     */
    @Test
    public void testDelete() {
        agendamentoServico.delete(1l);
        verify(agendamentoRepositorio).delete(1l);
    }

    /**
     * Test of findOne method, of class PessoaService.
     */
    @Test
    public void testFindOne() {
        assertEquals(agendamento, agendamentoServico.findOne(1l));
        verify(agendamentoRepositorio).findOne(1l);
    }

    @Test
    public void testObterAgendamentosParaDataAnterior() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(obterDataAtual());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date data = cal.getTime();
        Agendamento paraTeste = new Agendamento();
        paraTeste.setDataInicio(data);
        paraTeste.setDataFinal(data);
        List<Participante> participantes = new ArrayList<>();
        Participante p = new Participante();
        p.setUsuario(usuario);
        participantes.add(p);
        paraTeste.setParticipantes(participantes);
        agendamentoServico.save(paraTeste);
        assertEquals(0, agendamentoServico.obterAgendamentos(usuario).size());
    }

//    @Test
//    public void testObterAgendamentosParaDataAtual() throws ParseException {
//        Date data = obterDataAtual();
//        Agendamento paraTeste = new Agendamento();
//        paraTeste.setDataInicio(data);
//        paraTeste.setDataFinal(data);
//        List<Participante> participantes = new ArrayList<>();
//        Participante p = new Participante();
//        p.setUsuario(usuario);
//        participantes.add(p);
//        paraTeste.setParticipantes(participantes);
//        agendamentoServico.save(paraTeste);
//        Iterable<Agendamento> ee = agendamentoServico.findAll();
//        assertEquals(1, agendamentoServico.obterAgendamentos(usuario).size());
//    }
//
//    @Test
//    public void testObterAgendamentosParaDataPosterior() throws ParseException {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(obterDataAtual());
//        cal.add(Calendar.DAY_OF_MONTH, 1);
//        Date data = cal.getTime();
//        Agendamento paraTeste = new Agendamento();
//        paraTeste.setDataInicio(data);
//        paraTeste.setDataFinal(data);
//        List<Participante> participantes = new ArrayList<>();
//        Participante p = new Participante();
//        p.setUsuario(usuario);
//        participantes.add(p);
//        paraTeste.setParticipantes(participantes);
//        agendamentoServico.save(paraTeste);
//        Iterable<Agendamento> ee = agendamentoServico.findAll();
//        assertEquals(1, agendamentoServico.obterAgendamentos(usuario).size());
//    }
    
    private Date obterDataAtual() {
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.clear(Calendar.HOUR_OF_DAY);
        dataAtual.clear(Calendar.AM_PM);
        dataAtual.clear(Calendar.MINUTE);
        dataAtual.clear(Calendar.SECOND);
        dataAtual.clear(Calendar.MILLISECOND);
        
        return dataAtual.getTime();
    }
}
