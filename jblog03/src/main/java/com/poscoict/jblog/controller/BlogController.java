package com.poscoict.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.security.AuthUser;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ServletContext context;
	
	
//	//블로그 메인화면
//	@RequestMapping("/{id}")
//	public String main(@PathVariable(value = "id") String id, Model model) {
//
//		Map<String, Object> map = new HashMap<>();
//		
//		Long cno = categoryService.getMinCategoryNo(id);
//		Long pno = postService.getMaxPostNo(cno);
//
//		map = blogService.getBlogInfo(id, cno, pno); 
//		model.addAttribute("blog", map);
//		
//		return "blog/blog-main";
//	}
//	
//	@RequestMapping("/{id}/{category}")
//	public String main(@PathVariable(value = "id") String id, @PathVariable(value = "category") Optional<Long> category, Model model) {
//
//		Map<String, Object> map = new HashMap<>();
//		Long pno = postService.getMaxPostNo(category);
//		
//		map = blogService.getBlogInfo(id, category, pno);
//		model.addAttribute("blog", map);
//		
//		return "blog/blog-main";
//	}
	
	@RequestMapping({"", "/{category}", "/{id}/{category}/{post}"})
	public String main(@PathVariable(value = "id") String id, 
			@PathVariable(value = "category")  Optional<Long>  category,  
			@PathVariable(value = "post")  Optional<Long>  post, Model model) {
		
		Long category_no = 0L;
		Long post_no = 0L;
		
		if(category.isPresent()) {
			category_no = category.get();
		} else {
			category_no = categoryService.getMinCategoryNo(id);
		}
		
		if(post.isPresent()) {
			post_no = post.get();
		} else {
			post_no = postService.getMaxPostNo(category_no);
		}
		
		
		Map<String, Object> map = new HashMap<>();
		
		map = blogService.getBlogInfo(id, category_no, post_no); 
		model.addAttribute("blog", map);
		
		return "blog/blog-main";
	}
	
	
	
	
	
	
	//기본설정
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String basic(@AuthUser UserVo uservo, @PathVariable(value = "id") String id) {
		if(uservo.getId().equals(id)) {
			return "blog/blog-admin-basic";
		}
		return "main/index";
	}
	//기본설정-타이틀,로고 수정
	@Auth
	@RequestMapping(value="/admin/basic",method=RequestMethod.POST)
	public String basic(@AuthUser UserVo uservo, BlogVo blogvo, @RequestParam(value = "logo-file") MultipartFile multipartFile) {
		
		blogvo.setLogo(fileUploadService.restore(multipartFile));
		blogvo.setUser_id(uservo.getId());
		blogService.update(blogvo);
		context.setAttribute("blogvo", blogvo);  
 
		return "redirect:/jblog/"+ uservo.getId();
	}
	
	
	
	
	
	
	
	//카테고리
	@Auth
	@RequestMapping(value="/{id}/admin/category",method=RequestMethod.GET)
	public String category(@AuthUser UserVo uservo, Model model) {
		List<CategoryVo> clist =  categoryService.getCategoryList(uservo.getId());
		model.addAttribute("clist", clist);
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value="/{id}/admin/category",method=RequestMethod.POST)
	public String category(@AuthUser UserVo uservo, CategoryVo categoryvo) {
		categoryvo.setBlog_id(uservo.getId());
		categoryService.insert(categoryvo);
		return "redirect:/jblog/"+ uservo.getId() + "/admin/category";
	}
	//카테고리-삭제
	@Auth
	@RequestMapping(value="/{id}/admin/category/delete/{categoryno}",method=RequestMethod.GET)
	public String category(@PathVariable(value = "categoryno") Long categoryno, @AuthUser UserVo uservo) {
		categoryService.delete(categoryno);
		return "redirect:/jblog/"+ uservo.getId() + "/admin/category";
	}
	
	
	
	
	
	
	//글작성
	@Auth
	@RequestMapping(value="/{id}/admin/write",method=RequestMethod.GET)
	public String write(@AuthUser UserVo uservo, CategoryVo categoryvo, Model model) {
		List<CategoryVo> clist =  categoryService.getCategoryList(uservo.getId());
		model.addAttribute("clist", clist);
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value="/{id}/admin/write",method=RequestMethod.POST)
	public String write(@AuthUser UserVo uservo, PostVo postvo, CategoryVo categoryvo) {
		postService.insert(postvo);
		return "redirect:/jblog/"+ uservo.getId();
	}
}
