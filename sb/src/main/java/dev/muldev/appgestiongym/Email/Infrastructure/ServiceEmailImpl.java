package dev.muldev.appgestiongym.email.infrastructure;

import dev.muldev.appgestiongym.email.domain.EmailValue;
import dev.muldev.appgestiongym.email.domain.ServiceEmail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ServiceEmailImpl implements ServiceEmail {

    private final JavaMailSender emailSender;

    public ServiceEmailImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    @Override
    public void sendEmailWithCredentials(String username, String to, String pass) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            String htmlMsg = new EmailValue(username,pass).getCred();
            mimeMessage.setContent(htmlMsg, "text/html");

            helper.setTo(to);
            helper.setSubject("Alta gimnasio");
            helper.setFrom("gym@muldev.dev");
            emailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            Logger.getLogger(ServiceEmailImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
