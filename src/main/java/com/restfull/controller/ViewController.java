package com.restfull.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restfull.DTO.RegistrationDataObject;
import com.restfull.entity.RegistrationEntity;


@Controller
public class ViewController {

	@RequestMapping("/home")
	public String home()
	{
	return "home";
	}
	
	@RequestMapping("/register")
	public String register(Model model)
	{
		model.addAttribute("user", new RegistrationDataObject());
	return "registered";
	}
	
	
	
}
