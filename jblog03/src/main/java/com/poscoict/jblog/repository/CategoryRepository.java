package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	

	
	public Boolean insert(CategoryVo vo) {
		
		int count = sqlSession.insert("category.insert", vo);
		return count == 1;
	}
}
