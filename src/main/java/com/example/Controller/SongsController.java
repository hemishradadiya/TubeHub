package com.example.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Entities.Song;
import com.example.Service.SongService;

@Controller
public class SongsController {
	@Autowired
	SongService songserv;
	
	@PostMapping("/addSong")
	public String addSong( Song song) {
		String msg = songserv.addSong(song);
		return msg;
	}	
	
	@GetMapping("/viewSong")
	public String viewSong(Model model) {
		List<Song> songList = songserv.viewSong();
		for(Song song:songList) {
			System.out.println(song);			
		}
		model.addAttribute("songList",songList);
		return "displaySongs";
	}
}
