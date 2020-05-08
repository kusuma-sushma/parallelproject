package com.capgemini.librarymanagementsystemjpahibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemjpahibernate.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;


public interface UserService {
	
	//boolean registration(UserInformation userInfo);
	UserInformation userLogin(String email, String password);
	UserRequestInformation borrowBook(int userId, int bookId);
	UserRequestInformation returnBook(int userId, int bookId);
	

}
