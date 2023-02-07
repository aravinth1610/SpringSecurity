package com.restfull.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restfull.DTO.RegistrationDataObject;
import com.restfull.entity.RegistrationEntity;
import com.restfull.repositary.RestReposarity;

@Service
public class RestServices {

	@Autowired
	RestReposarity registeredRepo;
	
	private static RegistrationEntity registered = new RegistrationEntity();

	public Boolean registeredDetails(RegistrationDataObject regiEntity)
	{
	 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	SimpleDateFormat dateTime=new SimpleDateFormat("dd/mm/yyy HH:mm:ss");
    String registeredTime=dateTime.format(new Date());
    	
		
			
		//boolean isUserName=registeredRepo.existsByUserName(registerView.getUserName());
		boolean isEmail=registeredRepo.existsByGmail(regiEntity.getGmail());
		
	
	if(isEmail)
	{
		return false;
	}
	registered.setUser_id(UUID.randomUUID().toString().substring(0, 6));
	registered.setRegistered_time(registeredTime);
	
	registered.setGmail(regiEntity.getGmail());
	registered.setPassword(encoder.encode(regiEntity.getPassword())); 
	
	
	    boolean result=	registeredRepo.save(registered) != null;

		return result;
		
		
	}
	
}
