package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDao;
import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryManagementSystemFactory;


public class UserServiceImplementation implements UserService{

	private UserDao userDao=LibraryManagementSystemFactory.getUserDao();

//	@Override
//	public boolean registration(UserInformation userInfo) {
//		return userDao.registration(userInfo);
//	}

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
