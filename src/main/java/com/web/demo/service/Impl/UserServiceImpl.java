package com.web.demo.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.demo.mapper.CustomerMapper;
import com.web.demo.model.Customer;
import com.web.demo.model.CustomerExample;
import com.web.demo.service.UserService;
import com.web.demo.utils.ConnectDB;

public class UserServiceImpl implements UserService{

	@Override
	public Customer login(String loginName,String password) throws Exception {
		
		SqlSession session = ConnectDB.getInstance().getSession();
		Customer customer = new Customer();
		try {
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			//Customer customer = new Customer();
			CustomerExample example = new CustomerExample();
			example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
			List<Customer> lstCustomer = new ArrayList<Customer>();
			lstCustomer = mapper.selectByExample(example);
			if(lstCustomer.size() == 1)
			{
				customer = lstCustomer.get(0);
			}
			else
			{
				customer =  null;
			}
			
				
		}
		finally {
			
			session.close();
		}
		return customer;
		
	}
	@Override
	public Customer getBaseCustomerInfo(Integer userId) throws Exception
	{
		
		CustomerMapper mapper = ConnectDB.getInstance().getSession().getMapper(CustomerMapper.class);
		
		Customer customer = mapper.selectByPrimaryKey(userId);
		
		return customer;
	}

	

}
