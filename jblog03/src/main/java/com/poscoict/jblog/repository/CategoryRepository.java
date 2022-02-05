package com.poscoict.jblog.repository;

import java.util.List;

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
	
	
	public List<CategoryVo> getCategoryList(String blog_id) {
		
		return 	sqlSession.selectList("category.getcategorylist", blog_id);
	}
	
	public Long getMinNo(String blog_id){

		return sqlSession.selectOne("category.getminno", blog_id);
	}
	
	
	public boolean delete(Long no) {
		
		int count = sqlSession.delete("category.delete", no);
		return 	count == 1;
	}
}
