/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.utils;

import br.com.crescer.agend.entity.Agendamento;
import br.com.crescer.agend.entity.Email;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.CharEncoding;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 *
 * @author Henrique
 */
public class EmailUtils {

    private SpringTemplateEngine templateEngine;
    
    public EmailUtils() {
        TemplateResolver resolver = new TemplateResolver();
        resolver.setResourceResolver(new EmailResourceResolver());
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding(CharEncoding.UTF_8);
        resolver.setOrder(1);

        templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
    }

    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

    public String testeTemplate() {
        Context context = new Context();
        context.setVariable("teste", "teste");
        String actual = templateEngine.process("templateTeste", context);
        return actual;
    }

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
