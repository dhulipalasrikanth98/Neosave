package com.neosave.project.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.neosave.project.dao.UserDao;
import com.neosave.project.entity.User;
import com.neosave.project.exception.InvalidDetailsException;
import com.neosave.project.exception.RecordsNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	
	
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	@Transactional
	@Override
	public User saveUser(String name,String email,String addressPinCode)  {
		log.info("getuser");
		User user = null;
		try {
			validateUser(name,email,addressPinCode);
			String stateName = getState(addressPinCode);
			if(stateName==null) {
				throw new RecordsNotFoundException("no records found for this addressPinCode");
			}
			user = new User();
			user.setName(name);
			user.setAddressPinCode(addressPinCode);
			user.setEmailId(email);
			user.setStateName(stateName);
			userDao.save(user);
			log.info(stateName);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	private void validateUser(String name, String email, String addressPinCode) {
		if(name.isEmpty() || email.isEmpty() || addressPinCode.isEmpty()) {
			throw new InvalidDetailsException("provide the required details");
		}
		
	}

	private String getState(String addressPinCode) throws JsonMappingException, JsonProcessingException  {
		// TODO Auto-generated method stub
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = rest.getForEntity("https://api.postalpincode.in/pincode/"+addressPinCode,Object[].class);
        
		ObjectMapper objectMapper = new ObjectMapper();
		Gson gson = new Gson();
		String jsonString = gson.toJson(responseEntity);
		JsonNode jsonNode = objectMapper.readTree(jsonString);
		if(jsonNode!=null && jsonNode.get("body")!=null && jsonNode.get("body").get(0).get("PostOffice")!=null && jsonNode.get("body").get(0).get("PostOffice").get(0).get("State")!=null) {
		log.info(" response entity {}",jsonNode.get("body").get(0).get("PostOffice").get(0).get("State"));
		return jsonNode.get("body").get(0).get("PostOffice").get(0).get("State").textValue() ;
		}
		return null;
	}

	@Override
	public User getUser(Integer id) {
		if(id==null) {
			throw new InvalidDetailsException("please provide the id");
		}
		// TODO Auto-generated method stub
		Optional<User> user = userDao.findById(id);
		if(user.isEmpty()) {
			throw new RecordsNotFoundException("no details found for this id");
		}
		log.info("{}",user);
		return user.get();
		
	}

	

}
