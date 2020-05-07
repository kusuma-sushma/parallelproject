package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserRequestInformation implements Serializable {
	
	private int bookId;
	private int userId;
	private Date dateOfIssued;
	private Date dateOfReturn;
	private Date actualReturnDate;
	private boolean bookIssued;
	private boolean bookReturned;

	public UserRequestInformation() {
		
	}
	public UserRequestInformation(int bookId, int userId, Date dateOfIssued,
			Date dateOfReturn, Date actualReturnDate, boolean bookIssued, boolean bookReturned) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.dateOfIssued = dateOfIssued;
		this.dateOfReturn = dateOfReturn;
		this.actualReturnDate = actualReturnDate;
		this.bookIssued = bookIssued;
		this.bookReturned = bookReturned;
	}

	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getDateOfIssued() {
		return dateOfIssued;
	}

	public void setDateOfIssued(Date dateOfIssued) {
		this.dateOfIssued = dateOfIssued;
	}

	public Date getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public Date getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public boolean isBookIssued() {
		return bookIssued;
	}

	public void setBookIssued(boolean bookIssued) {
		this.bookIssued = bookIssued;
	}

	public boolean isBookReturned() {
		return bookReturned;
	}

	public void setBookReturned(boolean bookReturned) {
		this.bookReturned = bookReturned;
	}

}
