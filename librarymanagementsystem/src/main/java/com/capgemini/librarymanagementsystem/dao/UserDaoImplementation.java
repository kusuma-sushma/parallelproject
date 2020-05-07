package com.capgemini.librarymanagementsystem.dao;

import java.util.Calendar;
import java.util.Date;

import com.capgemini.librarymanagementsystem.database.LibraryManagementSystemDataBase;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystem.factory.LibraryManagementSystemFactory;

public class UserDaoImplementation implements UserDao {

	Date date=new Date();
	Date expectedReturnDate;
	Date returnedDate;
	
	@Override
	public UserInformation userLogin(String email, String password) throws LibraryManagementSystemException {
			for(UserInformation info:LibraryManagementSystemDataBase.user) {
			if(info.getEmail().equals(email)) {
				if (info.getPassword().equals(password)) {
					return info;
				} else {
					System.err.println("Password which is entered is invalid");
			}
			}
			}
			throw new LibraryManagementSystemException("Email which is mentioned is invalid. Please enter valid credentials");
			}
		

	@Override
	public UserRequestInformation borrowBook(int userId, int bookId) throws LibraryManagementSystemException {
//		boolean request = false; 
		boolean requestBook = false;
//		UserInformation userInfo=new UserInformation();
//		BooksInformation bookInfo=new BooksInformation();
		UserRequestInformation requestInfo = LibraryManagementSystemFactory.userRequest();
		for (UserRequestInformation requestInfo2 : LibraryManagementSystemDataBase.requests) {
			if (bookId == requestInfo2.getBookId()) {
//				if (userId!=new UserInformation().getUserId()) {
				requestBook = true;
				}
//			}

		}

		if (!requestBook) {
			for(UserInformation user:LibraryManagementSystemDataBase.user) {
				if (user.getUserId()==userId) {
					for (BooksInformation book : LibraryManagementSystemDataBase.book) {
						if (book.getBookId() == bookId) {
							if (book.isBookAvailable() == true) {
								requestInfo.setUserId(userId);
								requestInfo.setBookId(bookId);
								requestInfo.setBookIssued(false);
							requestBook = true;
						}
					}
			}
				}
			}
			if (requestBook == true) {
				LibraryManagementSystemDataBase.requests.add(requestInfo);
				return requestInfo;
			}
		}
		
		throw new LibraryManagementSystemException("Invalid user or book credentials book can not be borrowed");
		}

	@Override
	public UserRequestInformation returnBook(int userId, int bookId) throws LibraryManagementSystemException {
		Calendar calendar= Calendar.getInstance();
		calendar.add(Calendar.DATE, 20);
		returnedDate = calendar.getTime();
		for (UserRequestInformation requestInfo : LibraryManagementSystemDataBase.requests) {
			
			  if (requestInfo.getBookId() == bookId &&
			  requestInfo.getUserId() == userId &&
			  requestInfo.isBookIssued() == true) {
			 
			//if (requestInfo.isBookIssued() == true) {
				System.out.println("Returning Issued book only");
				requestInfo.setBookReturned(true);
				requestInfo.setDateOfReturn(returnedDate);
				return requestInfo;
		//	}
		}
		}
		throw new LibraryManagementSystemException("Invalid user or book credentials book can not be returned");
}
}
