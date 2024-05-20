package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MapController {
	@GetMapping("/map-register")
	public String registerMapping() {
		return "register";
	}
	
	@GetMapping("/map-login")
	public String loginMapping() {
		return "login";
	}
	
	@GetMapping("/map-addSongs")
	public String addSongs() {
		return "addSongs";
	}
	@GetMapping("/map-displaySongs")
	public String displaySongs() {
		return "displaySong";
	}
	@GetMapping("/map-viewPlayList")
	public String viewPlayList() {
		return "viewPlayList";
	}
	@GetMapping("/map-createPlayList")
	public String createPlayList() {
		return "create";
	}
}
