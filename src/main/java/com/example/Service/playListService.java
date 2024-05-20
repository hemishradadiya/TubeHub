package com.example.Service;
import java.util.List;
import com.example.Entities.PlayList;

public interface playListService {
	String addPlayList(PlayList playlist);
	List<PlayList> viewPlayList();
}
