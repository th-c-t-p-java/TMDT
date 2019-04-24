package com.web.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.mapper.CustomerMapper;
import com.web.demo.model.Customer;
import com.web.demo.model.CustomerExample;
import com.web.demo.utils.ConnectDB;

/*modelAttribute*/
@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/user-login",method = RequestMethod.POST)
	//@ResponseBody
	void login(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		SqlSession session = ConnectDB.getInstance().getSession();
		CustomerMapper mapper = session.getMapper(CustomerMapper.class);
		//Customer customer = new Customer();
		CustomerExample example = new CustomerExample();
		String loginName = request.getParameter("loginName").toString();
		String password = request.getParameter("password").toString();
		example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
		List<Customer> lstCustomer = new ArrayList<Customer>();
		lstCustomer = mapper.selectByExample(example);
		if(lstCustomer.isEmpty())
		{
			request.getSession().setAttribute("message", "Đăng nhập thất bại !!! Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.");
			response.sendRedirect("/check-out");
		}
		else
		{
			if(lstCustomer.size() == 1)
			{
				Integer userId = lstCustomer.get(0).getId();
				request.getSession().setAttribute("userID", userId);
				//response.sendRedirect("/");
				response.sendRedirect("/check-out-step-2");
			}
		}
		
	}
}
