package com.capgemini.librarymanagementsystemjpahibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="lms_jpa_lms_request")
public class UserRequestInformation implements Serializable{
	@Id
	@Column
	private int bookId;
	@Column
	private String bookName;
	@Column
	private int userId;
	@Column
	private String userName;
	@Column
	private String status;
	
}
