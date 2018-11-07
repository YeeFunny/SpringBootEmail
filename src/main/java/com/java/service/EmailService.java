package com.java.service;

import com.java.dto.Email;
import com.java.dto.EmailNew;

public interface EmailService {

	void sendEmail(Email email);

	void sendRoutingEmail(EmailNew email);
}
