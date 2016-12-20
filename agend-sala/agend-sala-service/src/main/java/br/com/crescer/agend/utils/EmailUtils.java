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
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 *
 * @author Henrique
 */
public class EmailUtils {

    public EmailUtils() {
        
        Set<ITemplateResolver> templatesResolvers = new HashSet<>();

        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setPrefix("/templates/");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setOrder(1);
        templatesResolvers.add(emailTemplateResolver);

        templateEngine.setTemplateResolvers(templatesResolvers);

    }

    private final SpringTemplateEngine templateEngine = new SpringTemplateEngine();

    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

    public String testeTemplate() {
        final Context ctx = new Context();
        String teste = "TESTE EMAIL";
        ctx.setVariable("teste", teste);

        final String htmlContent = this.templateEngine.process("login", ctx);

        return htmlContent;
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
