package com.capgemini.librarymanagementsystemspringrest.service;

import java.util.List;

import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;

public interface AdminService {

	boolean addUser(UserInformation userInfo);
	UserInformation adminLogin(String email, String password);
	boolean addBook(BooksInformation info);
	boolean removeBook(int bookId);	
	//boolean returnedBook(int bookId);
	boolean issueBook(int requestId);
	boolean updateBook(BooksInformation bookInfo);
	BooksInformation searchBook(int bookId);
	List<BooksInformation> showAllBooks();
	List<UserRequestInformation> showAllRequests();
	List<UserInformation> showAllUsers();
	boolean isBookRecevied(int requestId);

}
