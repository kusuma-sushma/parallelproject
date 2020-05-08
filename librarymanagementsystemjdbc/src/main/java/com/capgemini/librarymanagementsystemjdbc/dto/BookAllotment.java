package com.capgemini.librarymanagementsystemjdbc.dto;

import java.sql.Date;


public class BookAllotment {
	private int bookAllotmentId;
	private int bookId;
	private String bookName;
	private int userId;
	private String userName;
	private Date issueDate;
	private Date returnDate;
	private Date expectedReturnDate;
	private double fine;
	public int getBookAllotmentId() {
		return bookAllotmentId;
	}
	public void setBookAllotmentId(int bookAllotmentId) {
		this.bookAllotmentId = bookAllotmentId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(Date expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	
	
}
