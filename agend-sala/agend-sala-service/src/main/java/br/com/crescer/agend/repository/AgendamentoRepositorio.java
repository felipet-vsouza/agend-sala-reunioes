package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Agendamento;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AgendamentoRepositorio extends CrudRepository<Agendamento, Long> {

    public Page<Agendamento> findAll(Pageable pgbl);

    @Query(value = "   SELECT sum( "
            + "            DAY(a.dataFinal - a.dataInicio )*86400 + "
            + "            HOUR(a.dataFinal - a.dataInicio )*3600 + "
            + "            MINUTE(a.dataFinal - a.dataInicio )*60 + "
            + "            SECOND(a.dataFinal - a.dataInicio ))"
            + "        FROM Agendamento a "
            + "        JOIN a.sala s"
            + "        WHERE a.dataInicio >= :inicio "
            + "          AND a.dataFinal <= :fim"
            + "          AND s.id = :sala ")
    public Long findTempoOcupadoByAgendamentos(@Param("inicio") Date inicio, @Param("fim") Date fim, @Param("sala") Long id);

    @Query(value = "SELECT a" +
"                FROM Participante p" +
"                JOIN p.usuario u" +
"                JOIN p.agendamento a" +
"                JOIN a.sala sa" +
"                WHERE" +
"                  u.id = :usuario" +
"                  AND (" +
"                  :inicio BETWEEN a.dataInicio AND a.dataFinal" +
"                  OR" +
"                  :fim BETWEEN a.dataInicio AND a.dataFinal" +
"                  OR" +
"                  a.dataInicio BETWEEN :inicio AND :fim" +
"                  OR " +
"                  a.dataFinal BETWEEN :inicio AND :fim)")
    public List<Agendamento> findAgendamentoConflitantePorUsuario(@Param("usuario") Long id, @Param("inicio") Date inicio, @Param("fim") Date fim);
} 
