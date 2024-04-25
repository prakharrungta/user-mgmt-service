package com.usermgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermgmt.model.User;
import com.usermgmt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public String createUser(User user) {
		userRepo.save(user);
		return "User created!";
	}

	@Override
	public User getUser(Long userId) {
		User user = userRepo.findById(userId).get();
		return user;
	}

}
