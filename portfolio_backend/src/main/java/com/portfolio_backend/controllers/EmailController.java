package com.portfolio_backend.controllers;

import com.portfolio_backend.models.dto.EmailDto;
import com.portfolio_backend.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(value = "http://localhost:5173")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> enviarMensaje (@RequestBody EmailDto emailDto) {
        try {
            String result = emailService.sendEmailWithValidation(emailDto);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El mensaje no pudo ser enviado correctamente");
        }
    }

}
