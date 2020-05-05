package com.capgemini.librarymanagementsystemjpahibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemjpahibernate.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;



public interface AdminDao {

	UserInformation adminLogin(String email, String password);
	boolean addUser(UserInformation userInfo);
	boolean addBook(BooksInformation bookInfo);
	boolean removeBook(int bookId);
	//boolean returnedBook(int bookId);
	boolean issueBook(UserInformation userInfo, BooksInformation bookInfo);
	BooksInformation updateBook(int bookId);
	BooksInformation searchBook(int bookId);
	List<BooksInformation> showAllBooks(); 
	List<UserInformation> showAllUsers();
	List<UserRequestInformation> showAllRequests();
	boolean isBookRecevied(UserInformation userInfo, BooksInformation bookInfo);

}
