package com.web.demo.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.web.demo.mapper.CartItemMapper;
import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;
import com.web.demo.service.CartService;
import com.web.demo.utils.ConnectDB;

public class CartServiceImpl implements CartService{

	private BigDecimal total;
	
	@Override
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null)
			cart= new Cart();
		return cart;
	}

	@Override
	public Cart createCart(HttpServletRequest request) throws Exception {
		Cart cart = new Cart();
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			if(request.getSession().getAttribute("userID")!= null)
			{
				CartItemMapper mapper = session.getMapper(CartItemMapper.class);
				List<CartItem> cartItems = mapper.selectByCustomerId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
				if(!cartItems.isEmpty())
				{
					// X Tìm cách trả về các thuộc tính của CartItem
					HashMap<Integer,CartItem> c = cartItems.stream().collect(Collectors.toMap(CartItem::getProductId, X));
					for(CartItem item : cartItems)
					{
						cart.getCart().put(item.getProductId(), item);
					}
				}
						
			}	
		}
		finally {
			session.close();
		}
		request.getSession().setAttribute("cart", cart);
		
		return cart;
	}

	@Override
	public Integer insert(CartItem item, HttpServletRequest request) throws Exception {
		Integer rowsAffected = 0;
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			if(request.getSession().getAttribute("userID")!=null)
			{
				
				CartItemMapper mapper = session.getMapper(CartItemMapper.class);
				rowsAffected = mapper.insert(item);
				session.commit();
			}
			else
			{
				rowsAffected = 1;
			}
		}
		finally {
			session.close();
		}
		Cart cart = getCart(request);
		cart.getCart().put(item.getProductId(),item);
		request.getSession().setAttribute("cart",cart);
		return rowsAffected;
	}

	@Override
	public Integer update(CartItem item, HttpServletRequest request) throws Exception {
		Integer rowsAffected = 0 ;
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			if(request.getSession().getAttribute("userID")!= null)
			{
				
				CartItemMapper mapper = session.getMapper(CartItemMapper.class);
				rowsAffected = mapper.updateQuantityById(item.getId(), item.getAmount().longValue(),item.getQuantity());
				session.commit();
			}
			else
				rowsAffected = 1;
		}
		finally {
			session.close();
		}
		Cart cart = getCart(request);
		cart.getCart().remove(item.getProductId());
		cart.getCart().put(item.getProductId(), item);
		request.getSession().setAttribute("cart", cart);
		return rowsAffected;
	}

	@Override
	public Integer delete(CartItem item, HttpServletRequest request) throws Exception {
		Integer rowsAffected =0;
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			if(item.getCustomerId()!=null)
			{
				CartItemMapper mapper = session.getMapper(CartItemMapper.class);
				rowsAffected = mapper.updateIsEnableById(item.getId());
				session.commit();
			}
			else
				rowsAffected = 1;
			
		}
		finally {
			session.close();
		}
		Cart cart = getCart(request);
		cart.getCart().remove(item.getProductId());
		request.getSession().setAttribute("cart", cart);
		return rowsAffected;
	}

	@Override
	public void remove(HttpServletRequest request) {
		request.getSession().removeAttribute("cart");
	}

}
