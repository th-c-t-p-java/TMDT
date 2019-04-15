package com.web.demo.model;

import java.math.BigDecimal;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.web.demo.mapper.CartItemMapper;
import com.web.demo.model.CartItem;
import com.web.demo.utils.ConnectDB;
public class Cart {
	
	private HashMap<Integer,CartItem> _cart;
	private long _total;
	
	public Cart()
	{
		_cart = new HashMap<Integer,CartItem>();
		_total= 0;
	}
	public CartItem getCartItem(Integer itemId)
	{
		return _cart.get(itemId);
	}
	public Integer addItemToCart(HttpServletRequest request,CartItem item) throws Exception
	{
		Integer rowsAffected = 0;
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart!=null && cart.getCart().containsKey(item.getProductId()))
		{
			rowsAffected = editItem(item);
			
		}
		else
		{
			rowsAffected = insertItem(item);
		}
		return rowsAffected;
	}

	public void setCart(HashMap<Integer,CartItem> cart)
	{
		this._cart = cart;
	}
	public HashMap<Integer,CartItem> getCart()
	{
		return this._cart;
	}
	
	public void setTotal(Integer id)
	{
		if(this._cart!=null)
		{
			
			CartItem item =_cart.get(id);
			
			this._total += item.getAmount().longValue();
		}
	}
	
	public void Total(BigDecimal amount)
	{
		if(_cart!=null)
		{
			this._total+=amount.longValue();
		}
		
	}
	
	public BigDecimal getTotal()
	{
		return new BigDecimal(this._total);
	}
	private int editItem(CartItem item) throws Exception
	{
		
		int rowsAffected=0;
		SqlSession session =ConnectDB.getInstance().getSession();
		
		try {
				if(item.getCustomerId()!=null)
				{
					CartItemMapper mapper=session.getMapper(CartItemMapper.class);
					rowsAffected = mapper.updateQuantityById(item.getId(),item.getAmount().longValue(),item.getQuantity());
					session.commit(true);
				}				
			
				else
				{				
					
					rowsAffected = 1;
				}
			
			CartItem oldItem = _cart.get(item.getProductId());
			_total = (_total - oldItem.getAmount().longValue());				
			_cart.remove(oldItem.getProductId());
			_cart.put(item.getProductId(), item);
			Total(item.getAmount());
		}
		finally {
			session.close();
		}
		
		return rowsAffected;
	}
	private int insertItem(CartItem item) throws Exception
	{
		
		int rowsAffected=0;
		SqlSession session =ConnectDB.getInstance().getSession();
		try {
			if(item.getCustomerId()!=null)
			{
				
				CartItemMapper mapper=session.getMapper(CartItemMapper.class);
				rowsAffected = mapper.insert(item);
				session.commit(true);
			}
			else
			{
				rowsAffected =1;
			}
			_cart.put(item.getProductId(),item);
			Total(item.getAmount());
			
		}
		finally {
			session.close();
		}
		
		return rowsAffected;
	}
	public int deleteCartItem(CartItem item) throws Exception
	{
		int rowsAffected =0;
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			if(item.getCustomerId()!=null)
			{
				CartItemMapper mapper = session.getMapper(CartItemMapper.class);
				rowsAffected = mapper.updateIsEnableById(item.getId());
				session.commit();
			}
			_cart.remove(item.getProductId());
			_total = _total- item.getAmount().longValue();
		}
		finally {
			session.close();
		}
		return rowsAffected;
	}
}
