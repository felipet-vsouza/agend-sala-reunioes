/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.utils;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Email;
import java.text.SimpleDateFormat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Henrique
 */
public class EmailUtils {
    
    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

    public static String emailCancelamento(Agendamento agendamento) {        
        return "Olá <br/>"
                + "A reunião foi cancelada. Detalhes: <br/>"
                + "Local: " + agendamento.getSala().getNome() + "<br/> "
                + "Hora de inicio: " + DATE_FORMAT.format(agendamento.getDataInicio()) + "<br/>"
                + "Marcada por: " + agendamento.getCriador().getNome() + "<br/><br/>"
                + "Obrigado!";
    }

    public static String emailConvite(Agendamento agendamento, Email email) {
        return "Olá <br/>"
                + "Voce recebeu um convite para uma reunião, enviado por " + agendamento.getCriador().getNome() + ". <br/>"
                + "Detalhes <br/> Local: " + agendamento.getSala().getNome() + "<br/> "
                + "Hora de inicio: " + DATE_FORMAT.format(agendamento.getDataInicio()) + "<br/>"
                + "Hora de termino: " + DATE_FORMAT.format(agendamento.getDataFinal()) + "<br/><br/>"
                + "<a href=\"http://localhost:9090/aceitarparticipacao/" + email.getHash() + ".com\">Aceitar</a> |"
                + "<a href=\"http://localhost:9090/recusarparticipacao/" + email.getHash() + ".com\">Recusar</a> <br/></br>"
                + "Sua reposta é muito importante. Obrigado!";
    }
}
