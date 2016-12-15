/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.service;

import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.repository.ParticipanteRepositorio;
import br.com.crescer.agend.service.ParticipanteServico;
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

    @Before
    public void setUp() {
        when(participanteRepositorio.findAll()).thenReturn(participantes);
        when(participanteRepositorio.save(participante)).thenReturn(participante);
        when(participanteRepositorio.findOne(1l)).thenReturn(participante);
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
}
