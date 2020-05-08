package com.capgemini.librarymanagementsystemjpahibernate.dto;

import java.io.Serializable;
import java.util.Date;

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
	@Column(nullable=false)
	private int userId;
	@Column
	private String userName;
	@Column
	private String status;
	@Column
	private Date dateOfIssued;
	@Column
	private Date dateOfReturn;
	@Column
	private boolean bookIssued;
	@Column
	private boolean bookReturned;
	
}
