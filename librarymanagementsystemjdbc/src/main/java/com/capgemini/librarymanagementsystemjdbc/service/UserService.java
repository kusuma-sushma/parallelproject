package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;


public interface UserService {
	
	//boolean registration(UserInformation userInfo);
	UserInformation userLogin(String email, String password);
	UserRequestInformation borrowBook(int userId, int bookId);
	UserRequestInformation returnBook(int userId, int bookId);
	

}
