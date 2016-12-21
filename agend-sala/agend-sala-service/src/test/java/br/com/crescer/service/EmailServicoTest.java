/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.service;

import br.com.crescer.agend.entity.Email;
import br.com.crescer.agend.repository.EmailRepositorio;
import br.com.crescer.agend.service.EmailServico;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Henrique
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailServicoTest {
    
    @Mock
    private EmailRepositorio emailRepositorio;

    @InjectMocks
    private EmailServico emailServico;
    
    @Mock
    private Email email;
    
    
    @Before
    public void setUp() {
        when(emailServico.findByHash("12345-67891")).thenReturn(email);
       
    }
    
    @Test
    public void testHashInvalido(){
        Email email = emailServico.findByHash("123456789");
        assertEquals(false, emailServico.hashEhValido(email));
    }
    
    @Test
    public void testDelete() {
        Email email = new Email();
        emailServico.delete(email);
        verify(emailRepositorio).delete(email);
    }
    
    @Test
    public void testSave() {
        Email email = new Email();
        emailServico.salvar(email);
        verify(emailRepositorio).save(email);
    }   
}
