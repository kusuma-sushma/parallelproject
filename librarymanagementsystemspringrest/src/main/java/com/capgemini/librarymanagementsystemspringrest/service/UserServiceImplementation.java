package com.capgemini.librarymanagementsystemspringrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspringrest.dao.UserDao;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;

@Service
public class UserServiceImplementation implements UserService{
	@Autowired
	private UserDao userDao;

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
