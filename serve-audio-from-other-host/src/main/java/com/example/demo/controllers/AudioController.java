package com.example.demo.controllers;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Random;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

@Controller
public class AudioController {
	
	private Random random = new Random(); 

	@GetMapping(path="/godzilla", produces="text/plain")
	public ResponseEntity<byte[]> getGodzillaAudio() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate.getForEntity("http://localhost:8080/godzilla", byte[].class);
		readFileDurationViaFfmpeg(response.getBody());
		return response;
	}
	
	@GetMapping(path="/panic", produces="text/plain")
	public ResponseEntity<byte[]> getPanicAudio() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate.getForEntity("http://localhost:8080/panic-at-the-disco", byte[].class);
		readFileDurationViaFfmpeg(response.getBody());
		return response;
	}
	
	@GetMapping(path="/crawl", produces="text/plain")
	public ResponseEntity<byte[]> getCrawlOuttaLove() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate.getForEntity("http://localhost:8080/crawl-outta-love", byte[].class);
		readFileDurationViaFfmpeg(response.getBody());
		return response;
	}
	
	public void readFileDurationViaMp3sri(String filepath) throws Exception {
		File file = new File(filepath);
		AudioFileFormat baseFileFormat = null;
		AudioFormat baseFormat = null;
		baseFileFormat = AudioSystem.getAudioFileFormat(file);
		baseFormat = baseFileFormat.getFormat();
		// TAudioFileFormat properties
		if (baseFileFormat instanceof TAudioFileFormat) {
		    Map properties = ((TAudioFileFormat)baseFileFormat).properties();
		    String key = "author";
		    String val = (String) properties.get(key);
		    key = "mp3.id3tag.v2";
		    InputStream tag= (InputStream) properties.get(key);
		    System.out.println("DURATION " + properties.get("duration"));
		}
		// TAudioFormat properties
		if (baseFormat instanceof TAudioFormat) {
		     Map properties = ((TAudioFormat)baseFormat).properties();
		     String key = "bitrate";
		     Integer val = (Integer) properties.get(key);
		}
	}
	
	public void readFileDurationViaFfmpeg(byte[] fileBytes) throws Exception {
		String tempFilePath = "/Users/rileylittlefield/hey-look-listen/PoC/temp-files/temp." + random.nextFloat() + ".test";
		File tempFile = new File(tempFilePath);
		Files.write(tempFile.toPath(), fileBytes);
		
		FFprobe ffprobe = new FFprobe("/usr/local/bin/ffprobe");
		FFmpegProbeResult probeResult = ffprobe.probe(tempFilePath);
		FFmpegFormat format = probeResult.getFormat();
		
		System.out.format("%nFile: '%s' ; Format: '%s' ; Duration: %.3fs", 
			format.filename, 
			format.format_long_name,
			format.duration
		);
		
		Files.delete(tempFile.toPath());
	}
	
}
