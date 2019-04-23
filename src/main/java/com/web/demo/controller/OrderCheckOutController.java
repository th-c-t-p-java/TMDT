package com.web.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/check-out")
public class OrderCheckOutController {

	@RequestMapping("/step-2")
	public String checkOutStepTwo()
	{
		return "/components/check-out-step-2";
	}
	@RequestMapping("/step-3")
	public String checkOutStepThree()
	{
		return "/components/check-out-step-3";
	}

}
