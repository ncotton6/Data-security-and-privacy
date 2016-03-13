package edu.rit.csci622.model;

public class Session {

	private int idsession, userId;
	private String uuid, createdOn;
	
	public int getIdsession() {
		return idsession;
	}
	public void setIdsession(int idsession) {
		this.idsession = idsession;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
}
