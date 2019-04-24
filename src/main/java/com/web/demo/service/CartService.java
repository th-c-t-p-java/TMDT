package com.web.demo.service;

import javax.servlet.http.HttpServletRequest;

import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;

public interface CartService {

	public Cart loadCart(HttpServletRequest request) throws Exception;
	
	public Cart createCart(HttpServletRequest request) throws Exception;
	
	public Integer insert(Cart cart,CartItem item) throws Exception;
	
	public Integer update(Cart cart,CartItem item) throws Exception;
	
	public Integer delete(Cart cart,CartItem item) throws Exception;
	
	public CartItem findItem(Cart cart,Integer productId) throws Exception;
	
	public void remove(HttpServletRequest request) throws Exception;
	
	public Cart mergeCart(Cart sessionCart, Cart databaseCart) throws Exception;
}
