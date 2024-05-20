package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.Users;

public interface usersRepository extends JpaRepository<Users,Integer>{
	
	boolean existsByemail(String email);
	Users findByEmail(String email);
}
