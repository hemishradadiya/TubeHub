package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Song;
import com.example.Repositories.songRepository;

@Service
public class SongServiceImplementation implements SongService{
	@Autowired
	songRepository songrepo;
	
	@Override
	public String addSong(Song song) {
		if(songrepo.existsByName(song.getName())) {
			return "addSongFail";
		}else {
			songrepo.save(song);
			return "addSongSuccess";
		}
	}

	@Override
	public List<Song> viewSong() {
		return songrepo.findAll();
	}

	@Override
	public String updateSong(Song song) {
		songrepo.save(song);
		return "Song Updated";
	}
}
