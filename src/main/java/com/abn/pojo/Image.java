package com.abn.pojo;


public class Image {
	private int ownerAccountId;
	private  String url;
	private  int dateTaken;
	private  int id;
	
	
	public int getOwnerAccountId() {
		return ownerAccountId;
	}
	public void setOwnerAccountId(int ownerAccountId) {
		this.ownerAccountId = ownerAccountId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(int dateTaken) {
		this.dateTaken = dateTaken;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
