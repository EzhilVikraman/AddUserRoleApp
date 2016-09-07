package com.compass;

import org.picketlink.idm.credential.util.BCrypt;
public class Encoder {
	
	public boolean verify(String rawPassword, String encodedPassword) {
	   return BCrypt.checkpw(rawPassword, encodedPassword);
	}

	
}
