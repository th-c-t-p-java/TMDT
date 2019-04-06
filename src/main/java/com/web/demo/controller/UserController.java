package com.web.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.common.Constants;
import com.web.demo.model.Customer;
import com.web.demo.service.Impl.UserServiceImpl;

@Controller
public class UserController {
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	@ResponseBody
	String login(@ModelAttribute("user") Customer userInf, Model model,HttpSession session) throws Exception
	{
		UserServiceImpl userService = new UserServiceImpl();
		Map<String,Object> map = new HashMap<>();
		map.putAll(userService.login(userInf.getLoginName(), userInf.getPassword()));
		if (map.get("status")==Constants.TRUE)
		{
			session.setAttribute("status",map.get("status"));
			session.setAttribute("customer", map.get("customer"));
		}
		//modelMap.put("loginName",session.)
		Customer customer = new Customer();
		customer = (Customer)session.getAttribute("customer");
		model.addAttribute("loginname",customer.getLoginName());
		return "login";
	}
}
