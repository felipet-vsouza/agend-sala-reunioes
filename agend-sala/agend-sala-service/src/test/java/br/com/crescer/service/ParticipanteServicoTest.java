/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.repository.ParticipanteRepositorio;
import br.com.crescer.agend.service.ParticipanteServico;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

///**
// *
// * @author henrique.ostermann
// */
@RunWith(MockitoJUnitRunner.class)
public class ParticipanteServicoTest {
    
    @Mock
    private ParticipanteRepositorio participanteRepositorio;

    @InjectMocks
    private ParticipanteServico participanteServico;

    @Mock
    private Iterable<Participante> participantes;

    @Mock
    private Participante participante;
    
    @Mock
    private Usuario usuario;

    List<Participante> participantesNotMocked;

    @Before
    public void setUp() {
        when(participanteRepositorio.findAll()).thenReturn(participantes);
        when(participanteRepositorio.save(participante)).thenReturn(participante);
        when(participanteRepositorio.findOne(1l)).thenReturn(participante);
        participantesNotMocked = new ArrayList<>();
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
    public void testFindAll_0args() {
        assertEquals(participantes, participanteServico.findAll());
        verify(participanteRepositorio).findAll();
    }

    /**
     * Test of save method, of class PessoaService.
     */
    @Test
    public void testSave() {
        assertEquals(participante, participanteServico.save(participante));
        verify(participanteRepositorio).save(participante);
    }

    /**
     * Test of delete method, of class PessoaService.
     */
    @Test
    public void testDelete() {
        participanteServico.delete(1l);
        verify(participanteRepositorio).delete(1l);
    }

    /**
     * Test of findOne method, of class PessoaService.
     */
    @Test
    public void testFindOne() {
        assertEquals(participante, participanteServico.findOne(1l));
        verify(participanteRepositorio).findOne(1l);
    }

    @Test
    public void testObterAgendamentosParaDataAnterior() throws ParseException {
        Calendar cal = obterDataAtual();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date data = cal.getTime();
        Agendamento agendamento = new Agendamento();
        agendamento.setDataInicio(data);
        agendamento.setDataFinal(data);
        Participante p = new Participante();
        p.setAgendamento(agendamento);
        p.setUsuario(usuario);
        participantesNotMocked.add(p);
        agendamento.setParticipantes(participantesNotMocked);
        when(participanteRepositorio.findByUsuario(usuario)).thenReturn(participantesNotMocked);
        assertEquals(0, participanteServico.obterParticipantesDeAgendamentos(usuario).size());
    }

    @Test
    public void testObterAgendamentosParaDataAtual() throws ParseException {
        Date data = obterDataAtual().getTime();
        Agendamento agendamento = new Agendamento();
        agendamento.setDataInicio(data);
        agendamento.setDataFinal(data);
        Participante p = new Participante();
        p.setAgendamento(agendamento);
        p.setUsuario(usuario);
        participantesNotMocked.add(p);
        agendamento.setParticipantes(participantesNotMocked);
        when(participanteRepositorio.findByUsuario(usuario)).thenReturn(participantesNotMocked);
        assertEquals(1, participanteServico.obterParticipantesDeAgendamentos(usuario).size());
    }

    @Test
    public void testObterAgendamentosParaDataPosterior() throws ParseException {
        Calendar cal = obterDataAtual();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date data = cal.getTime();
        Agendamento agendamento = new Agendamento();
        agendamento.setDataInicio(data);
        agendamento.setDataFinal(data);
        Participante p = new Participante();
        p.setAgendamento(agendamento);
        p.setUsuario(usuario);
        participantesNotMocked.add(p);
        agendamento.setParticipantes(participantesNotMocked);
        when(participanteRepositorio.findByUsuario(usuario)).thenReturn(participantesNotMocked);
        assertEquals(1, participanteServico.obterParticipantesDeAgendamentos(usuario).size());
    }

    private Calendar obterDataAtual() {
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.clear(Calendar.HOUR_OF_DAY);
        dataAtual.clear(Calendar.MINUTE);
        dataAtual.clear(Calendar.SECOND);
        dataAtual.clear(Calendar.MILLISECOND);
        return dataAtual;
    }
}
