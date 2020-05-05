package com.capgemini.librarymanagementsystemjpahibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemjpahibernate.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;

public interface AdminService {

	boolean addUser(UserInformation userInfo);
	UserInformation adminLogin(String email, String password);
	boolean addBook(BooksInformation info);
	boolean removeBook(int bookId);	
	//boolean returnedBook(int bookId);
	boolean issueBook(UserInformation userInfo, BooksInformation bookInfo);
	BooksInformation updateBook(int bookId);
	BooksInformation searchBook(int bookId);
	List<BooksInformation> showAllBooks();
	List<UserRequestInformation> showAllRequests();
	List<UserInformation> showAllUsers();
	boolean isBookRecevied(UserInformation userInfo, BooksInformation bookInfo);

}
