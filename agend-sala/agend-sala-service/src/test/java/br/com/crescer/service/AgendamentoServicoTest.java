package br.com.crescer.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.exception.RegraNegocioException;
import br.com.crescer.agend.service.AgendamentoServico;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.agend.repository.AgendamentoRepositorio;
import br.com.crescer.agend.repository.ParticipanteRepositorio;
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

    @Mock
    private ParticipanteRepositorio participanteRepositorio;

    @Before
    public void setUp() {
        when(agendamentoRepositorio.findAll()).thenReturn(agendamentos);
        when(agendamentoRepositorio.findAll(pageable)).thenReturn(page);
        when(agendamentoRepositorio.save(agendamento)).thenReturn(agendamento);
        when(agendamentoRepositorio.findOne(1l)).thenReturn(agendamento);
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
     * Test of manterAgendamento method, of class PessoaService.
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
    public void testEhCriadorDoAgendamento(){
        Agendamento agendamento = new Agendamento();
        Usuario usuario = new Usuario();
        
        agendamento.setCriador(usuario);
        
        assertEquals(true, agendamentoServico.ehCriadorDoAgendamento(usuario, agendamento));
    }
    
    @Test
    public void testVerificarPermissao() throws RegraNegocioException{
        Agendamento agendamento = new Agendamento();
        Usuario usuario = new Usuario();
        
        agendamento.setCriador(usuario);
        
        Usuario usuarioSemPermissao = new Usuario();

        agendamentoServico.verificarPermissao(agendamento, usuarioSemPermissao);
    }
    
    @Test(expected = RegraNegocioException.class)
    public void testVerificarRegraNegocioExceptionParaListaNula() throws RegraNegocioException {
        agendamentoServico.manterAgendamento(null, agendamento, null, null, null);
    }
    
    private static Date getDateByString(final String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
    }
    
    @Test
    public void testfindTempoOcupadoByAgendamentos() throws ParseException {
        Sala sala = new Sala();
        sala.setId(1l);
        findTempoOcupadoByAgendamentosSetup(sala);
        assertEquals(50, agendamentoServico.findTempoOcupadoByAgendamentos(sala));
    }
    
    private void findTempoOcupadoByAgendamentosSetup(Sala sala) throws ParseException {
        Agendamento a = new Agendamento();
        Agendamento b = new Agendamento();
        a.setDataInicio(getDateByString("25/06/2016 08:00"));
        a.setDataFinal(getDateByString("25/06/2016 12:00"));
        b.setDataInicio(getDateByString("25/06/2016 12:00"));
        b.setDataFinal(getDateByString("25/06/2016 15:00"));
        ArrayList<Agendamento> lista = new ArrayList<>();
        lista.add(a);
        lista.add(b);
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.clear(Calendar.HOUR_OF_DAY);
        dataAtual.clear(Calendar.HOUR);
        dataAtual.clear(Calendar.AM_PM);
        dataAtual.clear(Calendar.MINUTE);
        dataAtual.clear(Calendar.SECOND);
        dataAtual.clear(Calendar.MILLISECOND);
        dataAtual.set(Calendar.HOUR_OF_DAY, 8);
        Date dateInicial = dataAtual.getTime();
        dataAtual.set(Calendar.HOUR_OF_DAY, 22);
        Date dateFinal = dataAtual.getTime();
        when(agendamentoRepositorio.findAgendamentosByDatasAndBySala(dateInicial, dateFinal, sala.getId())).thenReturn(lista);
    }
}
