package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;



@RestController
public class BlogController {
	
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		super();
		this.blogService = blogService;
	}


    @PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@PathVariable int userId,@RequestBody BlogRequest blogRequest)
	{
		return blogService.createBlog(userId,blogRequest);
	}
    
    @GetMapping("/titles/{title}/blogs")
    public ResponseEntity<Boolean> checkBlogTitle(@PathVariable String title)
    {
    	return blogService.checkBlogTitle(title);
    }
    
    @GetMapping("/blogs/{blogId}")
    public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(@PathVariable int blogId)
	{
		return blogService.findBlogById(blogId);
	}
    
    @PutMapping("/blogs/{blogId}")
    public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogById(@PathVariable int blogId,@RequestBody BlogRequest blogRequest)
   	{
   		return blogService.updateBlogById(blogId,blogRequest);
   	}
}
