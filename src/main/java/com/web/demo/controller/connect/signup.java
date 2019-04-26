package com.web.demo.controller.connect;


import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.mapper.CustomerMapper;
import com.web.demo.model.Customer;
import com.web.demo.utils.ConnectDB;

@Controller
@RequestMapping("/signup")
public class signup {
	@RequestMapping(value="/addtouser",method = RequestMethod.POST)
	//@ResponseBody
	public void addtouser(HttpServletRequest request,HttpServletResponse response ,Model model) throws Exception
	{	
		SqlSession session = ConnectDB.getInstance().getSession();
		try {
			String loginname = request.getParameter("tendangnhap");
			
			String password = request.getParameter("mk");
			
			String email=request.getParameter("email");
			Integer phonenumber=Integer.parseInt(request.getParameter("sdt"));
			String address=request.getParameter("diachi");
			String firstname=request.getParameter("ho");
			String lastname=request.getParameter("ten");
			Integer idcard = Integer.parseInt(request.getParameter("chungminh"));
			String sBirthDate = request.getParameter("ngaysinh");
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			Date birthDate = format.parse(sBirthDate);		
			Date insertDate = new Date(System.currentTimeMillis());
			
			
			CustomerMapper mapper = session.getMapper(CustomerMapper.class);
			Customer customer = new Customer();
			customer.setLoginName(loginname);
			customer.setPassword(password);
			customer.setEmail(email);
			customer.setPhoneNumber(phonenumber);
			customer.setAddress(address);
			customer.setFirstName(firstname);
			customer.setLastName(lastname);
			customer.setIdCard(idcard);
			customer.setBirthDay(birthDate);
			customer.setInsertDate(insertDate);
			customer.setIsEnable(true);
			customer.setType(1);
			customer.setModifyDate(null);
			Integer rowsAffected = 0;
			rowsAffected = mapper.insert(customer);
			session.commit();
			if(rowsAffected == 1)
			{
				response.sendRedirect("/sign-in");
				request.getSession().setAttribute("message", "Đăng nhập thành công");
			}
			else
			{
				response.sendRedirect("/sign-up");
				request.getSession().setAttribute("message", "Đăng nhập thất bại");
			}	
		
		}finally{
			session.close();
			
			
			
		}
	}

}
