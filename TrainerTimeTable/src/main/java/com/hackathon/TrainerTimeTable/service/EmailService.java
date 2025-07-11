//package com.hackathon.TrainerTimeTable.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    @Autowired private JavaMailSender mailSender;
//
//    @Bean
//    public void sendWelcomeEmail(String toEmail, String empName, long empId) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setSubject("Welcome to Anudip - CMIS");
//        message.setText("Dear " + empName + ",\n\n"
//            + "Welcome to the Anudip family!\n"
//            + "Your employee ID is: " + empId + "\n\n"
//            + "Regards,\nAnudip CMIS Team");
//
//        mailSender.send(message);
//    }
//}
