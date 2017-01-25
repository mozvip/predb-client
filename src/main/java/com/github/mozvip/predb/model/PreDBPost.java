package com.github.mozvip.predb.model;

public class PreDBPost {

	private String id;
	private String time;
	private PreDBCategory[] categories;
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public PreDBCategory[] getCategories() {
		return categories;
	}

	public void setCategories(PreDBCategory[] categories) {
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
