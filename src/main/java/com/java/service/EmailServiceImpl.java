package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.java.dto.Email;
import com.java.dto.EmailNew;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSender gmailSender;
	@Autowired
	JavaMailSender outlookSender;
	
	@Override
	public void sendEmail(Email email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getAddress());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getContent());
		gmailSender.send(mailMessage);
	}

	@Override
	public void sendRoutingEmail(EmailNew email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getTo());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getContent());
		if (email.getFrom().equals("outlook")) {
			outlookSender.send(mailMessage);
		}
		else if (email.getFrom().equals("gmail")) {
			gmailSender.send(mailMessage);
		}
			
	}

}
