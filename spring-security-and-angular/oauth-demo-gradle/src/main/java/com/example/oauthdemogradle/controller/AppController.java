package com.example.oauthdemogradle.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Value("${security.oauth2.resource.id}")
	private String resourceId;
	
	@Value("${auth0.domain}")
	private String domain;
	
	@Value("${auth0.clientId}")
	private String clientId;
	
	@CrossOrigin
	@GetMapping(value="/api/public", produces="application/json")
	@ResponseBody
	public String publicEndpoint() throws Exception {
		return new JSONObject()
			.put("message", "Hello from a public endpoint! You don't need to be authenticate to see this.")
			.toString();
	}
	
	@CrossOrigin
	@GetMapping(value="/api/private", produces="application/json")
	@ResponseBody
	public String privateEndpoint() throws Exception {
		return new JSONObject()
			.put("message", "Hello from a private endpoint! You need to be authenticated to see this.")
			.toString();
	}
	
	@GetMapping(value="/api/private-scoped", produces="application/json")
	@ResponseBody
	public String privateScopedEndpoint() throws Exception {
		return new JSONObject()
			.put("message", "Hello from a private endpoint! You need to be authenticated and have a scope of read:messages to see this.")
			.toString();
	}
	
	@GetMapping(value="/config", produces="application/json")
	@ResponseBody
	public String getAppConfigs() throws Exception {
		return new JSONObject()
			.put("domain", domain)
			.put("clientID", clientId)
			.put("audience", resourceId)
			.toString();
	}
	
}
