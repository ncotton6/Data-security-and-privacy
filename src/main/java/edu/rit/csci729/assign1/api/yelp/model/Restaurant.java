package edu.rit.csci729.assign1.api.yelp.model;

/**
 * A POJO to hold restaurant data.
 * 
 * @author Nathaniel Cotton
 *
 */
public class Restaurant {

	private String name, display_phone;
	private String[] display_location;
	private boolean is_closed;
	private float latitude, longitude, rating;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getDisplay_phone() {
		return display_phone;
	}

	public void setDisplay_phone(String display_phone) {
		this.display_phone = display_phone;
	}

	public String[] getDisplay_location() {
		return display_location;
	}

	public void setDisplay_location(String[] display_location) {
		this.display_location = display_location;
	}

	public boolean isIs_closed() {
		return is_closed;
	}

	public void setIs_closed(boolean is_closed) {
		this.is_closed = is_closed;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

}
