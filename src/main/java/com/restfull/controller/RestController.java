package com.restfull.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.restfull.DTO.RegistrationDataObject;
import com.restfull.entity.RegistrationEntity;
import com.restfull.services.RestServices;


@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	RestServices services;
	
	@PostMapping("/signup")
	public String registered(RegistrationDataObject registered,HttpServletResponse res)
	{
	 
	 try {
		 Boolean b = services.registeredDetails(registered);
		res.sendRedirect("/login");	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 System.out.println("yes"); 
		return "sucess";
	}
	
	
}
