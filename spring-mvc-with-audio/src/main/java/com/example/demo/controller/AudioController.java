package com.example.demo.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AudioController {
	
	
	@GetMapping(path="/godzilla", produces="audio/mpeg")
	@ResponseBody
	public FileSystemResource getGodzillaAudio() {
		return new FileSystemResource("/Users/rileylittlefield/hey-look-listen/PoC/browser-audio/godzilla_roar.mp3");
	}
	
	@GetMapping(path="/panic-at-the-disco", produces="audio/mpeg")
	@ResponseBody
	public FileSystemResource getIMadeIt() {
		return new FileSystemResource("/Users/rileylittlefield/hey-look-listen/PoC/browser-audio/panic-at-the-disco-hey-look-ma-i-made-it-official-video.mp3");
	}
}
