package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AudioController {

	@GetMapping(path="/godzilla", produces="text/plain")
	public ResponseEntity<byte[]> getGodzillaAudio() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity("http://localhost:8080/godzilla", byte[].class);
	}
	
	@GetMapping(path="/panic", produces="text/plain")
	public ResponseEntity<byte[]> getPanicAudio() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity("http://localhost:8080/panic-at-the-disco", byte[].class);
	}
	
	@GetMapping(path="/crawl", produces="text/plain")
	public ResponseEntity<byte[]> getCrawlOuttaLove() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity("http://localhost:8080/crawl-outta-love", byte[].class);
	}
	
}
