package com.portfolio_backend.services;

import com.portfolio_backend.models.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmailWithValidation(EmailDto emailDto) throws MessagingException {
        if (!isValidEmail(emailDto.getFrom())) {
            return "Email entered is incorrect";
        }

        try {
            sendEmail(emailDto);
            return "Message was successfully sent";
        } catch (MessagingException e) {
            // Puedes manejar excepciones específicas si ocurren problemas durante el envío del correo
            throw new MessagingException("Error sending query", e);
        }
    }

    public String sendEmail(EmailDto emailDto) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String asunto = "Nueva consulta de " + emailDto.getName();
        String mensaje = "Nombre: " + emailDto.getName() + "\nCorreo: " + emailDto.getFrom() + "\nMensaje: " + emailDto.getMessage();

        helper.setTo("dardosanchez2000@gmail.com");
        helper.setSubject(asunto);

        helper.setText(mensaje, false);

        mailSender.send(mimeMessage);

        return "Correo enviado correctamente!";
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
