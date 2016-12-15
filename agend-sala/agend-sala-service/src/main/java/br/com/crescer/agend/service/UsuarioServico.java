/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Henrique
 */
@Service
public class UsuarioServico {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public Usuario findByEmail(String username) {
        return usuarioRepositorio.findByEmail(username);
    }
}
