package com.capgemini.librarymanagementsystemspringrest.dao;

import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;


public interface UserDao {

	//boolean registration(UserInformation info,int count);
	UserInformation userLogin(String email, String password);
	boolean borrowBook(int id, int bookId);
	boolean returnBook(int userId, int bookId);
}
