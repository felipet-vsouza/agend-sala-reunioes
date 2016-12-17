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

    /**
     *
     * @param dataInicial
     * @param dataFinal
     * @param equipamentoSelecionado
     * @param quantidadeSelecionado
     * @return
     */
    @Query(value = "SELECT NOME_SALA \n"
            + "          FROM SALA SA \n"
            + "          WHERE SA.ID_SALA IN \n"
            + "                (SELECT * \n"
            + "                 FROM SALA_EQUIPAMENTO SE, SALA  \n"
            + "                 WHERE SE.SALA_ID_SALA = SALA.ID_SALA\n"
            + "                 AND   SE.EQUIPAMENTO_ID_EQUIPAMENTO IN (:EQUIPAMENTOS)\n"
            + "    ) AND SA.ID_SALA NOT IN"
            + "                (SELECT * \n"
            + "                FROM AGENDAMENTO s\n"
            + "                WHERE\n"
            + "                  :DATAINICIAL BETWEEN s.DT_INICIO_AGENDAMENTO AND s.DT_FINAL_AGENDAMENTO\n"
            + "                  OR\n"
            + "                  :DATAFINAL BETWEEN s.DT_INICIO_AGENDAMENTO AND s.DT_FINAL_AGENDAMENTO\n"
            + "                  OR\n"
            + "                  s.DT_INICIO_AGENDAMENTO BETWEEN :DATAFINAL AND \n"
            + "                  :DATAINICIAL \n"
            + "                  OR\n"
            + "                  s.DT_FINAL_AGENDAMENTO BETWEEN :DATAINICIAL AND \n"
            + "                  :DATAFINAL\n"
            + "    AND             \n" 
            + "    SA.CAPACIDADE_SALA >= :QUANTIDADESELECIONADO", nativeQuery = true)
            public List<Sala> findAllSala(@Param("DATAINICIAL") Date dataInicial, @Param("DATAFINAL") Date dataFinal,
                                          @Param("EQUIPAMENTOS") List<Equipamento> equipamentos, 
                                          @Param("QUANTIDADESELECIONADO") Integer quantidadeSelecionado);
}
