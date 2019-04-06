package com.web.demo.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.web.demo.mapper.CartItemMapper;
import com.web.demo.model.CartItem;
import com.web.demo.utils.ConnectDB;
public class Cart {
	
	private HashMap<Integer,CartItem> _cart;
	private Double _total;
	
	public Cart()
	{
		_cart = new HashMap<Integer,CartItem>();
		_total= 0.0;
	}
	public void setCart(CartItem item)
	{
		if(_cart !=null)
		{
			if(_cart.containsKey(item.getProductId()))
			{
				
				CartItem oldItem = _cart.get(item.getProductId());
				_total = _total- (oldItem.getAmount()*oldItem.getQuantity());
				
				_cart.remove(item.getProductId());
				
			}
		}
		_cart.put(item.getProductId(), item);
		setTotal(item.getProductId());
	}
	public void putItemIntoCart(CartItem item)
	{
		_cart.put(item.getProductId(), item);
	}
	public void setCart(HashMap<Integer,CartItem> cart)
	{
		this._cart = cart;
	}
	public HashMap<Integer,CartItem> getCart()
	{
		return this._cart;
	}
	
	private void setTotal(Integer id)
	{
		if(this._cart!=null)
		{
			CartItem item =_cart.get(id);
			this._total += item.getAmount().doubleValue() * item.getQuantity();
		}
	}
	public BigDecimal getTotal()
	{
		_total=0.0;
		List<CartItem> listItem = new ArrayList<CartItem>(_cart.values());
		for(CartItem item : listItem)
		{
			this._total += item.getAmount().doubleValue() * item.getQuantity();
		}
		return new BigDecimal(this._total,MathContext.DECIMAL64);
	}
}
