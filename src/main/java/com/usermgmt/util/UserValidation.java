package com.usermgmt.util;

public class UserValidation {

	public static boolean validateUsername(String username) {
		boolean isValid = true;
		if(username==null || username.length()<5 || username.length() > 30 || !username.matches("[a-zA-Z0-9]+")) {
			isValid = false;
		}
		return isValid;
	}
	
	public static boolean validatePassword(String password) {
		boolean isValid = true;
		if(password==null || password.length() < 7 ||  password.length() > 30 || password.matches("[a-zA-Z0-9]+")) {
			isValid = false;
		}
		return isValid;
	}
}
