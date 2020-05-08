package com.capgemini.librarymanagementsystemspringrest.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="lms_spring_bookallotment")

public class BookAllotment {
	@Id
	@Column
	@GeneratedValue
	private int bookAllotedId;
	@Column
	private int bookId;
	@Column
	private String bookName;
	@Column(nullable=false)
	private int userId;
	@Column
	private String userName;
	@Column
	private Date issueDate;
	@Column
	private Date returnDate;
	@Column
	private double fine;
}