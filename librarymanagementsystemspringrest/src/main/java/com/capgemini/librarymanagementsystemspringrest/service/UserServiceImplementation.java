package com.capgemini.librarymanagementsystemspringrest.service;

import com.capgemini.librarymanagementsystemspringrest.dao.UserDao;
import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemspringrest.factory.LibraryManagementSystemFactory;


public class UserServiceImplementation implements UserService{

	private UserDao userDao=LibraryManagementSystemFactory.getUserDao();

	@Override
	public UserInformation userLogin(String email, String password) {
		return userDao.userLogin(email, password);
	}

	@Override
	public UserRequestInformation borrowBook(UserInformation userInfo, BooksInformation bookInfo) {
		return userDao.borrowBook(userInfo, bookInfo);
	}

	@Override
	public UserRequestInformation returnBook(UserInformation userInfo, BooksInformation bookInfo) {
		return userDao.returnBook(userInfo, bookInfo);
	}

}
