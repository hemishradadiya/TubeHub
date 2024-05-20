package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.Entities.PlayList;
import com.example.Entities.Song;
import com.example.Service.SongService;
import com.example.Service.playListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class playListController {
	@Autowired
	playListService plserv;
	
	@Autowired
	SongService songserv;
	
	@GetMapping("/createPlayList")
	public String createPlayList(Model model) {
		List<Song> songlist = songserv.viewSong();
		model.addAttribute("songlist", songlist);
		return "createPlayList";
	}
	
	@PostMapping("/addPlayList")
	public String addPlayList(@ModelAttribute PlayList playlist) {
		String msg = plserv.addPlayList(playlist);
		List<Song> songList = playlist.getSong();
		for(Song song:songList) {
			song.getPlayList().add(playlist);
			String res = songserv.updateSong(song);
			System.out.println(res);
		}
		System.out.println(msg);
		return "PlayListSuccess";
	}
	
	
	@GetMapping("/viewPlayList")
	public String viewPlayList(Model model) {
			List<PlayList> plist = plserv.viewPlayList();
			System.out.println(plist);
			model.addAttribute("plist",plist);
		return "viewPlayList";
	}
	
}
