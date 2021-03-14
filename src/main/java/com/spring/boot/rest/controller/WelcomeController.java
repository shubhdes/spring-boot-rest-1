package com.spring.boot.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.rest.entity.WelcomeEntity;

@RestController
public class WelcomeController {

	@GetMapping(path = "/welcomeWithQueryString")
	public String welcomeWithQueryString(
			@RequestParam(name = "name", required = false) String usernameFromQueryString) {

		StringBuilder response = new StringBuilder();

		response.append("Welcome ");

		if (null != usernameFromQueryString) {
			response.append(usernameFromQueryString + " ");
		}

		response.append("to spring boot's REST webservice");

		return response.toString();
	}

	@GetMapping(path = "/welcomeWithPathVariable/{name}")
	public String welcomeWithPathVariable(
			@PathVariable(name = "name", required = true) String usernameFromPathVariable) {

		StringBuilder response = new StringBuilder();

		response.append("Welcome ");
		response.append(usernameFromPathVariable + " ");
		response.append("to spring boot's REST webservice");

		return response.toString();
	}

	@PostMapping(path = "/welcomeWithRequestBody")
	public WelcomeEntity welcomeWithRequestBody(@RequestBody(required = true) WelcomeEntity welcomeEntity) {

		StringBuilder response = new StringBuilder();

		response.append("Welcome ");
		response.append(welcomeEntity.getName() + " ");
		response.append("to spring boot's REST webservice");

		welcomeEntity.setMessage(response.toString());

		return welcomeEntity;
	}
}
