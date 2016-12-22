package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.crescer.agend.repository.AgendamentoRepositorio;
import br.com.crescer.agend.repository.SalaRepositorio;
import br.com.crescer.agend.repository.UsuarioRepositorio;
import br.com.crescer.agend.utils.EmailUtils;
import java.util.Calendar;
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

    public void manterAgendamento(List<Long> idsUsuarios, Agendamento agendamento, Date dataInicial, Date dataFinal, Sala sala) throws RegraNegocioException {
        List<Usuario> usuarios = obterListaDeUsuarios(idsUsuarios);
        
        if (salaEstaDisponivel(sala, dataInicial, dataFinal, usuarios.size(), agendamento)) {
            agendamento.setDataFinal(dataFinal);
            agendamento.setDataInicio(dataInicial);

            if (agendamento.getId() != null) {
                participanteServico.atualizarParticipacao(agendamento.getParticipantes(), usuarios, agendamento);
            } else {
                agendamentoRepositorio.save(agendamento);
                participanteServico.adicionarParticipacao(usuarios, agendamento);
            }
        } else {
            throw new RegraNegocioException("A sala está indisponível.");
        }
    }

    public boolean ehCriadorDoAgendamento(Usuario usuarioSessao, Agendamento agendamento) {
        return usuarioSessao.equals(agendamento.getCriador());
    }

    public void verificarPermissao(Agendamento agendamento, Usuario usuario) throws RegraNegocioException {
        if (!usuario.equals(agendamento.getCriador())) {
            throw new RegraNegocioException("O usuario da sessão não possui permissão para efetuar essa ação.");
        }
    }

    public int findTempoOcupadoByAgendamentos(Sala sala) {
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

        List<Agendamento> agendamentosDoDia = agendamentoRepositorio.findAgendamentosByDatasAndBySala(dateInicial, dateFinal, sala.getId());
        long tempoOcupado = agendamentosDoDia.stream()
                .map(a -> (a.getDataFinal().getTime() - a.getDataInicio().getTime()) / 1000)
                .mapToInt(i -> i.intValue())
                .sum();
        long tempoDisponivelPorDia = (dateFinal.getTime() - dateInicial.getTime()) / 1000;
        return (int)((((double) tempoOcupado) / tempoDisponivelPorDia) * 100);
    }

    
    public List<Agendamento> findAgendamentoConflitantePorUsuario(Long id, Date inicio, Date fim){
        return agendamentoRepositorio.findAgendamentoConflitantePorUsuario(id, inicio, fim);
    }
    
    public void cancelarAgendamento(List<Participante> participantes, Agendamento agendamento) {
        emailServico.enviarEmail(participantes, EmailUtils.emailCancelamento(agendamento), "Reunião cancelada.");
        for (int i = 0; i < participantes.size(); i++) {
            participanteServico.delete(participantes.get(i).getId());
        }
        this.delete(agendamento.getId());
    }
    
    private List<Usuario> obterListaDeUsuarios(List<Long> idsUsuarios) throws RegraNegocioException {
        List<Usuario> usuarios;
        
        if (idsUsuarios != null) {
            usuarios = idsUsuarios.stream()
                    .map(id -> usuarioRepositorio.findOne(id))
                    .collect(Collectors.toList());
        } else {
            throw new RegraNegocioException("Ao menos um participante deve ser selecionado.");
        }
        return usuarios;
    }
    
    private boolean salaEstaDisponivel(Sala sala, Date dataInicial, Date dataFinal, int capacidade, Agendamento agendamento) {
        List<Sala> conflituosasPorData = salaRepositorio.findByIntervalo(dataInicial, dataFinal, agendamento.getId());
        List<Sala> conflituosasPorCapacidade = IteratorUtils.toList(salaRepositorio.findAll().iterator())
                .stream()
                .filter(s -> s.getCapacidade() < capacidade)
                .collect(Collectors.toList());
        return !conflituosasPorData.contains(sala) && !conflituosasPorCapacidade.contains(sala);
    }
}
