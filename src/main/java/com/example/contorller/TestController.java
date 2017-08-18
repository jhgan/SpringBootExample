package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.impl.TestServiceImpl;

@Controller
public class TestController {
	
	@Autowired
	private TestServiceImpl testServiceImpl;
	
	@GetMapping("/test")
	public String test(Model model) {
		System.out.println("call test function!");
		
		model.addAttribute("currentTime", System.currentTimeMillis());
		
		model.addAllAttributes(testServiceImpl.mulitiDataSourceTest());
		
		return "temp/test";
	}
}
