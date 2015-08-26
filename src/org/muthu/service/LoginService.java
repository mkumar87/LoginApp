package org.muthu.service;

public class LoginService {
	
	public String authenticate(String userName , String password) {
		
		String message = "Success";
		if(userName == null || !userName.trim().equalsIgnoreCase("mkumar87")) {
			message = "User doesnot exists/User name is empty";
			return message;
		}
		if(password == null || !password.trim().equalsIgnoreCase("Mylife12")) {
			message = "Password is empty/does not match";
			return message;
		}
	
		return message;
	}

}
