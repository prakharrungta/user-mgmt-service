package com.usermgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermgmt.model.User;
import com.usermgmt.service.UserService;


@RequestMapping("user")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping
	public String createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@DeleteMapping("{userId}")
	public String deleteUser(@PathVariable Long userId) {
		return userService.deleteUser(userId);
	}

	@PutMapping
	public User updateUserInfo(@RequestBody User updatedUserInfo) {
		return userService.updateUser(updatedUserInfo);
	}
	
}
