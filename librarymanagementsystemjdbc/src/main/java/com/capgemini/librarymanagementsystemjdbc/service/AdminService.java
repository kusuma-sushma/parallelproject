package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementSystemException;


public interface AdminService {

	boolean addUser(UserInformation userInfo);
	public UserInformation adminLogin(String email, String password);
	public boolean addBook(BooksInformation info);
	public boolean removeBook(int bookId);	
	//boolean returnedBook(int bookId);
	boolean issueBook(int userId, int bookId);
	boolean updateBook(int bookId) throws LibraryManagementSystemException;
	BooksInformation searchBook(int bookId);
	List<BooksInformation> showAllBooks();
	List<UserRequestInformation> showAllRequests();
	List<UserInformation> showAllUsers();
	boolean isBookRecevied(int userId, int bookId);
	
}
