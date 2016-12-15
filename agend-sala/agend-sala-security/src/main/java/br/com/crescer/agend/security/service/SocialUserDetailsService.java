/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.security.service;

import br.com.crescer.agend.security.enumeration.AgendRoles;
import br.com.crescer.agend.service.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.crescer.agend.entity.Usuario;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Henrique
 */
@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioServico userService;

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {

        Usuario usuario = userService.findByEmail(firstName);

        if (usuario.getNome().isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", firstName));
        }

        return new User(usuario.getEmail(), usuario.getSenha(), AgendRoles.valuesToList());
    }
}
