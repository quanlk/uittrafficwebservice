package com.uit.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

	private long id;
	private String username;
	private String password;
	private String phoneNumber;
	private String role;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public Account() {	
		this.id = -1;
		this.username = "";
		this.password = "";
		this.phoneNumber = "";
		this.role = "";
	}

	public Account(long id, String username, String password,
			String phoneNumber, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

}
