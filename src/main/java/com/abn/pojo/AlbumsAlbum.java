package com.abn.pojo;

public class AlbumsAlbum {
	
	private long assetId;
    private long ownerId;
    private String thumbnailEncryption;
    private long id;
    private String albumName;
    
	public long getAssetId() {
		return assetId;
	}
	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public String getThumbnailEncryption() {
		return thumbnailEncryption;
	}
	public void setThumbnailEncryption(String thumbnailEncryption) {
		this.thumbnailEncryption = thumbnailEncryption;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
}
