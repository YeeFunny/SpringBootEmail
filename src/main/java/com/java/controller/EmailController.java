package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.Email;
import com.java.dto.Result;
import com.java.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService service;
	
	@PostMapping(path = "/sendEmail", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Result> sendEmail(@RequestBody Email email) {
		Result result = null;
		HttpStatus status = null;
		try {
			service.sendEmail(email);
			result = Result.builder().resultCode(2000)
					.description("Send email successfully.").success(true).build();
			status = HttpStatus.OK;
		} catch(MailParseException e) {
			result = Result.builder().resultCode(4002)
					.description("Fail to parse the mail content.").success(false).build();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch(MailAuthenticationException e) {
			result = Result.builder().resultCode(4003)
					.description("Invalid target email address.").success(false).build();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch(MailSendException e) {
			result = Result.builder().resultCode(4001)
					.description("Fail to send the email.").success(false).build();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch(MailException e) {
			result = Result.builder().resultCode(5001)
					.description("Fail to send the email.").success(false).build();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Result>(result, status);
	}
}
