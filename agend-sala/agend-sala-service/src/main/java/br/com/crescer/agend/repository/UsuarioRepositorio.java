package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {

    Usuario findByEmail(String username);
}
