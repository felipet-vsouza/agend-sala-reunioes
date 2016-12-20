/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.entity.Sala;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author henrique.ostermann
 */
public interface SalaRepositorio extends CrudRepository<Sala, Long> {

    public Page<Sala> findAll(Pageable pgbl);

    @Query(value = ""
            + " SELECT * "
            + "   FROM SALA SA "
            + "  WHERE SA.CAPACIDADE_SALA >= :CAPACIDADE "
            + "    AND SA.ID_SALA NOT IN ( "
            + "     SELECT S.ID_SALA "
            + "       FROM AGENDAMENTO S "
            + "      WHERE ( "
            + "          :DATAINICIAL BETWEEN S.DT_INICIO_AGENDAMENTO AND S.DT_FINAL_AGENDAMENTO "
            + "          OR :DATAFINAL BETWEEN S.DT_INICIO_AGENDAMENTO AND S.DT_FINAL_AGENDAMENTO "
            + "          OR S.DT_INICIO_AGENDAMENTO BETWEEN :DATAINICIAL AND :DATAFINAL "
            + "          OR S.DT_FINAL_AGENDAMENTO BETWEEN :DATAINICIAL AND :DATAFINAL "
            + "         ) "
            + "         AND NOT (S.DT_INICIO_AGENDAMENTO = :DATAFINAL) "
            + "         AND NOT (S.DT_FINAL_AGENDAMENTO = :DATAINICIAL)"
            + "      ) ", nativeQuery = true)
    public List<Sala> filtroDeSalas(@Param("DATAINICIAL") Date dataInicial, @Param("DATAFINAL") Date dataFinal,
            @Param("CAPACIDADE") Long capacidade);

    @Query("SELECT S FROM Agendamento A JOIN A.sala S WHERE A.dataInicio >= :inicio OR A.dataFinal <= :fim AND S.capacidade >= :capacidade ")
    public List<Sala> findByIntervalo(@Param("inicio") final Date dataInicio, @Param("fim") final Date dataFinal, @Param("capacidade")  Integer capacidade);

}
