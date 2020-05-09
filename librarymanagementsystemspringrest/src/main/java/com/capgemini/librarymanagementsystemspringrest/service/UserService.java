package com.capgemini.librarymanagementsystemspringrest.service;

import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;


public interface UserService {
	
	//boolean registration(UserInformation userInfo);
	UserInformation userLogin(String email, String password);
	boolean borrowBook(int id, int bookId);
	boolean returnBook(int userId, int bookId);
	

}
