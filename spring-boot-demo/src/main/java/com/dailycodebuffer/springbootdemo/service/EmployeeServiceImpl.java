package com.dailycodebuffer.springbootdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.springbootdemo.CustomExceptions.EmployeeNotFoundException;
import com.dailycodebuffer.springbootdemo.entity.User;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	List<User> users;
	public EmployeeServiceImpl() {
		users = new ArrayList<>();
		users.add(new User("1","Niwanshu","niwanshu16@gmail.com"));
		users.add(new User("2","Manish","manish12@gmail.com"));
		users.add(new User("3","Jawan","Jawaan@gmail.com"));
	}
	
	public User getUser(String id) {
		return users
				.stream()
				.filter((u) -> u.getId().equalsIgnoreCase(id))
				.findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found with ID " + id));
	
	}
}
