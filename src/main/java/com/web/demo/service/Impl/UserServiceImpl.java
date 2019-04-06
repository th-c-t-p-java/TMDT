package com.web.demo.service.Impl;

import java.util.HashMap;
import java.util.Map;

import com.web.demo.mapper.CustomerMapper;
import com.web.demo.model.Customer;
import com.web.demo.model.CustomerExample;
import com.web.demo.service.UserService;
import com.web.demo.utils.ConnectDB;
import com.web.demo.common.*;

public class UserServiceImpl implements UserService{

	@Override
	public Map<String, Object> login(String loginName,String password) throws Exception {
		
		Map<String, Object> output = new HashMap<>();
		output.put("status", Constants.FAIL);
		CustomerMapper mapper = ConnectDB.getInstance().getSession().getMapper(CustomerMapper.class);
		CustomerExample example = new CustomerExample();
		example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
		
		Customer customer = new Customer();
		customer = mapper.selectByExample(example).get(0);
		if(customer == null)
		{
			output.put("message", "Data invalid !!!");
			
		}
		else
		{
			output.put("customer", customer);
			output.put("status",Constants.TRUE);
			
		}
		return output;
	}

	

}
