package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.agend.repository.AgendamentoRepositorio;
import br.com.crescer.agend.repository.SalaRepositorio;
import br.com.crescer.agend.repository.UsuarioRepositorio;
import br.com.crescer.agend.utils.EmailUtils;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoServico {

    @Autowired
    AgendamentoRepositorio agendamentoRepositorio;

    @Autowired
    ParticipanteServico participanteServico;

    @Autowired
    SalaRepositorio salaRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    EmailServico emailServico;

    public void cancelarAgendamento(List<Participante> participantes, Agendamento agendamento) {
        emailServico.enviarEmail(participantes, EmailUtils.emailCancelamento(agendamento), "Reunião cancelada.");
        for (int i = 0; i < participantes.size(); i++) {
            participanteServico.delete(participantes.get(i).getId());
        }
        this.delete(agendamento.getId());
    }

    public Page<Agendamento> findAll(Pageable pgbl) {
        return agendamentoRepositorio.findAll(pgbl);
    }

    public Iterable<Agendamento> findAll() {
        return agendamentoRepositorio.findAll();
    }

    public Agendamento save(Agendamento reserva) {
        return agendamentoRepositorio.save(reserva);
    }

    public void delete(Long id) {
        agendamentoRepositorio.delete(id);
    }

    public Agendamento findOne(Long id) {
        return agendamentoRepositorio.findOne(id);
    }

    public List<Participante> save(List<Long> idsUsuarios, Agendamento agendamento, Date dataInicial, Date dataFinal, Sala sala) throws RegraNegocioException {
        List<Usuario> usuarios;
        if (idsUsuarios != null) {
            usuarios = idsUsuarios.stream()
                    .map(id -> usuarioRepositorio.findOne(id))
                    .collect(Collectors.toList());
        } else {
            throw new RegraNegocioException("Ao menos um participante deve ser selecionado.");
        }
        if (salaEstaDisponivel(sala, dataInicial, dataFinal, usuarios.size(), agendamento)) {
            agendamento.setDataFinal(dataFinal);
            agendamento.setDataInicio(dataInicial);

            if (agendamento.getId() != null) {
                return participanteServico.update(agendamento.getParticipantes(), usuarios, agendamento);
            } else {
                agendamentoRepositorio.save(agendamento);
                return participanteServico.save(usuarios, agendamento);
            }
        } else {
            throw new RegraNegocioException("A sala está indisponível.");
        }
    }

    public boolean ehCriadorDoAgendamento(Usuario usuarioSessao, Agendamento agendamento) {
        return usuarioSessao.equals(agendamento.getCriador());
    }

    private boolean salaEstaDisponivel(Sala sala, Date dataInicial, Date dataFinal, int capacidade, Agendamento agendamento) {
        List<Sala> conflituosasPorData = salaRepositorio.findByIntervalo(dataInicial, dataFinal, agendamento.getId());
        List<Sala> conflituosasPorCapacidade = IteratorUtils.toList(salaRepositorio.findAll().iterator())
                .stream()
                .filter(s -> s.getCapacidade() < capacidade)
                .collect(Collectors.toList());
        return !conflituosasPorData.contains(sala) && !conflituosasPorCapacidade.contains(sala);
    }

    public void verificarPermissao(Agendamento agendamento, Usuario usuario) throws RegraNegocioException {
        if (!usuario.equals(agendamento.getCriador())) {
            throw new RegraNegocioException("O usuario da sessão não possui permissão para efetuar essa ação.");
        }
    }
}
