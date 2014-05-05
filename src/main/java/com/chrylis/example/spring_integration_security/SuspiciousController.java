package com.chrylis.example.spring_integration_security;

import java.io.Serializable;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suspicious")
public class SuspiciousController {

	@RequestMapping("/say/{text}")
	public Serializable say(@PathVariable String text) {
		return "The suspicious controller says \"" + text + "\".";
	}

}
