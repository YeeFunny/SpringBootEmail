package com.java.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.java.dto.Email;
import com.java.dto.Result;

public class EmailControllerTests {

	@Test
	public void test1() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
        //Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));         
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);  
		Email email = new Email("yliu40@email.wm.edu", "Test spring email"
				, "Greeting from spring email, this is a test email.");
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Email> entity = new HttpEntity<>(email, header);
		ResponseEntity<Result> response = restTemplate.exchange("http://localhost:8080/sendEmail"
				, HttpMethod.POST, entity, Result.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
