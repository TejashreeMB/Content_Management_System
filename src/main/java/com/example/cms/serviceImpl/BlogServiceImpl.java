package com.example.cms.serviceImpl;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.exception.BlogAlreadyExistsByTitleException;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.model.Blog;
import com.example.cms.model.User;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

@Service
public class BlogServiceImpl implements BlogService{

	private BlogRepository blogRepo;
	private UserRepository userRepo;
	private ResponseStructure<BlogResponse> structure;

	public BlogServiceImpl(BlogRepository blogRepo, UserRepository userRepo,
			ResponseStructure<BlogResponse> structure) {
		super();
		this.blogRepo = blogRepo;
		this.userRepo = userRepo;
		this.structure = structure;
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(int userId,BlogRequest blogRequest) {

		return userRepo.findById(userId).map(user -> {
			if(blogRepo.existsByTitle(blogRequest.getTitle()))
				throw new BlogAlreadyExistsByTitleException("Failed to create a blog");
			if(blogRequest.getTopics().length <1)
				throw new TopicNotSpecifiedException("Failed to create a blog");
			// Blog blog =blogRepo.save(mapToBlogEntity(blogRequest,new Blog(),user));
			Blog blog =mapToBlogEntity(blogRequest,new Blog());
			blog.setUser(Arrays.asList(user));
			blogRepo.save(blog);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog Created SuccessFully")
					.setData(mapToBlogResponse(blog)));
		}).orElseThrow(() -> new UserNotFoundByIdException("Invalid UserId"));
	}


	// private Blog mapToBlogEntity(BlogRequest blogRequest, Blog blog,User user) {
	private Blog mapToBlogEntity(BlogRequest blogRequest, Blog blog) {
		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setAbout(blogRequest.getAbout());
	//	blog.setUser(Arrays.asList(user));
		return blog;
	}


	private BlogResponse mapToBlogResponse(Blog blog) {
		BlogResponse blogResponse = new BlogResponse();
		blogResponse.setBlogId(blog.getBlogId());
		blogResponse.setTitle(blog.getTitle());
		blogResponse.setTopics(blog.getTopics());
		blogResponse.setAbout(blog.getAbout());
		return blogResponse;
	}


	@Override
	public ResponseEntity<Boolean> checkBlogTitle(String title) {
		Boolean res=blogRepo.existsByTitle(title);
		
			return new ResponseEntity<Boolean>(res,HttpStatus.OK);
	}


		@Override
		public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId) {
			return blogRepo.findById(blogId)
					.map(blog -> ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
							.setMessage("blog Fetched ")
							.setData(mapToBlogResponse(blog))))
					.orElseThrow(()-> new BlogNotFoundByIdException("Failed to Fetch blog"));

		}


	
	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogById(int blogId, BlogRequest blogRequest) {
		return blogRepo.findById(blogId).map(blog->{
			Blog blog1=mapToBlogEntity(blogRequest,new Blog());
			blog1.setBlogId(blog.getBlogId());
			blog1=blogRepo.save(blog1);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("blog created successfully")
					.setData(mapToBlogResponse(blog)));

		}).orElseThrow(()-> new BlogNotFoundByIdException("Failed to create blog"));
	}


	

	
}
