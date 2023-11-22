package com.kh.com.kh.domain.dao.MemberDAO;

import jakarta.mail.MessagingException;

import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailService {

  private SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

  private final JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String username;

  public void sendMail(String to, String subject, String body){

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    try{

      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

      mimeMessageHelper.setFrom(username);

      mimeMessageHelper.setTo(to);

      mimeMessageHelper.setSubject(subject);

      mimeMessageHelper.setText(body,true);

      javaMailSender.send(mimeMessage);

    }catch(MessagingException e){

      e.printStackTrace();

    }

  }

  public void snedSimpleMail(String message){

    simpleMailMessage.setText(message);

    javaMailSender.send(simpleMailMessage);

  }

}