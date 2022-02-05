package com.poscoict.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class BlogService {

	
	@Autowired
	private BlogRepository blogrepository;
	
	@Autowired
	private CategoryService categoryservice;
	
	@Autowired
	private PostService postservice;
	
	public boolean insert(BlogVo vo) {
		
		return blogrepository.insert(vo);
	}
	
	public BlogVo select(String user_id) {
		
		return blogrepository.select(user_id);
	}
	
	public boolean update(BlogVo vo) {
		
		return blogrepository.update(vo);
	}
	

	public Map<String, Object> getBlogInfo(String user_id, Long category_no, Long post_no) {
		
		Map<String, Object> map = new HashMap<>();
		
		List<CategoryVo> clist= categoryservice.getCategoryList(user_id);
		List<PostVo> plist = postservice.getPostList(category_no);
		
		System.out.println(clist.size());

		
		PostVo postVo = new PostVo();
		for(int i = 0 ; i < plist.size(); i ++) {
			if(plist.get(i).getNo() == post_no) {
				postVo = plist.get(i);
			}
		}

		map.put("postvo", postVo);
		map.put("clist", clist);
		map.put("plist", plist);
		map.put("cno", category_no);
		
		return map;
	}

}
