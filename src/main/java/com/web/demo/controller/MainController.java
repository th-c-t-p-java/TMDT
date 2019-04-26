package com.web.demo.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.common.Constants;
import com.web.demo.mapper.CustomerMapper;
import com.web.demo.mapper.ProductMapper;
import com.web.demo.model.Cart;
import com.web.demo.model.CartItem;
import com.web.demo.model.Customer;
import com.web.demo.model.Product;
import com.web.demo.service.Impl.CartServiceImpl;
import com.web.demo.service.Impl.UserServiceImpl;
import com.web.demo.utils.ConnectDB;

@Controller

public class MainController {
	
		UserServiceImpl userService = new UserServiceImpl();
		CartServiceImpl cartService = new CartServiceImpl();
		String userName = "Đăng nhập";
		@ModelAttribute(name="currentUser")
		public void currentUser(HttpServletRequest request,Model model)
		{
			if(request.getSession().getAttribute("userID") == null)
			{
				model.addAttribute("currentUser","Đăng nhập");
			}
			else
			{
				
				model.addAttribute("currentUser", request.getSession().getAttribute("userName").toString());
			}
		}
		
		@RequestMapping("/")
		//@ResponseBody
		public String index(Model model,HttpServletRequest request) throws Exception
		{			
			/*if(request.getSession().getAttribute("userID")!=null)
			{	
				Cart cart = cartService.loadCart(request);
				cart = cartService.createCart(request);
				request.getSession().setAttribute("cart", cart);
			}			*/
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
				model.addAttribute("message",request.getSession().getAttribute("message"));
				if(request.getSession().getAttribute("message") == Constants.UPDATE_SUCCESS)
				{					
					CartItem item = (CartItem)request.getSession().getAttribute("cartItem");
					model.addAttribute("item",item);
				}	
				
			}
			finally {
				ConnectDB.getInstance().getSession().close();
				request.getSession().removeAttribute("message");
				request.getSession().removeAttribute("cartItem");
			
			}			
			return "/components/detail";			
		}
		@RequestMapping("/cart")
		//@ResponseBody
		public String cart(Model model,HttpServletRequest request) throws Exception
		{
			
			Cart cart = new Cart();
			
			cart = cartService.loadCart(request);			
			List<CartItem> cartItemLst = new ArrayList<CartItem>(cart.getCart().values());
			model.addAttribute("cartItemLst",cartItemLst);
			model.addAttribute("totalItem",cartItemLst.size());
			model.addAttribute("totalPrice",cartService.Total(cart));		
			if(request.getSession().getAttribute("message") == Constants.UPDATE_SUCCESS)
			{
				CartItem item = (CartItem)request.getSession().getAttribute("cartItem");
				model.addAttribute("item", item);
				request.getSession().removeAttribute("cartItem");
				request.getSession().removeAttribute("message");
			}
			
			return "/components/cart";
			
		}
		@RequestMapping("/sign-in")
		public String signin()
		{
			HttpSession sessions = request.getSession();
			model.addAttribute("message", sessions.getAttribute("message"));
			return "/components/sign-in";
		}
		@RequestMapping("/sign-up")
		public String signup()
		{
			HttpSession sessions = request.getSession();
			model.addAttribute("message", sessions.getAttribute("message"));
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
		
		@RequestMapping("/check-out")
		public String checkOut( Model model,HttpServletRequest request)
		{
			//request.getSession().setAttribute("checkoutOrder","");
			if(request.getSession().getAttribute("userID") == null)
			{
				if(request.getSession().getAttribute("message") !=null)
				{
					model.addAttribute("message",request.getSession().getAttribute("message"));
					
				}
				return "/components/check-out";
			}
			
			return "/components/check-out-step-2";
		}
		
		@RequestMapping("/check-out/step-2")
		public String checkOutStepTwo( Model model,HttpServletRequest request) throws Exception
		{	
			Integer userId= Integer.parseInt(request.getSession().getAttribute("userID").toString());
			Customer customer = userService.getBaseCustomerInfo(userId);
			
			model.addAttribute("customerInfo",customer);
			return "/components/check-out-step-2";
		}
		@RequestMapping("/check-out/step-3")
		public String checkOutStepThree( Model model,HttpServletRequest request) throws Exception
		{
			Integer userId= Integer.parseInt(request.getSession().getAttribute("userID").toString());
			Customer customer = userService.getBaseCustomerInfo(userId);

			Cart cart = cartService.loadCart(request);
			
			List<CartItem> orderItem = new ArrayList<CartItem>(cart.getCart().values());
			cartService.getTotal();
			model.addAttribute("customerInfo",customer);
			model.addAttribute("orderItems", orderItem);
			model.addAttribute("total", cartService.Total(cart));
			return "/components/check-out-step-3";
		}
		
		

}
