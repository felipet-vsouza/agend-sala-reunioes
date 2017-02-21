package br.com.crescer.agend.service;

import br.com.crescer.agend.entity.Email;
import br.com.crescer.agend.entity.Participante;
import br.com.crescer.agend.repository.EmailRepositorio;
import java.util.Date;
import java.util.List;
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
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailServico {

    @Autowired
    EmailRepositorio emailRepositorio;

    public Email findByHash(String hash) {
        return emailRepositorio.findByhash(hash);
    }

    public Email salvar(Email email) {
        return emailRepositorio.save(email);
    }

    public void delete(Email email) {
        emailRepositorio.delete(email);
    }

    public boolean hashEhValido(Email email) {
        if (email == null) {
            return false;
        }
        return email.getParticipante().getAgendamento().getDataInicio().after(new Date());
    }

    public void enviarEmail(Participante participante, String conteudo, String assunto) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("agendamentosalascwi@gmail.com", "********");
            }
        });

        session.setDebug(true);
        try {

            String text = conteudo;

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("agendamentosalascwi@gmail.com"));

            Address[] toUser = InternetAddress.parse(participante.getUsuario().getEmail());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);
            message.setContent(text, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarEmail(List<Participante> destinatarios, String conteudo, String assunto) {
        for (int i = 0; i < destinatarios.size(); i++) {
            this.enviarEmail(destinatarios.get(i), conteudo, assunto);
        }
    }
}
