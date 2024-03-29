package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {

	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(int userId, BlogRequest blogRequest);

	ResponseEntity<Boolean> checkBlogTitle(String title);

	ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId);

	ResponseEntity<ResponseStructure<BlogResponse>> updateBlogById(int blogId, BlogRequest blogRequest);

	

	

}
