package com.example.cms.exception;

public class BlogNotFoundByIdException extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public BlogNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

}
