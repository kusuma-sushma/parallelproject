package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BooksInformation implements Serializable {

	private int bookId;
	private String bookName;
	private String bookCategory;
	private String bookAuthor;
	private String bookPublisher;
	private boolean bookAvailable=true;
	private Date bookIssueDate;
	private Date bookReturnDate;

	public BooksInformation() {
		
	}
	
	public BooksInformation(int bookId, String bookName, String bookCategory, String bookAuthor, String bookPublisher,
			boolean bookAvailable) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookCategory = bookCategory;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
		this.bookAvailable = bookAvailable;
	}

	public Date getIssuedate() {
		return bookIssueDate;
	}

	public void setIssuedate(Date issuedate) {
		this.bookIssueDate = issuedate;
	}

	public Date getReturndate() {
		return bookReturnDate;
	}

	public void setReturndate(Date returndate) {
		this.bookReturnDate = returndate;
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

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public boolean isBookAvailable() {
		return bookAvailable;
	}

	public void setBookAvailable(boolean bookAvailable) {
		this.bookAvailable = bookAvailable;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	
	}
