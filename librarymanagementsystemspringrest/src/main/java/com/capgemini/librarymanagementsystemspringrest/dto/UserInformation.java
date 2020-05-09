package com.capgemini.librarymanagementsystemspringrest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity
@Table(name="lms_spring_allusers")
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	private int noOfBooksBorrowed=0;
	@Column
	private double fine=0;
	
	//public int getNoOfBooks() {
		//return noOfBooks;
	//}
	//public void setNoOfBooks(int noOfBooks) {
		//this.noOfBooks = noOfBooks;
	//}
	
}
