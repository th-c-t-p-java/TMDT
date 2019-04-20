package com.web.demo.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.web.demo.mapper.CartItemMapper;
import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;
import com.web.demo.service.CartService;
import com.web.demo.utils.ConnectDB;

public class CartServiceImpl implements CartService{

	private long total;
	public CartServiceImpl ()
	{
		this.total = 0;
	}
	public long getTotal()
	{
		
		return this.total;
	}
	public void setTotal(long total)
	{
		this.total = total;
	}
	public long Total(Cart cart)
	{
		total = 0;
		HashMap<Integer,CartItem> hshmpCart = cart.getCart();
		List<CartItem> tmp = new ArrayList<CartItem>(hshmpCart.values());
		for(CartItem item : tmp)
		{
			total = total + item.getAmount().longValue();
		}
		return this.total;
	}
	
	
	@Override
	public Cart loadCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null)
			cart = new Cart();
		return cart;
	}

	@Override
	public Cart createCart(HttpServletRequest request) throws Exception {
		Cart cart = loadCart(request);
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			if(request.getSession().getAttribute("userID")!= null)
			{
				CartItemMapper mapper = session.getMapper(CartItemMapper.class);
				List<CartItem> cartItems = mapper.selectByCustomerId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
				if(!cartItems.isEmpty())
				{
					Map<Integer, CartItem>tmp = cartItems.stream().collect(Collectors.toMap(CartItem::getProductId, CartItem::getItem));
					cart.setCart((HashMap<Integer, CartItem>) tmp);
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
	public Integer insert(Cart cart, CartItem item, HttpServletRequest request) throws Exception {
		Integer rowsAffected = 0;
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			if(request.getSession().getAttribute("userID")!=null)
			{
				
				CartItemMapper mapper = session.getMapper(CartItemMapper.class);
				rowsAffected = mapper.insert(item);
				session.commit(true);
			}
			else
			{
				rowsAffected = 1;
			}
		}
		finally {
			session.close();
		}
		this.total+= item.getAmount().longValue();
		cart.getCart().put(item.getProductId(),item);
		request.getSession().setAttribute("cart", cart);
		return rowsAffected;
	}

	@Override
	public Integer update(Cart cart,CartItem item, HttpServletRequest request) throws Exception {
		Integer rowsAffected = 0 ;
		SqlSession session = ConnectDB.getInstance().getSession();
		CartItemMapper mapper = session.getMapper(CartItemMapper.class);
		try {
			if(request.getSession().getAttribute("userID")!= null)
			{				
				/*Integer id = item.getId();
				long amount = item.getAmount().longValue();
				Integer quantity = item.getQuantity();*/
				Integer id = cart.getCart().get(item.getProductId()).getId();
				rowsAffected = mapper.updateQuantityById(id,item.getAmount().longValue(),item.getQuantity());
				session.commit(true);
			}
			else
			{
				rowsAffected = 1;
				System.out.print("NO CUSTOMER");
			}
				
		}
		finally {
			session.close();
		}
		CartItem oldItem = cart.getCart().get(item.getProductId());
		long oldAmount = oldItem.getAmount().longValue();
		cart.getCart().remove(oldItem.getProductId());

		this.total -= oldAmount;
		
		cart.getCart().put(item.getProductId(), item);
		this.total += item.getAmount().longValue();
		//request.getSession().removeAttribute("cart");
		request.getSession().setAttribute("cart", cart);
		return rowsAffected;
	}

	@Override
	public Integer delete(Cart cart,CartItem item, HttpServletRequest request) throws Exception {
		Integer rowsAffected =0;
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			if(item.getCustomerId()!=null)
			{
				CartItemMapper mapper = session.getMapper(CartItemMapper.class);
				
				rowsAffected = mapper.updateIsEnableById(item.getId());
				session.commit(true);
			}
			else
			{
				rowsAffected = 1;
				
			}
				
			
		}
		finally {
			session.close();
		}
		
		cart.getCart().remove(item.getProductId());
		this.total -= item.getAmount().longValue();
		request.getSession().setAttribute("cart", cart);
		return rowsAffected;
	}

	@Override
	public void remove(HttpServletRequest request) {
		request.getSession().removeAttribute("cart");
	}
	@Override
	public CartItem findItem(Cart cart, Integer productId) throws Exception {
		CartItem item = cart.getCart().get(productId);		
		return item;
	}

}
