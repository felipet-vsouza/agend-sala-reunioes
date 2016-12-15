/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.service.AgendamentoServico;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.agend.repository.AgendamentoRepositorio;
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

}