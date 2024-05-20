package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.PlayList;
import com.example.Repositories.playListRepository;

@Service
public class playListServiceImplementation implements playListService{
	@Autowired
	playListRepository plrepo;

	@Override
	public String addPlayList(PlayList playlist) {
		plrepo.save(playlist);
		return "PlayList Added Successfully";
	}

	@Override
	public List<PlayList> viewPlayList() {
//		plrepo.findAll();
		return plrepo.findAll();
	}
	
}
