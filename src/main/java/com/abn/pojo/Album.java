package com.abn.pojo;

import java.util.ArrayList;

public class Album {
	
	ArrayList images;
    int ownerId;
    String thumbnailEncryption;
    int id;
    String albumName;
    
	
	public ArrayList getImages() {
		return images;
	}
	public void setImages(ArrayList images) {
		this.images = images;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getThumbnailEncryption() {
		return thumbnailEncryption;
	}
	public void setThumbnailEncryption(String thumbnailEncryption) {
		this.thumbnailEncryption = thumbnailEncryption;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
}
