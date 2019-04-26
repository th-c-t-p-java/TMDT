package com.example.demo.controller.connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.connect.connectDB;

import com.example.demo.mapper.CustomerMapper;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerExample;

@Controller
public class customer {
	@RequestMapping("/dangnhap")
	//@ResponseBody
	public void user(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CustomerMapper mapper = connectDB.getInstance().getsSession().getMapper(CustomerMapper.class);
		CustomerExample example = new CustomerExample();
		String username = request.getParameter("login");
		String password = request.getParameter("password");
		example.createCriteria().andLoginNameEqualTo(username).andPasswordEqualTo(password);
		Customer customer = null;
	
	
		if (!mapper.selectByExample(example).isEmpty())
		{
			customer = mapper.selectByExample(example).get(0);
		}		
		
		connectDB.getInstance().getsSession().close();
		
		//return customer;
		if(customer == null)
		{
				
			
		
			 response.sendRedirect("/sign-in");
			 HttpSession session = request.getSession();
				session.setAttribute("message", "đăng nhập thất bại");
		}
		else
		{
			HttpSession sessions = request.getSession();
			sessions.removeAttribute("message");
			 response.sendRedirect("/");
		}
	}

}
