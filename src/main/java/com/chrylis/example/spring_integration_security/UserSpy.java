package com.chrylis.example.spring_integration_security;

import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.Payload;

@MessagingGateway
public interface UserSpy {
	String CHANNEL_NAME = "user-spy-channel";

	@Gateway(requestChannel = CHANNEL_NAME)
	@Payload("''")
	public String findMeNow();

	@Gateway(requestChannel = CHANNEL_NAME)
	@Payload("''")
	public Future<String> findMeLater();

}
