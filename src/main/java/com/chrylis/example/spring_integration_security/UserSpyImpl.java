package com.chrylis.example.spring_integration_security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSpyImpl {
	public String whoami() {
		return "You are " + SecurityContextHolder.getContext().getAuthentication().toString() + ".";
	}
}
