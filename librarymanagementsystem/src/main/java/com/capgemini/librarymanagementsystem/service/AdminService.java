package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.AdminInformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;

public interface AdminService {

	boolean addUser(UserInformation userInfo) throws LibraryManagementSystemException;

	public AdminInformation adminLogin(String email, String password) throws LibraryManagementSystemException;

	public boolean addBook(BooksInformation info) throws LibraryManagementSystemException;

	public boolean removeBook(int bookId) throws LibraryManagementSystemException;

	// boolean returnedBook(int bookId);
	boolean issueBook(int userId, int bookI) throws LibraryManagementSystemException;

	BooksInformation updateBook(int bookId);

	BooksInformation searchBook(int bookId);

	List<BooksInformation> showAllBooks();

	List<UserRequestInformation> showAllRequests();

	List<UserInformation> showAllUsers();

	boolean isBookRecevied(int userId, int bookId) throws LibraryManagementSystemException;

}
