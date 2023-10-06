package com.dailycodebuffer.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodebuffer.springbootdemo.entity.User;
import com.dailycodebuffer.springbootdemo.service.EmployeeService;

@RestController
@ResponseBody
public class HelloWorldController {
	
	EmployeeService service;
	
	@Autowired
	public HelloWorldController(EmployeeService service) {
		this.service = service;
	}	
	
	@RequestMapping("/")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") String id) {
		return service.getUser(id);
	}
}
