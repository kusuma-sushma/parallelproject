package com.capgemini.librarymanagementsystemspringrest.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
//@SuppressWarnings("serial")
@Data
@Entity
@Table(name="lms_spring_allusers")
public class UserInformation {
	@Id
	@Column
	@GeneratedValue
	private int userId;
	@Column
	private String username;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String department;
	//private int noOfBooks=0;
	@Column
	private String role;
	@Column	
	private int noOfBooksBorrowed;
	@Column
	private double fine;
	
	//public int getNoOfBooks() {
		//return noOfBooks;
	//}
	//public void setNoOfBooks(int noOfBooks) {
		//this.noOfBooks = noOfBooks;
	//}
	
}
