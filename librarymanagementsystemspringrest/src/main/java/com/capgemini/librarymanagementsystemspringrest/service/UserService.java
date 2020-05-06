package com.capgemini.librarymanagementsystemspringrest.service;

import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;


public interface UserService {
	
	//boolean registration(UserInformation userInfo);
	UserInformation userLogin(String email, String password);
	UserRequestInformation borrowBook(UserInformation userInfo, BooksInformation bookInfo);
	UserRequestInformation returnBook(UserInformation userInfo, BooksInformation bookInfo);
	

}
