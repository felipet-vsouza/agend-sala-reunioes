/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Usuario;
import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Henrique
 */
@Service
public class EmailServico {
    
    public void enviarEmail(String[] destinatarios, String conteudo, String assunto, Usuario usuario){
        
        Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email", "senha");
            }
        });
        
        session.setDebug(true);
        try {

            String text = conteudo;

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("email"));

           Address[] toUser = null;
            
            for (String destinatario : destinatarios) {
                toUser = InternetAddress.parse(destinatario);
            }
            
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);
            message.setContent(text, "text/html; charset=utf-8");
            
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
