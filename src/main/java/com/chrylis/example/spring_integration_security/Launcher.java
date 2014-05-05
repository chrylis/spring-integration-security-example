package com.chrylis.example.spring_integration_security;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Launcher {
	public static void main(String[] args) {
		// eliminate this profile to use DirectChannels; the synchronous operations work in that case
		new SpringApplicationBuilder(Launcher.class).profiles("executor").run(args);
	}

	@Configuration
	@EnableIntegration
	@IntegrationComponentScan
	public static class IntegrationActivators {

		// ---------- URL spy ----------
		@Autowired
		private UrlSpyImpl spy;

		@ServiceActivator(inputChannel = UrlSpy.CHANNEL_NAME)
		public String spySync() {
			return spy.findSuspiciousController();
		}

		// ---------- user spy ----------

		@Autowired
		private UserSpyImpl userSpy;

		@ServiceActivator(inputChannel = UserSpy.CHANNEL_NAME)
		public String userSpySync() {
			return userSpy.whoami();
		}
	}

	@Configuration
	@Profile("!executor")
	public static class DirectChannelPlan {

		@PostConstruct
		public void log() {
			System.err.println("using direct channels");
		}

		@Bean(name = UrlSpy.CHANNEL_NAME)
		public MessageChannel urlChannel() {
			return new DirectChannel();
		}

		@Bean(name = UserSpy.CHANNEL_NAME)
		public MessageChannel userChannel() {
			return new DirectChannel();
		}
	}

	@Configuration
	@Profile("executor")
	public static class ExecutorChannelPlan {

		@PostConstruct
		public void log() {
			System.err.println("using executor channels");
		}

		@Bean
		public Executor executor() {
			return Executors.newFixedThreadPool(1);
		}

		@Bean(name = UrlSpy.CHANNEL_NAME)
		public MessageChannel urlChannel() {
			return new ExecutorChannel(executor());
		}

		@Bean(name = UserSpy.CHANNEL_NAME)
		public MessageChannel userChannel() {
			return new ExecutorChannel(executor());
		}
	}
}
