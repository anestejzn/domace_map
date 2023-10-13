package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.MailCannotBeSentException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HTMLEmailService {

    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String from, String to, String subject, String msg) throws MailCannotBeSentException {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(subject);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(msg, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            throw new MailCannotBeSentException(from);
        }
    }
}
