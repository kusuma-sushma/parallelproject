package com.capgemini.librarymanagementsystem.service;

import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;

public interface UserService {
	
	UserInformation userLogin(String email, String password) throws LibraryManagementSystemException;
	UserRequestInformation borrowBook(UserInformation userInfo, BooksInformation bookInfo) throws LibraryManagementSystemException;
	UserRequestInformation returnBook(UserInformation userInfo, BooksInformation bookInfo) throws LibraryManagementSystemException;

}
