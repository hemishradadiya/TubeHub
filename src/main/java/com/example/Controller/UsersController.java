package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Song;
import com.example.Entities.Users;
import com.example.Service.SongService;
import com.example.Service.UsersService;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class UsersController {
	@Autowired
	UsersService userserv;
	
	@Autowired
	SongService songserv;
	
	@PostMapping("/register")
	public String addUser(@RequestBody Users user) {
		System.out.println(user);
		Boolean res = userserv.addUser(user);
		if(res) {
			return "registerFail";
		}else {
			System.out.println("suceess");
			return "registerSuccess";
		}
	}
	
	@PostMapping("/login")
	public String validateLogin(@RequestBody JsonNode loginCredencial, HttpSession session) {
		String email = loginCredencial.get("email").asText();
		String password = loginCredencial.get("password").asText();
		System.out.println(email+" "+password);
		boolean res = userserv.validateLogin(email, password);
		System.out.println(res);
		if(userserv.validateLogin(email, password)) {
			session.setAttribute("email", email);
			System.out.println("Login Success");
			if(userserv.getRole(email).equals("admin")) {
				return "adminHome";
			}else {
				return "customerHome";				
			}
		}else {
			System.out.println("Login Failed");
			return "loginFail";
		}
	}
	
	@GetMapping("/checkPremium")
	public String checkPremium(HttpSession session) {
		String email = (String)session.getAttribute("email");
		boolean PrimeStatus = userserv.isPremium(email);
		System.out.println(PrimeStatus);
		if(PrimeStatus) {
			return "exploreSongs";
		}else {
			return "samplePayment";
		}
	}
}
