/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.utils;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Email;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 *
 * @author Henrique
 */
public class EmailUtils {

    public EmailUtils() {
        
    }

    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

    public static String emailCancelamento(Agendamento agendamento) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache template = mf.compile("src/main/resources/templates/emailCancelamento.html");

        StringWriter writer = new StringWriter();
        HashMap<String, String> scopes = new HashMap<>();
        scopes.put("criador", agendamento.getCriador().getNome());
        scopes.put("horaInicio", DATE_FORMAT.format(agendamento.getDataInicio()));
        scopes.put("descricao", agendamento.getDescricao());
        scopes.put("sala", agendamento.getSala().getNome());

        template.execute(writer, scopes);

        return writer.toString();
    }

    public String emailConvite(Agendamento agendamento, Email email) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache template = mf.compile("src/main/resources/templates/emailConvite.html");

        StringWriter writer = new StringWriter();
        HashMap<String, String> scopes = new HashMap<>();
        scopes.put("criador", agendamento.getCriador().getNome());
        scopes.put("horaInicio", DATE_FORMAT.format(agendamento.getDataInicio()));
        scopes.put("horaTermino", DATE_FORMAT.format(agendamento.getDataFinal()));
        scopes.put("hash", email.getHash());
        scopes.put("descricao", agendamento.getDescricao());
        scopes.put("sala", agendamento.getSala().getNome());

        template.execute(writer, scopes);

        return writer.toString();
    }
}
