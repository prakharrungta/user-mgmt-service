package com.usermgmt.service;

import com.usermgmt.model.User;

public interface UserService {

	public String createUser(User user);
	public User getUser(Long userId);
	
}
