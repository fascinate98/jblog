package com.poscoict.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainControlller {
	@RequestMapping({"", "/main"})
	public String index() {
		//return "/WEB-INF/views/main/index.jsp";
		
		return "main/index";
	}
}
