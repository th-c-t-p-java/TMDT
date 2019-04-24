package com.web.demo.controller;

import java.math.BigDecimal;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.web.demo.common.Constants;
import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;
import com.web.demo.service.Impl.CartServiceImpl;

@Controller
@RequestMapping("/cart")
public class CartController {

	CartServiceImpl cartService = new CartServiceImpl();
	@RequestMapping(value="/addtocart",method = RequestMethod.POST)
	//@ResponseBody
	public void addtocart(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
		
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		BigDecimal price = new BigDecimal(request.getParameter("price").toString());
		
		BigDecimal amount = new BigDecimal((price.longValue()*quantity));
		String image = request.getParameter("image");
		Integer customerId = (Integer)request.getSession().getAttribute("userID");
		String productName=request.getParameter("productName");
		Date insertDate = new Date(System.currentTimeMillis());
		CartItem item = new CartItem(customerId,productId,quantity,amount,customerId,insertDate);
		item.setImage(image);
		item.setProductName(productName);
		Cart cart = cartService.loadCart(request);	
		Integer rowsAffected = 0;
		if(cart.getCart().containsKey(item.getProductId())==false||cart.getCart().isEmpty()==true)
		{
			rowsAffected = cartService.insert(cart, item);
		}
		else
		{
			rowsAffected = cartService.update(cart, item);
		}
		
		request.getSession().setAttribute("cart", cart);
		
		if(rowsAffected <= 0)
		{
			request.getSession().setAttribute("message", Constants.UPDATE_FAIL);
		}
		else
		{
			request.getSession().setAttribute("message", Constants.UPDATE_SUCCESS);
			request.getSession().setAttribute("cartItem", item);
			
		}
		//return rowsAffected;
		response.sendRedirect("/detail?id="+productId);
		//return item.getAmount().toString();
		//return request.getSession().getAttribute("userID").toString();
	}
	@RequestMapping(value="/delete")
	public void delete(@RequestParam("id") Integer productId,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Cart cart = cartService.loadCart(request);
		CartItem item = cartService.findItem(cart, productId);
		Integer rowsAffected =0;
		rowsAffected = cartService.delete(cart, item);
		if(rowsAffected <=0)
		{
			request.getSession().setAttribute("message", Constants.UPDATE_FAIL);
		}
		else
		{
			request.getSession().setAttribute("message", Constants.UPDATE_SUCCESS);
		}
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect("/cart");
	}

}

