package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementSystemException;



public interface AdminDao {

	UserInformation adminLogin(String email, String password);
	boolean addUser(UserInformation userInfo);
	boolean addBook(BooksInformation bookInfo);
	boolean removeBook(int bookId);
	//boolean returnedBook(int bookId);
	boolean issueBook(int userId, int bookId);
	boolean updateBook(int bookId) throws LibraryManagementSystemException;
	BooksInformation searchBook(int bookId);
	List<BooksInformation> showAllBooks(); 
	List<UserInformation> showAllUsers();
	List<UserRequestInformation> showAllRequests();
	boolean isBookRecevied(int userId, int bookId);

}
