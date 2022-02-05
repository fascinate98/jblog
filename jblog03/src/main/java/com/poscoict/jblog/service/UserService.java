package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

@Service
public class UserService {
	  @Autowired
	   private UserRepository userRepository;
	  
	  @Autowired
	  private BlogRepository blogRepository;
	  
	  @Autowired
	  private CategoryRepository categoryRepository;

	   public boolean join(UserVo vo) {
		   //유저생성
		   Boolean result = userRepository.insert(vo);
		   
		   //블로그생성
		   BlogVo blogVo = new BlogVo(vo.getName() + "님의 블로그입니다.", "/images/20210275419754.png", vo.getId());

		   blogRepository.insert(blogVo);
		   
		   //카테고리생성
		   CategoryVo categoryVo = new CategoryVo(Long.valueOf(0) , "", "", vo.getId());
		   categoryRepository.insert(categoryVo);
		   
		   return result;
	   }

		public UserVo getUser(String id, String password) {
			   return userRepository.findByIdAndPassword(id, password);
		}

//		public UserVo getUser(Long userNo) {
//			
//			//return userRepository.findByNo(userNo);
//		}

		public void updateUser(UserVo userVo) {
			
			userRepository.update(userVo);
		}
}
