package com.capgemini.librarymanagementsystemjpahibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemjpahibernate.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;


public interface UserDao {

	//boolean registration(UserInformation info,int count);
	UserInformation userLogin(String email, String password);
	UserRequestInformation borrowBook(int userId, int bookId);
	UserRequestInformation returnBook(int userId, int bookId);
}
