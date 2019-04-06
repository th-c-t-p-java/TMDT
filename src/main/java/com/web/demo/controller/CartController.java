package com.web.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseBody;

//import com.web.demo.model.AccountExample;
import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;
//import com.web.demo.model.ObjectHandle;
import com.web.demo.utils.CartUtils;

@Controller
@RequestMapping("/cart")
public class CartController {

	@RequestMapping("/addtocart")
	//@ResponseBody
	public void addtocart(HttpServletRequest request,HttpServletResponse response) throws IOException
	{	
		
		CartItem item = new CartItem();
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer qty = Integer.parseInt(request.getParameter("quantity"));
		BigDecimal amount = new BigDecimal(request.getParameter("price"));
		String image = request.getParameter("image");
		Integer customerId = (Integer)request.getSession().getAttribute("userID");
		String productName=request.getParameter("productName");
		item.setProductId(id);
		item.setQuantity(qty);
		item.setCustomerId(customerId);
		item.setAmount(amount);	
		item.setProductName(productName);
		item.setImage(image);
		Cart cart = CartUtils.getCartInSession(request);
		
		cart.setCart(item);
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect("/detail?id="+id);
	}

}

