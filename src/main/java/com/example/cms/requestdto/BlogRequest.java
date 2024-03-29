package com.example.cms.requestdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BlogRequest {
	@NotNull(message ="must not be null")
	@Pattern(regexp = "^[a-zA-Z]*$")
	private String title;
	
	@NotNull(message = "Atleast one topic should be specified")
	private String[] topics;
	
	
	private String about;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getTopics() {
		return topics;
	}
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
	
	

}
