package com.capgemini.librarymanagementsystemspringrest.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
