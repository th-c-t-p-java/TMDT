package com.web.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.demo.model.Cart;
import com.web.demo.model.Customer;
import com.web.demo.service.Impl.CartServiceImpl;
import com.web.demo.service.Impl.UserServiceImpl;

/*modelAttribute*/
@Controller
@RequestMapping("/check-out")
public class CheckOutController {
	
	@RequestMapping(value="/user-login",method = RequestMethod.POST)
	//@ResponseBody
	void login(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		UserServiceImpl userService = new UserServiceImpl();
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		Customer customer = userService.login(loginName, password);
		if(customer == null )
		{
			request.getSession().setAttribute("message", "Đăng nhập thất bại !!! Vui lòng kiểm tra lại tên tài khoản và mật khẩu.");
			response.sendRedirect("/check-out");
		}
		else
		{
			
			Integer userId = customer.getId();
			String userName = customer.getLastName();
			CartServiceImpl cartService = new CartServiceImpl();
			
			request.getSession().setAttribute("userID", userId);
			request.getSession().setAttribute("userName", userName);
			
			
			Cart sessionCart = (Cart) request.getSession().getAttribute("cart");
			Cart databaseCart = cartService.createCart(request);
			Cart cart = cartService.mergeCart(sessionCart, databaseCart,userId);
			
			request.getSession().setAttribute("cart",cart);
			response.sendRedirect("/check-out/step-2");
			
		}
		
	}
}
