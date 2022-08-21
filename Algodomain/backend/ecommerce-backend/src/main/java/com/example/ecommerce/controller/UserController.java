package com.example.ecommerce.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;


@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	//get all user
	@GetMapping("/users")
	public List<User> getAllUser()
	{
		return userRepository.findAll();
	}
	
	//get all user
	@PostMapping("/Login")
	public String Login(@RequestBody User user)
	{
		String IsValid = "Invalid User";
		List<User> UserList = userRepository.findAll();
		
		for(int i=0; i< UserList.size(); i++) {
			
			User UUser = UserList.get(i);
			if(UUser.getUserName().equalsIgnoreCase(user.getUserName()) && UUser.getPassword().equals(user.getPassword())) {
				IsValid = "Valid User";
			}
		}
		
		return IsValid;
	}
	
	//add user
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) 
	{
		return userRepository.save(user);
	}
}
	

