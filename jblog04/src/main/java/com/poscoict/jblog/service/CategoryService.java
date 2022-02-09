package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	public List<CategoryVo> getCategoryList(String blog_id){
		return categoryRepository.getCategoryList(blog_id);
	}
	
	public Long getMinCategoryNo(String blog_id){

		return categoryRepository.getMinNo(blog_id);
	}
	
	public boolean insert(CategoryVo vo) {
		
		return categoryRepository.insert(vo);
	}
	
	public boolean delete(Long no) {
		
		return categoryRepository.delete(no);
	}
}
