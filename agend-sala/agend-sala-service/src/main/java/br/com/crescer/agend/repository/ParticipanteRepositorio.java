package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ParticipanteRepositorio extends CrudRepository<Participante, Long> {

    public Iterable<Participante> findAll();

    public List<Participante> findByUsuario(Usuario usuario);

    public List<Participante> findByAgendamento(Agendamento agendamento);

}
