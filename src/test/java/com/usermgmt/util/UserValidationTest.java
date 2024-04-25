package com.usermgmt.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserValidationTest {

	@Test
	public void testUserValidationSuccess() {
		String username = "user0";
		String password = "password@123";
		boolean isUsernameValid = UserValidation.validateUsername(username);
		boolean isPassValid = UserValidation.validatePassword(password);
		Assertions.assertTrue(isUsernameValid);
		Assertions.assertTrue(isPassValid);
	}
	
	@Test
	public void testUsernameFailureScenarios() {
		String username1 = "user";
		String username2 = "user*";
		boolean isUsername1Valid = UserValidation.validateUsername(username1);
		boolean isUsername2Valid = UserValidation.validateUsername(username2);
		Assertions.assertFalse(isUsername1Valid);
		Assertions.assertFalse(isUsername2Valid);
	}
	
	@Test
	public void testPasswordFailureScenarios() {
		String pass1 = "pass0";
		String pass2 = "anything";
		String pass3 = "anything12345";
		boolean isPassValid1 = UserValidation.validatePassword(pass1);
		boolean isPassValid2 = UserValidation.validatePassword(pass2);
		boolean isPassValid3 = UserValidation.validatePassword(pass3);
		Assertions.assertFalse(isPassValid1);
		Assertions.assertFalse(isPassValid2);
		Assertions.assertFalse(isPassValid3);
	}
}
