/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Henrique
 */
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String username);
}

