package br.com.crescer.repository;

import br.com.crescer.TestRun;
import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.repository.AgendamentoRepositorio;
import br.com.crescer.agend.repository.ParticipanteRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestRun.class)
@Transactional
public class AgendamentoRepositorioTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AgendamentoRepositorio agendamentoRepositorio;
    
    Usuario usuario;
    
    Agendamento agendamento;

    Date dataInicio;

    Date dataFinal;
    
    Sala sala;

    @Before
    public void criarAgendamento() throws ParseException {
        entityManager.clear();
        agendamento = new Agendamento();
        agendamento.setDataInicio(getDateByString("25/12/2016 08:00"));
        agendamento.setDataFinal(getDateByString("25/12/2016 15:00"));
        sala = criarSala();
        agendamento.setSala(sala);
        ArrayList<Participante> participantes = new ArrayList<>();
        Participante p = new Participante();
        usuario = new Usuario();
        usuario.setEmail("");
        usuario.setNome("");
        usuario.setSenha("");
        entityManager.persist(usuario);
        p.setUsuario(usuario);
        entityManager.persist(p);
        participantes.add(p);
        agendamento.setParticipantes(participantes);
        entityManager.persist(agendamento);
        dataInicio = getDateByString("25/12/2016 08:00");
        dataFinal = getDateByString("25/12/2016 22:00");
    }

    private Sala criarSala() {
        final Sala sala = new Sala();
        sala.setCapacidade(30);
        sala.setNome("Sala de testes");
        entityManager.persist(sala);
        return sala;
    }
    
    @Test
    public void retornaRegistroParaBuscaNoDia25() {
        assertEquals(1, agendamentoRepositorio.findAgendamentosByDatasAndBySala(dataInicio, dataFinal, sala.getId()).size());
    } 
    
    @Test
    public void naoRetornaRegistroParaBuscaNoDia26() throws ParseException {
        Date dInicio = getDateByString("26/12/2016 08:00");
        Date dFinal = getDateByString("26/12/2016 22:00");
        assertEquals(0, agendamentoRepositorio.findAgendamentosByDatasAndBySala(dInicio, dFinal, sala.getId()).size());
    }
    
    @Test
    public void naoRetornaRegistroParaBuscaNoDia26EUsuarioAtual() throws ParseException {
        Date dInicio = getDateByString("26/12/2016 08:00");
        Date dFinal = getDateByString("26/12/2016 22:00");
        assertEquals(0, agendamentoRepositorio.findAgendamentoConflitantePorUsuario(usuario.getId(), dInicio, dFinal).size());
    }

    private static Date getDateByString(final String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
    }
}
