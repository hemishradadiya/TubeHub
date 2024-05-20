package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entities.Users;
import com.example.Repositories.usersRepository;

@Service
public class UserServiceImplementation implements UsersService{
	
	@Autowired
	usersRepository userrepo;
	
	@Override
	public Boolean addUser(Users user) {
		if(userrepo.existsByemail(user.getEmail())) {
			return true;
		}else {
			userrepo.save(user);
			return false;
		}
	}
	
	@Override
	public void updateUser(Users user) {
		userrepo.save(user);
	}
	

	@Override
	public boolean validateLogin(String email,String password) {
		Users user = userrepo.findByEmail(email);
		System.out.println("repo "+email);
		if(user!=null) {
			String db_password = user.getPassword();
			if(db_password.equals(password)){
				return true;
			}else {
				return false;			
			}			
		}else {
			return false;			
		}
	}

	@Override
	public String getRole(String email) {
		return (userrepo.findByEmail(email).getRole());
	}

	@Override
	public boolean isPremium(String email) {
		return userrepo.findByEmail(email).isPremium();
	}

	@Override
	public Users getUser(String email) {
		return userrepo.findByEmail(email);
	}

	
	
}
