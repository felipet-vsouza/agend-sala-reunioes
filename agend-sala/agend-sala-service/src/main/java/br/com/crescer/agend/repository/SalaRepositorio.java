package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Sala;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SalaRepositorio extends CrudRepository<Sala, Long> {

    public Page<Sala> findAll(Pageable pgbl);

    @Query("   SELECT S "
            + "FROM Agendamento A "
            + "JOIN A.sala S "
            + "WHERE (:agendamento IS NULL OR A.id != :agendamento) "
            + "  AND (NOT (:inicio < A.dataInicio OR :inicio >= A.dataFinal)"
            + "  OR   NOT (:fim <= A.dataInicio OR :fim > A.dataFinal)"
            + "  OR       (A.dataInicio < :fim AND A.dataInicio > :inicio)"
            + "  OR       (A.dataFinal < :fim AND A.dataFinal > :inicio))")
    public List<Sala> findByIntervalo(@Param("inicio") final Date dataInicio, @Param("fim") final Date dataFinal, @Param("agendamento") Long id);

}
