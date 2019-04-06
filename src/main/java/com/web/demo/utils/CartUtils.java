package com.web.demo.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.web.demo.mapper.CartItemMapper;
import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;

public class CartUtils {

	public static Cart getCartInSession(HttpServletRequest request)
	{
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null)
		{
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);			
		}
		
		return cart;
	}
	public static Cart getCartInDB(HttpServletRequest request,Integer userID) throws Exception
	{
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null)
		{
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);			
		}
		
		CartItemMapper mapper= ConnectDB.getInstance().getSession().getMapper(CartItemMapper.class);
		List<CartItem> cartItems = mapper.selectByCustomerIdTest(userID);
		for(CartItem item:cartItems)
		{
			cart.getCart().put(item.getProductId(), item);
		}
		return cart;
	}
	public static void removeCart(HttpServletRequest request)
	{
		request.getSession().removeAttribute("cart");
	}

}
