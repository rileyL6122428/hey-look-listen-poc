package com.example.demo.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Map;
import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

@Controller
public class AudioController {
	
	@CrossOrigin
	@GetMapping(path="/godzilla", produces="audio/mpeg")
	@ResponseBody
	public FileSystemResource getGodzillaAudio() throws Exception {
		String filepath = "/Users/rileylittlefield/hey-look-listen/PoC/browser-audio/godzilla_roar.mp3";
//		readFileDurationViaMp3sri(filepath);
		readFileDurationViaFfmpeg(filepath);
		return new FileSystemResource(filepath);
	}
	
	@GetMapping(path="/panic-at-the-disco", produces="audio/mpeg")
	@ResponseBody
	public FileSystemResource getIMadeIt() throws Exception {
		String filepath = "/Users/rileylittlefield/hey-look-listen/PoC/browser-audio/panic-at-the-disco-hey-look-ma-i-made-it-official-video.mp3";
//		readFileDurationViaMp3sri(filepath);
		readFileDurationViaFfmpeg(filepath);
		return new FileSystemResource(filepath);
	}
	
	@GetMapping(path="/crawl-outta-love", produces="audio/mpeg")
	@ResponseBody
	public FileSystemResource getCrawlOuttaLove() throws Exception {
		String filepath = "/Users/rileylittlefield/hey-look-listen/PoC/browser-audio/crawl-outta-love.mp3";
//		readFileDurationViaMp3sri(filepath);
		readFileDurationViaFfmpeg(filepath);
		return new FileSystemResource(filepath);
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
	
	public void readFileDurationViaFfmpeg(String filepath) throws Exception {
		FFprobe ffprobe = new FFprobe("/usr/local/bin/ffprobe");
		FFmpegProbeResult probeResult = ffprobe.probe(filepath);
		FFmpegFormat format = probeResult.getFormat();
		
		System.out.format("%nFile: '%s' ; Format: '%s' ; Duration: %.3fs", 
			format.filename, 
			format.format_long_name,
			format.duration
		);
	}
}
