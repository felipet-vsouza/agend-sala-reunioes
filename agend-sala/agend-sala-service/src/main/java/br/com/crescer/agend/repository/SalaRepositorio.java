/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Sala;
import java.awt.print.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author henrique.ostermann
 */
public interface SalaRepositorio extends CrudRepository<Sala, Long> {

    public Page<Sala> findAll(Pageable pgbl);
    
}

