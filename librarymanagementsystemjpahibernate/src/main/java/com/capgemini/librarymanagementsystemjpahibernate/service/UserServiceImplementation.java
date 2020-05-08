package com.capgemini.librarymanagementsystemjpahibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemjpahibernate.dao.UserDao;
import com.capgemini.librarymanagementsystemjpahibernate.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjpahibernate.factory.LibraryManagementSystemFactory;


public class UserServiceImplementation implements UserService{

	private UserDao userDao=LibraryManagementSystemFactory.getUserDao();

	@Override
	public UserInformation userLogin(String email, String password) {
		return userDao.userLogin(email, password);
	}

	@Override
	public UserRequestInformation borrowBook(int userId, int bookId) {
		return userDao.borrowBook(userId, bookId);
	}

	@Override
	public UserRequestInformation returnBook(int userId, int bookId) {
		return userDao.returnBook(userId, bookId);
	}

}
