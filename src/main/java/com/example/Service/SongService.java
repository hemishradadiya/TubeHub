package com.example.Service;

import java.util.List;

import com.example.Entities.Song;

public interface SongService {
	String addSong(Song song);
	List<Song> viewSong();
	String updateSong(Song song);
}
