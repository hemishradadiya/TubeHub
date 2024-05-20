package com.example.Service;

import com.example.Entities.Users;

public interface UsersService {
	Boolean addUser(Users user);
	boolean validateLogin(String email,String password);
	String getRole(String email);
	boolean isPremium(String email);
	Users getUser(String email);
	void updateUser(Users user);
}
