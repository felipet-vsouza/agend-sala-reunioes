package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.repository.EquipamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoServico {

    @Autowired
    EquipamentoRepositorio equipamentoRepositorio;

    public Iterable<Equipamento> findAll() {
        return equipamentoRepositorio.findAll();
    }

    public Equipamento save(Equipamento equipamento) {
        return equipamentoRepositorio.save(equipamento);
    }

    public void delete(Long id) {
        equipamentoRepositorio.delete(id);
    }

    public Equipamento findOne(Long id) {
        return equipamentoRepositorio.findOne(id);
    }
}
