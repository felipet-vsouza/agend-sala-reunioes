/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.web;

import br.com.crescer.agend.entity.Equipamento;
import br.com.crescer.agend.entity.Sala;
import br.com.crescer.agend.service.SalaServico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Henrique
 */
@Controller
public class SalasController {
    
    @Autowired
    SalaServico salaServico;
    
    @RequestMapping(value = {"/salas", "/find"})
    public String salas(Date dataInicio, Date dataFim, long capacidade) {
        
        //Obter da tela. TO-DO.
        List<Equipamento> equipamentos = new ArrayList<>();
        
        List<Sala> salas = salaServico.findAllSala(dataInicio, dataFim, capacidade, equipamentos);
        
        return "home";
    }
}
