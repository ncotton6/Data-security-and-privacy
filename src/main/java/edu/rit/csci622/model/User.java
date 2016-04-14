package edu.rit.csci622.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

	private int idUser;
	private int role = 0;
	private boolean loggedIn;
	private String username;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String joinedOn;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJoinedOn() {
		return joinedOn;
	}
	public void setLoggedIn(){this.loggedIn = true;}
	public Date getJoinedOnAsDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(getJoinedOn());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setJoinedOnAsDate(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		setJoinedOn(sdf.format(d));		
	}
	
	public void setJoinedOn(String joinedOn) {
		this.joinedOn = joinedOn;
	}
	
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", role=" + role + ", username=" + username + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", email=" + email + ", password=" + password + ", joinedOn=" + joinedOn
				+ "]";
	}
	
}
