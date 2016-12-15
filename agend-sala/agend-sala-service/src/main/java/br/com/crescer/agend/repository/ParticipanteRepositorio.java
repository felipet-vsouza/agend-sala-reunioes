/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.repository;

import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.entity.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author henrique.ostermann
 */
public interface ParticipanteRepositorio extends CrudRepository<Participante, Long> {

    public Iterable<Participante> findAll();

    public List<Participante> findByUsuario(Usuario usuario);

}
