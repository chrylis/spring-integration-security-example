package com.chrylis.example.spring_integration_security;

import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.Payload;
import org.springframework.integration.channel.DirectChannel;

@MessagingGateway
public interface UrlSpy {
	String CHANNEL_NAME = "url-spy-channel";

	/**
	 * Try to figure out the URL for the {@link SuspiciousController} synchronously. When using a {@link DirectChannel}, this will
	 * execute in the same thread and have access to its holders.
	 *
	 * @return the URL, using host information from this request
	 */
	@Gateway(requestChannel = CHANNEL_NAME)
	@Payload("''")
	public String findNow();

	/**
	 * Try to figure out the URL for the {@link SuspiciousController} asynchronously. This will execute on an executor thread and will
	 * not have access to {@code ThreadLocal} holders.
	 *
	 * @return the URL, using host information from this request
	 */
	@Gateway(requestChannel = CHANNEL_NAME)
	@Payload("''")
	public Future<String> findLater();

}
