package com.capgemini.librarymanagementsystem.service;

import com.capgemini.librarymanagementsystem.dao.AdminDao;
import com.capgemini.librarymanagementsystem.dao.UserDao;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystem.factory.LibraryManagementSystemFactory;

public class UserServiceImplementation implements UserService {

	private UserDao userDao = LibraryManagementSystemFactory.getUserDao();
	
	@Override
	public UserInformation userLogin(String email, String password) throws LibraryManagementSystemException {
		return userDao.userLogin(email, password);
	}

	@Override
	public UserRequestInformation borrowBook(int userId, int bookId) throws LibraryManagementSystemException {
		return userDao.borrowBook(userId, bookId);
	}

	@Override
	public UserRequestInformation returnBook(int userId, int bookId) throws LibraryManagementSystemException {
		return userDao.returnBook(userId, bookId);
	}


}
