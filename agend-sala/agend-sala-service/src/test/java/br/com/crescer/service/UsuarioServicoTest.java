package br.com.crescer.service;

import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.repository.SalaRepositorio;
import br.com.crescer.agend.repository.UsuarioRepositorio;
import br.com.crescer.agend.service.SalaServico;
import br.com.crescer.agend.service.UsuarioServico;
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

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServicoTest {
    
    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @InjectMocks
    private UsuarioServico usuarioServico;

    @Mock
    private Iterable<Usuario> usuarios;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Usuario> page;
    
    @Mock
    private Usuario usuario;

    @Before
    public void setUp() {
        when(usuarioRepositorio.findAll()).thenReturn(usuarios);
        when(usuarioRepositorio.save(usuario)).thenReturn(usuario);
        when(usuarioRepositorio.findOne(1l)).thenReturn(usuario);
    }

    /**
     * Test of findAll method, of class PessoaService.
     */
    @Test
    public void testFindAll_0args() {
        assertEquals(usuarios, usuarioServico.findAll());
        verify(usuarioRepositorio).findAll();
    }

    /**
     * Test of save method, of class PessoaService.
     */
    @Test
    public void testSave() {
        assertEquals(usuario, usuarioServico.update(usuario));
        verify(usuarioRepositorio).save(usuario);
    }

    /**
     * Test of findOne method, of class PessoaService.
     */
    @Test
    public void testFindOne() {
        assertEquals(usuario, usuarioServico.findOne(1l));
        verify(usuarioRepositorio).findOne(1l);
    }
}
