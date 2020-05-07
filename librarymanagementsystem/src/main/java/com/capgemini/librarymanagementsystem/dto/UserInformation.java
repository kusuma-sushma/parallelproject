package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

public class UserInformation implements Serializable {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String department;
	private int noOfBooksBorrowed = 0;
	private double fine;

	public UserInformation() {
		
	}
	
	public UserInformation(int userId, String username, String password, String email, String department,
			int noOfBooksBorrowed, double fine) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.department = department;
		this.noOfBooksBorrowed = noOfBooksBorrowed;
		this.fine = fine;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getNoOfBooksBorrowed() {
		return noOfBooksBorrowed;
	}

	public void setNoOfBooksBorrowed(int noOfBooksBorrowed) {
		this.noOfBooksBorrowed = noOfBooksBorrowed;
	}

}
