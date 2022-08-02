package com.neosave.project.service;

import com.neosave.project.entity.User;

public interface UserService {

	
	
	User getUser(Integer id);

	User saveUser(String name, String email, String addressPinCode);
	
	
	
}
