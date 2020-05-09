package com.capgemini.librarymanagementsystemspringrest.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@Entity
@Table(name="lms_spring_bookallotment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookAllotment {
	@Id
	@Column
	@GeneratedValue
	private int bookAllotedId;
	@Column(nullable=false)
	private int bookId;
	@Column(nullable=false)
	private int userId;
	@Column
	private Date issueDate;
	@Column
	private Date returnDate;
	@Column
	private double fine;
}
