package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepositorio extends CrudRepository<Email, Long> {
    
    Email findByhash(String hash);
}
