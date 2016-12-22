package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Usuario;
import br.com.crescer.agend.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioServico {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public Usuario findByEmail(String username) {
        return usuarioRepositorio.findByEmail(username);
    }

    public Usuario save(Usuario usuario) {
        String senha = usuario.getSenha();
        senha = new BCryptPasswordEncoder().encode(senha);
        usuario.setSenha(senha);
        usuarioRepositorio.save(usuario);
        return usuario;
    }

    public Usuario update(Usuario usuario) {
        usuarioRepositorio.save(usuario);
        return usuario;
    }

    public Iterable<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public Usuario findOne(Long id) {
        return usuarioRepositorio.findOne(id);
    }

    public Usuario obterUsuarioDaSessao() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        } else {
            User user = (User) auth.getPrincipal();
            Usuario usuario = findByEmail(user.getUsername());
            return usuario;
        }
    }
}
