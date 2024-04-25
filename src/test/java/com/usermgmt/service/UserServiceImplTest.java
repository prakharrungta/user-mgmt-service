package com.usermgmt.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.usermgmt.config.InvalidInputException;
import com.usermgmt.model.User;
import com.usermgmt.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private UserServiceImpl userService;

	private User mockUser;

	@BeforeEach
	public void setup() {
		mockUser = new User();
		mockUser.setId(1L);
		mockUser.setUsername("user0");
		mockUser.setPassword("password@123");
	}

	@Test
	public void testGetUserSuccess() {
		given(userRepo.findById(1L)).willReturn(Optional.of(mockUser));
		User user = userService.getUser(1L);
		Assertions.assertEquals(mockUser, user);
	}
	
	@Test
    public void testCreateUserSuccess(){
        given(userRepo.save(mockUser)).willReturn(mockUser);

        String opMessage = userService.createUser(mockUser);
        System.out.println(opMessage);
        
        Assertions.assertEquals(opMessage, "User created: " + mockUser.getUsername());
    }
	
	@Test
    public void testCreateUserFailure(){
		User user = new User();
		user.setUsername("user");
		user.setPassword("password@123");
        
       Assertions.assertThrows(InvalidInputException.class, () -> {
    	   userService.createUser(user);
        });

        verify(userRepo, never()).save(any(User.class));
    }
	
	@Test
    public void testCreateUserFailure2(){
		User user = new User();
		user.setUsername("user123");
		user.setPassword("password123");
        
       Assertions.assertThrows(InvalidInputException.class, () -> {
    	   userService.createUser(user);
        });

        verify(userRepo, never()).save(any(User.class));
    }
	
	@Test
    public void testUpdateUserSuccess(){
		User existingUser = new User();
		existingUser.setId(1L);
		existingUser.setUsername("exsName");
		existingUser.setPassword("old#pass");
		
		User userUpdates = new User();
		userUpdates.setId(1L);
		userUpdates.setUsername("changedUsername");
		
		User updatedUser = new User();
		updatedUser.setId(1L);
		updatedUser.setPassword("old#pass");
		updatedUser.setUsername(userUpdates.getUsername());
		
		given(userRepo.findById(1L)).willReturn(Optional.of(existingUser));
        given(userRepo.save(existingUser)).willReturn(updatedUser);

        User actualUpdatedUser = userService.updateUser(userUpdates);
        
        Assertions.assertEquals(actualUpdatedUser, updatedUser);
    }
	
	@Test
    public void testUpdateUserSuccess2(){
		User existingUser = new User();
		existingUser.setId(1L);
		existingUser.setUsername("exsName");
		existingUser.setPassword("old#pass");
		
		User userUpdates = new User();
		userUpdates.setId(1L);
		userUpdates.setPassword("changed#Pass");
		
		User updatedUser = new User();
		updatedUser.setId(1L);
		updatedUser.setPassword("changed#Pass");
		updatedUser.setUsername(userUpdates.getUsername());
		
		given(userRepo.findById(1L)).willReturn(Optional.of(existingUser));
        given(userRepo.save(existingUser)).willReturn(updatedUser);

        User actualUpdatedUser = userService.updateUser(userUpdates);
        
        Assertions.assertEquals(actualUpdatedUser, updatedUser);
    }
	
}
