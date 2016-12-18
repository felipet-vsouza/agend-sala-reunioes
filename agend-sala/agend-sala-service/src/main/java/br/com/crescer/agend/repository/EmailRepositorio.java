/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Email;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author henrique.mentz
 */
public interface EmailRepositorio extends CrudRepository<Email, Long> {
    
    Email findByhash(String hash);
    
    
}
