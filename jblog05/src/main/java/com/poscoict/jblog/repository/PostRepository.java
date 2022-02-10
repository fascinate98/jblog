package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getPostList(Long category_no){

		return sqlSession.selectList("post.getpostlist", category_no);
	}
	
	public Long getMaxNo(Long category_no){

		return sqlSession.selectOne("post.getmaxno", category_no);
	}

	public boolean insert(PostVo postvo) {
		
		int count = sqlSession.insert("post.insert", postvo);
		return count == 1;
	}
}
