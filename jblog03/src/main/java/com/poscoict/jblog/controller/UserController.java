package com.poscoict.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo, Model model) {
		model.addAttribute("userVo", userVo);

		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model, BlogVo blogVo) {
		System.out.println("dfdfdf"+ userVo.getId());
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
			
		}
		
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}	
	
	
	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}
	
//
//	@Auth
//	@RequestMapping(value="/update", method=RequestMethod.GET)
//	public String update(@AuthUser UserVo authUser, Model model) {
//		/* access controller */	
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		
//		Long userNo = authUser.getNo();
//		UserVo userVo = userService.getUser(userNo);
//		model.addAttribute("userVo", userVo);
//		
//		return "user/update";
//	}
//	
//	
//	
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public String update(HttpSession session, UserVo userVo) {
//		/* access controller */
//		UserVo authUser = (UserVo)session.getAttribute("authUser");
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		
////		Long userNo = authUser.getNo();
////		UserVo userVo = userService.getUser(userNo);
////		model.addAttribute("userVo", userVo);
//		
//		userVo.setNo(authUser.getNo());
//		userService.updateUser(userVo);
//		
//		return "redirect:/user/login";
//	}
	
	@ExceptionHandler(Exception.class)
	public String UserControllerExceptionHandler() {
		return "error/exception";
	}
}
