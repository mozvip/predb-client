package com.github.mozvip.predb.model;

public class PreDBPostDetails {

	private String rlsName;
	private String group;
	private String[] tags;
	private float sizeInMegs;
	private int fileCount;
	
	private String title;
	private String language;
	private int season;
	private int episode;
	
	private String baseUri;
	
	public PreDBPostDetails(String baseUri) {
		this.baseUri = baseUri;
	}
	
	public String baseUri() {
		return baseUri;
	}

	public String getRlsName() {
		return rlsName;
	}

	public void setRlsName(String rlsName) {
		this.rlsName = rlsName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public float getSizeInMegs() {
		return sizeInMegs;
	}

	public void setSizeInMegs(float sizeInMegs) {
		this.sizeInMegs = sizeInMegs;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

}
