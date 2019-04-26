package com.web.demo.controller;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.utils.ConnectDB;
import com.web.demo.mapper.CatalogMapper;
import com.web.demo.mapper.ProductMapper;
import com.web.demo.model.Catalog;
import com.web.demo.model.CatalogExample;
import com.web.demo.model.Product;
import com.web.demo.model.ProductExample;


@Controller
public class AdminController {
	
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") Integer id, HttpServletRequest request,Model model,HttpServletResponse response) throws Exception {
		SqlSession session= ConnectDB.getInstance().getSession();
		ProductMapper mapper= session.getMapper(ProductMapper.class);
		Product product = new Product();
    	ProductExample example = new ProductExample();
    	example.createCriteria().andIdEqualTo(id);
        product=mapper.selectByExample(example).get(0);	
        model.addAttribute("edit",product);
        CatalogMapper mappers= session.getMapper(CatalogMapper.class);
		List<Catalog> catalog = new ArrayList<Catalog>();
		CatalogExample examples = new CatalogExample();
		example.createCriteria().andIsEnableEqualTo(true);
		catalog=mappers.selectByExample(examples);
        model.addAttribute("catalog", catalog);
        model.addAttribute("message", request.getSession().getAttribute("message"));
		
		
		return "/components/edit";		
	}
	@RequestMapping(value = "/update")
	public void update(HttpServletRequest request,Model model, HttpServletResponse response) throws Exception {
		SqlSession session= ConnectDB.getInstance().getSession();
		ProductMapper mapper= session.getMapper(ProductMapper.class);
		try {
		Product product = new Product();
		List<Integer> catalog = new ArrayList<Integer>();
		catalog.add(Integer.parseInt(request.getParameter("catalog").toString()));
		List<String> image = new ArrayList<String>();
		image.add(request.getParameter("image"));
		String productname = request.getParameter("productname");
		String price = request.getParameter("price");
		String id = request.getParameter("id");
		String quantity = request.getParameter("quantity");
		Date insertDate = new Date(System.currentTimeMillis());
		/*model.addAttribute("catalog", catalog);
		model.addAttribute("productname", productname);
		model.addAttribute("price", price);
		model.addAttribute("id", id);
		model.addAttribute("image", image);
		model.addAttribute("quantity", quantity);*/
		Connection connection = session.getConnection();
		Array ctg = connection.createArrayOf("INTEGER", catalog.toArray());
		Array img = connection.createArrayOf("VARCHAR", image.toArray());
		product.setId(new Integer(id));
		product.setImage(img);
		product.setCatalogId(ctg);
		product.setProductName(productname);
		product.setPrice(new BigDecimal(price));
		product.setIsEnable(true);
		product.setModifyDate(insertDate);
		product.setModifyUser(new Integer(1));
		product.setQuantity(new Integer(quantity));
		product.setView(0);
		mapper.updateByPrimaryKey(product);
		session.commit();
		response.sendRedirect("/listproduct");
		HttpSession sessions=request.getSession();
		sessions.removeAttribute("message");
		} catch(Exception e) {
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		HttpSession sessions=request.getSession();
		sessions.setAttribute("message", "Update failed!");
		response.sendRedirect("/edit?id="+id);	
		}
	}
	@RequestMapping("/listproduct")
	public String listproduct(Model model) throws Exception
	{	
		SqlSession session= ConnectDB.getInstance().getSession();
		ProductMapper mapper= session.getMapper(ProductMapper.class);
    	List<Product> product = new ArrayList<Product>();
    	ProductExample example = new ProductExample();
    	example.createCriteria().andIsEnableEqualTo(true);
        product=mapper.selectByExample(example);	
        model.addAttribute("listproduct",product);
	    return "/components/listproduct";
	}

	@RequestMapping("/delete")
	public void delete(@RequestParam("id") Integer id, HttpServletRequest request,Model model,HttpServletResponse response) throws Exception {
		SqlSession session= ConnectDB.getInstance().getSession();
		ProductMapper mapper= session.getMapper(ProductMapper.class);
    	mapper.updateIsEnable(id);
    	session.commit();
	    response.sendRedirect("/listproduct");
	}
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addproduct(HttpServletRequest request,Model model) throws Exception {
		SqlSession session= ConnectDB.getInstance().getSession();
		CatalogMapper mapper= session.getMapper(CatalogMapper.class);
		List<Catalog> catalog = new ArrayList<Catalog>();
		CatalogExample example = new CatalogExample();
		example.createCriteria().andIsEnableEqualTo(true);
		catalog=mapper.selectByExample(example);
		model.addAttribute("catalog", catalog);
		model.addAttribute("message", request.getSession().getAttribute("message"));
		HttpSession sessions=request.getSession();
		sessions.removeAttribute("message");
		return "/components/addproduct";
	}
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public void getproduct(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqlSession session= ConnectDB.getInstance().getSession();
		ProductMapper mapper= session.getMapper(ProductMapper.class);
		try {
		Product product = new Product();
		List<Integer> catalog = new ArrayList<Integer>();
		catalog.add(Integer.parseInt(request.getParameter("catalog").toString()));
		String productname = request.getParameter("productname");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		List<String> image = new ArrayList<String>();
		image.add(request.getParameter("image"));
		Date insertDate = new Date(System.currentTimeMillis());
		/*model.addAttribute("catalog", catalog);
		model.addAttribute("productname", productname);
		model.addAttribute("price", price);
		model.addAttribute("quantity", quantity);
		model.addAttribute("image", image);*/
		Connection connection = session.getConnection();
		Array ctg = connection.createArrayOf("INTEGER", catalog.toArray());
		Array img = connection.createArrayOf("VARCHAR", image.toArray());
		product.setImage(img);
		product.setCatalogId(ctg);
		product.setProductName(productname);
		product.setPrice(new BigDecimal(price));
		product.setIsEnable(true);
		product.setInsertDate(insertDate);
		product.setInsertUser(new Integer(1));
		product.setQuantity(new Integer(quantity));
		product.setView(0);
		mapper.insert(product);
		session.commit();
		response.sendRedirect("/listproduct");
		} catch(Exception e) {
			HttpSession sessions=request.getSession();
			sessions.setAttribute("message", "product is existing!");
			response.sendRedirect("/addproduct");;
		}
	}
	@RequestMapping("/admin")
	public String admin(Model model) throws Exception
	{
		SqlSession session= ConnectDB.getInstance().getSession();
		ProductMapper mapper= session.getMapper(ProductMapper.class);
    	ProductExample example = new ProductExample();
    	example.createCriteria().andIsEnableEqualTo(true);
    	long count = mapper.countByExample(example);
        model.addAttribute("count",count);
		return "/components/admin";
	}
}
