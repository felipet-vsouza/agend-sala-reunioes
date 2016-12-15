/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Equipamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author henrique.ostermann
 */
public interface EquipamentoRepositorio extends CrudRepository<Equipamento, Long>  {

    public Page<Equipamento> findAll(Pageable pgbl);    
}
