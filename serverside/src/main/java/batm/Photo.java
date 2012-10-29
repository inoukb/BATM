package batm;

import java.sql.Timestamp;

public class Photo {

	private long pid;
	private float longitude;
	private float latitude;
	private float radius;
	private String photo;
	private String comment;
	private long userId;
	private Timestamp timestamp = new Timestamp(0);
	private String status;
	private String site;
	
	public void setTimestamp(Timestamp timestamp) {
		if(timestamp != null){
			this.timestamp = timestamp;
		}
	}
	
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	
	
}
