package com.chrylis.example.spring_integration_security;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.stereotype.Component;

@Component
public class UrlSpyImpl {
	public String findSuspiciousController() {
		return "The suspicious controller can be found at " + linkTo(methodOn(SuspiciousController.class).say("")).toString();
	}
}
