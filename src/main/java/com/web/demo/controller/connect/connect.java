
package com.web.demo.controller.connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.demo.mapper.AccountMapper;
import com.web.demo.model.Account;
import com.web.demo.model.AccountExample;
import com.web.demo.utils.ConnectDB;

@Controller
@RequestMapping("/admin")
public class connect {

	@RequestMapping("/dangnhap")
	//@ResponseBody
	public void user(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		AccountMapper mapper = ConnectDB.getInstance().getSession().getMapper(AccountMapper.class);
		AccountExample example = new AccountExample();
		String username = request.getParameter("login");
		String password = request.getParameter("password");
		example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		Account account = null;
		if (!mapper.selectByExample(example).isEmpty())
		{
			account = mapper.selectByExample(example).get(0);
		}		
		
		ConnectDB.getInstance().getSession().close();
		//return account;
		if(account == null)
		{
				
			 response.sendRedirect("/admin");
		}
		else
		{
			 response.sendRedirect("/manager");
		}
	}
}
	 


