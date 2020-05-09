package com.capgemini.librarymanagementsystemspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspringrest.dao.AdminDao;
import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemspringrest.factory.LibraryManagementSystemFactory;

@Service
public class AdminServiceImplementation implements AdminService{
	@Autowired
	private AdminDao adminDao;

	@Override
	public boolean addUser(UserInformation userInfo) {
		return adminDao.addUser(userInfo);
	}

	@Override
	public UserInformation adminLogin(String email, String password) {
		return adminDao.adminLogin(email, password);
	}

	@Override
	public boolean addBook(BooksInformation info) {
		return adminDao.addBook(info);
	}

	@Override
	public boolean removeBook(int bookId) {
		return adminDao.removeBook(bookId);
	}

	@Override
	public boolean issueBook(int requestId) {
		return adminDao.issueBook(requestId);
	}

	@Override
	public boolean updateBook(BooksInformation bookInfo) {
		return adminDao.updateBook(bookInfo);
	}

	@Override
	public BooksInformation searchBook(int bookId) {
		return adminDao.searchBook(bookId);
	}

	@Override
	public List<BooksInformation> showAllBooks() {
		return adminDao.showAllBooks();
	}

	@Override
	public List<UserRequestInformation> showAllRequests() {
		return adminDao.showAllRequests();
	}

	@Override
	public List<UserInformation> showAllUsers() {
		return adminDao.showAllUsers();
	}

	@Override
	public boolean isBookRecevied(int requestId) {
		return adminDao.isBookRecevied(requestId);
	}

}
