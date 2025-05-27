package com.iesports.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTemporaryPassword(String toEmail, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Contraseña temporal");
        message.setText("La contraseña temporal que se la sido asignada es: " + tempPassword);
        mailSender.send(message);
    }
} 
