package com.web.demo.controller;


import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.mapper.CartItemMapper;
import com.web.demo.mapper.CustomerMapper;
import com.web.demo.mapper.ProductMapper;
import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;
import com.web.demo.model.Customer;
import com.web.demo.model.Product;

import com.web.demo.utils.CartUtils;
import com.web.demo.utils.ConnectDB;

@Controller

public class MainController {
	
	
		@RequestMapping("/")
		//@ResponseBody
		public String index(Model model,HttpServletRequest request) throws Exception
		{
			
			request.getSession().setAttribute("userID", 11);
				
			
			ProductMapper mapper = ConnectDB.getInstance().getSession().getMapper(ProductMapper.class);
			List<Product> hotDealProduct = new ArrayList<Product>();
			List<Product> productForYou = new ArrayList<Product>();
			//Product pro= new Product();
			try {
				
				
				hotDealProduct = mapper.selectByCatalogId(6,6);
				productForYou = mapper.selectByCatalogId(7,6);

				model.addAttribute("hotDealProduct",hotDealProduct);
				model.addAttribute("productForYou",productForYou);
			}	
			finally
			{
				ConnectDB.getInstance().getSession().close();
			}
			return "index";
		}
		
		
		@RequestMapping(value ="/detail")
		//@ResponseBody
		public String detail(@RequestParam("id") Integer id,Model model,HttpServletResponse response,HttpServletRequest request) throws Exception
		{
			ProductMapper mapper = ConnectDB.getInstance().getSession().getMapper(ProductMapper.class);
			List<Product> relateProduct = new ArrayList<Product>();
			Product product = new Product();
			try {
				product = mapper.selectByPrimaryKey(id);
				String[] imageList= product.getImageList();
				Object[] details = (Object[])product.getDescription();
				relateProduct = mapper.selectByCatalogId(21,6);
				model.addAttribute("product",product);
				model.addAttribute("details",details);
				model.addAttribute("imageList",imageList);
				model.addAttribute("relateProduct",relateProduct);
			}
			finally {
				ConnectDB.getInstance().getSession().close();
			}			
			return "/components/detail";			
		}
		@RequestMapping("/cart")
		//@ResponseBody
		public String cart(Model model,HttpServletRequest request) throws Exception
		{
			
			Cart cart = new Cart();
			if(request.getSession().getAttribute("userID")==null)
			{
				cart = CartUtils.getCartInSession(request);
			}			 
			else
			{
				Integer userID = Integer.parseInt(request.getSession().getAttribute("userID").toString());
				cart = CartUtils.getCartInDB(request,userID);
			}
								
			List<CartItem> cartItemLst = new ArrayList<CartItem>(cart.getCart().values());
			
			model.addAttribute("cartItemLst",cartItemLst);
			model.addAttribute("totalItem",cartItemLst.size());
			model.addAttribute("totalPrice", cart.getTotal());			
			
			return "/components/cart";
		}
		@RequestMapping("/sign-in")
		public String signin()
		{
			return "/components/sign-in";
		}
		@RequestMapping("/sign-up")
		public String signup()
		{
			return "/components/sign-up";
		}
		@RequestMapping("/editproduct")
		public String editproduct()
		{
			return "/components/editproduct";
		}
		@RequestMapping("/detailmanager")
		public String detailmanager()
		{
			return "/components/detailmanager";
		}
		@RequestMapping("/manager")
		public String manager()
		{
			return "/components/manager";
		}
		
		
		

}
