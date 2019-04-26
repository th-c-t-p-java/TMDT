package com.web.demo.controller.connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.demo.mapper.CustomerMapper;
import com.web.demo.model.Cart;
import com.web.demo.model.Customer;
import com.web.demo.model.CustomerExample;
import com.web.demo.service.Impl.CartServiceImpl;
import com.web.demo.utils.ConnectDB;



@Controller
public class customerConnect {
	@RequestMapping("/dangnhap")
	//@ResponseBody
	public void user(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CustomerMapper mapper = ConnectDB.getInstance().getSession().getMapper(CustomerMapper.class);
		CustomerExample example = new CustomerExample();
		String username = request.getParameter("login");
		String password = request.getParameter("password");
		example.createCriteria().andLoginNameEqualTo(username).andPasswordEqualTo(password);
		Customer customer = new Customer();
	
		if (!mapper.selectByExample(example).isEmpty())
		{
			customer = mapper.selectByExample(example).get(0);
			Integer userId = customer.getId();
			String userName = customer.getLastName();
			CartServiceImpl cartService = new CartServiceImpl();
			
			request.getSession().setAttribute("userID", userId);
			request.getSession().setAttribute("userName", userName);
			
			
			Cart sessionCart = (Cart) request.getSession().getAttribute("cart");
			Cart databaseCart = cartService.createCart(request);
			Cart cart = cartService.mergeCart(sessionCart, databaseCart,userId);
			
			request.getSession().setAttribute("cart",cart);
			response.sendRedirect("/");
			
		}		
		else
		{
			response.sendRedirect("/sign-in");
		}
		
		ConnectDB.getInstance().getSession().close();
		
		//return customer;
	}

}
