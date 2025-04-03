package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.LoginModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;


@Controller
@RequestMapping("")
public class LoginController {
	
	@Autowired
	private OrdersBusinessServiceInterface service;
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private SecurityBusinessService security;
	
	@GetMapping("")
	public String display(Model model) {
		// Display login form view
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {

		// Check for Validation error
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}
		
		// Display the Order view
	    model.addAttribute("title", "My Orders");
	    model.addAttribute("orders", service.getOrders());
	    
	    // Call the OrdersBusinessServiceInterface
	    service.test();
	    security.authenticate("username", "password");
		
		return "orders";
	}
	
}
