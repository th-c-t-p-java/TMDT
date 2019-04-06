package com.web.demo.service;

import java.util.Map;

import com.web.demo.model.Customer;

public interface UserService {
	Map<String,Object> login(String loginName,String password) throws Exception;
	
}
