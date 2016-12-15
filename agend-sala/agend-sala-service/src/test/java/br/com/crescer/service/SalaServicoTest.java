/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.service;

import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.repository.SalaRepositorio;
import br.com.crescer.agend.service.SalaServico;
import java.awt.print.Pageable;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
/**
 *
 * @author henrique.ostermann
 */
@RunWith(MockitoJUnitRunner.class)

public class SalaServicoTest {
    @Mock
    private SalaRepositorio salaRepositorio;

    @InjectMocks
    private SalaServico salaServico;

    @Mock
    private Iterable<Sala> salas;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Sala> page;
    
    @Mock
    private Sala sala;

    @Before
    public void setUp() {
        when(salaRepositorio.findAll()).thenReturn(salas);
        when(salaRepositorio.findAll(pageable)).thenReturn(page);
        when(salaRepositorio.save(sala)).thenReturn(sala);
        when(salaRepositorio.findOne(1l)).thenReturn(sala);
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
        assertEquals(page, salaServico.findAll(pageable));
        verify(salaRepositorio).findAll(pageable);
    }

    /**
     * Test of findAll method, of class PessoaService.
     */
    @Test
    public void testFindAll_0args() {
        assertEquals(salas, salaServico.findAll());
        verify(salaRepositorio).findAll();
    }

    /**
     * Test of save method, of class PessoaService.
     */
    @Test
    public void testSave() {
        assertEquals(sala, salaServico.save(sala));
        verify(salaRepositorio).save(sala);
    }

    /**
     * Test of delete method, of class PessoaService.
     */
    @Test
    public void testDelete() {
        salaServico.delete(1l);
        verify(salaRepositorio).delete(1l);
    }

    /**
     * Test of findOne method, of class PessoaService.
     */
    @Test
    public void testFindOne() {
        assertEquals(sala, salaServico.findOne(1l));
        verify(salaRepositorio).findOne(1l);
    }

}