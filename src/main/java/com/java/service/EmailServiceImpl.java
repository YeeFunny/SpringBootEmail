package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.java.dto.Email;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSender sender;
	
	@Override
	public void sendEmail(Email email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getAddress());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getContent());
		sender.send(mailMessage);
	}

}
