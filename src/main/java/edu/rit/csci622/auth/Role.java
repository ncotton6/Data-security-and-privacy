package edu.rit.csci622.auth;

public enum Role {

	ANONYMOUS(-1),
	CUSTOMER(0),
	EMPLOYEE(3),
	HR(2),
	MANAGER(1);
	
	
	public int roleId;

	Role(int roleId){
		this.roleId = roleId;
	}
	
}
