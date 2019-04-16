package com.web.demo.service;

import javax.servlet.http.HttpServletRequest;

import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;

public interface CartService {

	public Cart getCart(HttpServletRequest request) throws Exception;
	public Cart createCart(HttpServletRequest request) throws Exception;
	public Integer insert(CartItem item,HttpServletRequest request) throws Exception;
	public Integer update(CartItem item,HttpServletRequest request) throws Exception;
	public Integer delete(CartItem item,HttpServletRequest request) throws Exception;
	public void remove(HttpServletRequest request) throws Exception;
}
