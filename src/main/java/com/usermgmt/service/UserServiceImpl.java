package com.usermgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermgmt.config.InvalidInputException;
import com.usermgmt.model.User;
import com.usermgmt.repository.UserRepository;
import com.usermgmt.util.UserValidation;

import jakarta.transaction.Transactional;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public String createUser(User user) {
		if(!UserValidation.validateUsername(user.getUsername()))
			throw new InvalidInputException("Username is not in the specified format. Please check!");
		if(!UserValidation.validatePassword(user.getPassword()))
			throw new InvalidInputException("Password is not in the specified format. Please check!");
		userRepo.save(user);
		return "User created: " + user.getUsername();
	}

	@Override
	public User getUser(Long userId) {
		User user = userRepo.findById(userId).get();
		return user;
	}
	
	@Override
	@Transactional
	public User updateUser(User userUpdates) {
		if(userUpdates.getId() == null)
			throw new InvalidInputException("user id is missing in input!");
		
		User existingUser = userRepo.findById(userUpdates.getId()).get();
		if(userUpdates.getUsername() != null && !userUpdates.getUsername().isBlank()) {
			if(!UserValidation.validateUsername(userUpdates.getUsername()))
				throw new InvalidInputException("Username is not in the specified format. Please check!");
			existingUser.setUsername(userUpdates.getUsername());
		}
		if(userUpdates.getPassword() != null && !userUpdates.getPassword().isBlank()) {
			if(!UserValidation.validatePassword(userUpdates.getPassword()))
				throw new InvalidInputException("Password is not in the specified format. Please check!");
			existingUser.setPassword(userUpdates.getPassword());
		}
		if(userUpdates.getRoles() != null && !userUpdates.getRoles().isBlank()) {
			existingUser.setRoles(userUpdates.getRoles());
		}
		User updatedUser = userRepo.save(existingUser);
		log.info(updatedUser.getUsername() + " user info has been updated!");
		return updatedUser;
	}

	@Override
	public String deleteUser(Long userId) {
		User UserToBeDeleted = userRepo.findById(userId).get();
		String userName = UserToBeDeleted.getUsername();
		userRepo.deleteById(userId);
		
		String outputMsg = userName + " has been removed from our system";
		log.info(outputMsg);
		return outputMsg;
	}

}
