package com.web.demo.service;

import com.web.demo.model.Customer;

public interface UserService {
	Customer login(String loginName,String password) throws Exception;
	Customer getBaseCustomerInfo(Integer id) throws Exception;
	//Customer getAllCustomerInfo(String loginName,String password) throws Exception;
	
}
