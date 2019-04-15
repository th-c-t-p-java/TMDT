package com.web.demo.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.web.demo.mapper.CartItemMapper;
import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;

public class CartUtils {
	
	public static Cart loadCart(HttpServletRequest request) throws Exception
	{
		Cart cart = getCart(request);
		if (cart == null)
		{
			cart = new Cart();
					
		}
		if(request.getSession().getAttribute("userID")==null)
		{
			return cart;
		}
		else
		{
			CartItemMapper mapper= ConnectDB.getInstance().getSession().getMapper(CartItemMapper.class);
			List<CartItem> cartItems = mapper.selectByCustomerId((Integer)request.getSession().getAttribute("userID"));
			
			// Chậm
			for(CartItem item:cartItems)
			{
				cart.getCart().put(item.getProductId(), item);
				cart.Total(item.getAmount());
			}
		}
		
		request.getSession().setAttribute("cart", cart);	
		return cart;
	}
	public static Cart getCart(HttpServletRequest request)
	{
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null)
		{
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);			
		}
		return cart;
	}
	public static void removeCart(HttpServletRequest request)
	{
		request.getSession().removeAttribute("cart");
	}

}
