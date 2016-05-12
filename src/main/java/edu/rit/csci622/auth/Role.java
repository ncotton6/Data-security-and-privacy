package edu.rit.csci622.auth;

/**
 * A class to represent a synchronization with the roles in the database.
 * 
 * @author Nathaniel Cotton
 *
 */
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
