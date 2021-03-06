package com.poscoict.jblog.vo;

public class BlogVo {
	private String title;
	private String logo;
	private String user_id;
	
	public BlogVo(String title, String logo, String user_id) {
		this.title = title;
		this.logo = logo;
		this.user_id = user_id;
	}
	
	public BlogVo() {}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
	@Override
	public String toString() {
		return "BlogVo [title=" + title + ", logo=" + logo + ", user_id=" + user_id + "]";
	}
	
	
	
	

}
