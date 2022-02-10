package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postrepository;
	
	
	public boolean insert(PostVo postvo) {
		
		return postrepository.insert(postvo);
	}

	

	public List<PostVo> getPostList(Long category_no){

		return postrepository.getPostList(category_no);
	}
	
	public Long getMaxPostNo(Long category_no){

		return postrepository.getMaxNo(category_no);
	}

}
