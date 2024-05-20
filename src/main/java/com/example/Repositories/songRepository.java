package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entities.Song;

public interface songRepository extends JpaRepository<Song, Integer>{
	boolean existsByName(String name);
}
