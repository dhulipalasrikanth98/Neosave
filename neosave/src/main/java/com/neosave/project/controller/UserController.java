package com.neosave.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosave.project.entity.User;
import com.neosave.project.exception.InvalidDetailsException;
import com.neosave.project.exception.RecordsNotFoundException;
import com.neosave.project.service.UserService;

@RestController
@RequestMapping("/neosave")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user/create")
	public ResponseEntity<?> createUser(@RequestParam("name") String name,
										@RequestParam("email") String email,
										@RequestParam("addressPinCode") String addressPinCode) throws InvalidDetailsException,RecordsNotFoundException{
		
		User user  =userService.saveUser(name, email, addressPinCode);
		Map<String, Integer> map = new HashMap<>();
		
		map.put("id", user.getId());

		return new ResponseEntity<>(map,HttpStatus.ACCEPTED); 
		
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Integer id) throws InvalidDetailsException,RecordsNotFoundException{
		
		User user  =userService.getUser(id);
		Map<String, String> map = new HashMap<>();
		map.put("name", user.getName());
		map.put("email", user.getEmailId());
		map.put("addressPinCode", user.getAddressPinCode());
		map.put("stateName", user.getStateName());
		return new ResponseEntity<>(map,HttpStatus.ACCEPTED); 
		
	}
	

}
