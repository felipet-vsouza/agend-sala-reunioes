/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.service;

import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.repository.EquipamentoRepositorio;
import br.com.crescer.agend.service.EquipamentoServico;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author henrique.ostermann
 */
@RunWith(MockitoJUnitRunner.class)
public class EquipamentoServicoTest {
    


    @Mock
    private EquipamentoRepositorio equipamentoRepositorio;

    @InjectMocks
    private EquipamentoServico equipamentoServico;

    @Mock
    private Iterable<Equipamento> equipamentos;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Equipamento> page;
    
    @Mock
    private Equipamento equipamento;

    @Before
    public void setUp() {
        when(equipamentoRepositorio.findAll()).thenReturn(equipamentos);
        when(equipamentoRepositorio.findAll(pageable)).thenReturn(page);
        when(equipamentoRepositorio.save(equipamento)).thenReturn(equipamento);
        when(equipamentoRepositorio.findOne(1l)).thenReturn(equipamento);
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
        assertEquals(page, equipamentoServico.findAll(pageable));
        verify(equipamentoRepositorio).findAll(pageable);
    }

    /**
     * Test of findAll method, of class PessoaService.
     */
    @Test
    public void testFindAll_0args() {
        assertEquals(equipamentos, equipamentoServico.findAll());
        verify(equipamentoRepositorio).findAll();
    }

    /**
     * Test of save method, of class PessoaService.
     */
    @Test
    public void testSave() {
        assertEquals(equipamento, equipamentoServico.save(equipamento));
        verify(equipamentoRepositorio).save(equipamento);
    }

    /**
     * Test of delete method, of class PessoaService.
     */
    @Test
    public void testDelete() {
        equipamentoServico.delete(1l);
        verify(equipamentoRepositorio).delete(1l);
    }

    /**
     * Test of findOne method, of class PessoaService.
     */
    @Test
    public void testFindOne() {
        assertEquals(equipamento, equipamentoServico.findOne(1l));
        verify(equipamentoRepositorio).findOne(1l);
    }

}
