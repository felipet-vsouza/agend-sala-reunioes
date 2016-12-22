package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Equipamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface EquipamentoRepositorio extends CrudRepository<Equipamento, Long>  {

    public Page<Equipamento> findAll(Pageable pgbl);    
}
