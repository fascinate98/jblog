package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	

	public Boolean insert(BlogVo vo) {
		
		int count = sqlSession.insert("blog.insert", vo);
		return count == 1;
	}
	
	public BlogVo select(String user_id) {
		
		return sqlSession.selectOne("blog.select", user_id);
	}

	public boolean update(BlogVo vo) {
		
		int count = sqlSession.update("blog.update",vo);
		return count == 1;
	}


}
