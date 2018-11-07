package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.java.dto.Email;

@Service
public class EmailServiceV2Impl implements EmailServiceV2{

	@Autowired
	JavaMailSender gmailSender;
	@Autowired
	JavaMailSender outlookSender;
	
	@Override
	public void sendFromGmail(Email email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getAddress());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getContent());
		gmailSender.send(mailMessage);
	}

	@Override
	public void sendFromOutlook(Email email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getAddress());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getContent());
		outlookSender.send(mailMessage);
	}

}
