package com.capgemini.librarymanagementsystemspringrest.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="lms_spring_allbooks")

public class BooksInformation implements Serializable{
	@Id
	@Column
	@GeneratedValue
	private int bookId;
	@Column
	private String bookName;
	@Column
	private String bookAuthor;
	@Column
	private String bookCategory;
	@Column
	private String bookPublisher;
//	@Column
//	private Date issueDate;
//	@Column
//	private Date returnDate;
//	@Column
//	private double fine;

}
