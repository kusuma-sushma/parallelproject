package com.capgemini.librarymanagementsystem.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.AdminInformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;


public interface AdminDao {

	AdminInformation adminLogin(String email, String password) throws LibraryManagementSystemException;
	boolean addUser(UserInformation userInfo);
	boolean addBook(BooksInformation bookInfo) throws LibraryManagementSystemException;
	boolean removeBook(int bookId) throws LibraryManagementSystemException;
	//boolean returnedBook(int bookId);
	boolean issueBook(UserInformation userInfo, BooksInformation bookInfo) throws LibraryManagementSystemException;
	BooksInformation updateBook(int bookId);
	BooksInformation searchBook(int bookId);
	List<BooksInformation> showAllBooks(); 
	List<UserInformation> showAllUsers();
	List<UserRequestInformation> showAllRequests();
	boolean isBookRecevied(UserInformation userInfo, BooksInformation bookInfo) throws LibraryManagementSystemException;
	

}
