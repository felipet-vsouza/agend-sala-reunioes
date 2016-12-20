/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.web;

import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.service.EquipamentoServico;
import br.com.crescer.agend.service.SalaServico;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Henrique
 */
@Controller
public class SalasController {
    
    @Autowired
    SalaServico salaServico;
    
    @Autowired
    EquipamentoServico equipamentoServico;
    
    @RequestMapping(value = {"/salas/find"})
    public String salas(Model model, Date dataInicio, Date dataFim, int capacidade, @RequestParam(value="equipamentos[]", required = false) List<Long> equipamentos) {
        List<Sala> salas = salaServico.FiltroSalas(dataInicio, dataFim, capacidade, equipamentos);
        model.addAttribute("salas", salas);
        return "fragments :: lista-salas";
    }
}
