package com.capgemini.librarymanagementsystemspringrest.dto;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="lms_spring_request")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestInformation implements Serializable{

	@Id
	@Column
	@GeneratedValue
	private int requestId;
	@Column
	private int id;
	@Column
	private int bookId;
	@Column
	private Date issueDate;
	@Column
	private Date expectedReturnDate;
	@Column
	private Date returnedDate;

	
}
