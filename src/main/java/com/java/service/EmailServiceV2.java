package com.java.service;

import com.java.dto.Email;

public interface EmailServiceV2 {

	void sendFromGmail(Email email);
	
	void sendFromOutlook(Email email);
}
