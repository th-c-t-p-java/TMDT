package com.web.demo.model;

import java.util.HashMap;
import com.web.demo.model.CartItem;
public class Cart {
	
	private HashMap<Integer,CartItem> _cart;
	
	public Cart()
	{
		_cart = new HashMap<Integer,CartItem>();
	}
	
	public void setCart(HashMap<Integer, CartItem> cart)
	{
		this._cart =cart;
	}
	public HashMap<Integer, CartItem>getCart()
	{
		return this._cart;
	}
}
