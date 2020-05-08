package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDao;
import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryManagementSystemFactory;



public class AdminServiceImplementation implements AdminService{
	
	private AdminDao adminDao=LibraryManagementSystemFactory.getAdminDao();

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

//	@Override
//	public boolean returnedBook(int bookId) {
//		return adminDao.returnedBook(bookId);
//	}

	@Override
	public boolean issueBook(int userId, int bookId) {
		return adminDao.issueBook(userId, bookId);
	}

	@Override
	public boolean updateBook(int bookId) throws LibraryManagementSystemException {
		return adminDao.updateBook(bookId);
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
	public boolean isBookRecevied(int userId, int bookId) {
		return adminDao.isBookRecevied(userId, bookId);
	}


}
