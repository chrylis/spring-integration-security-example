package com.chrylis.example.spring_integration_security;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spying")
public class SpyingController {

	/**
	 * Note that this injection is of a plain interface; this controller knows nothing directly about Spring Integration.
	 */
	@Autowired
	UrlSpy urlSpy;

	@RequestMapping("/controller/fast")
	public String verySuspicious() {
		return urlSpy.findNow();
	}

	@RequestMapping("/controller/slow")
	public String lessSuspicious() throws InterruptedException, ExecutionException {
		return urlSpy.findLater().get();
	}

	@Autowired
	UserSpy userSpy;

	@RequestMapping("/user/fast")
	public String mirror() {
		return userSpy.findMeNow();
	}

	@RequestMapping("/user/slow")
	public String securityCamera() throws InterruptedException, ExecutionException {
		return userSpy.findMeLater().get();
	}
}
