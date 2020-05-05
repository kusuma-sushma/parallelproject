package com.capgemini.librarymanagementsystemjpahibernate.factory;

import java.util.Date;

import com.capgemini.librarymanagementsystemjpahibernate.dao.AdminDao;
import com.capgemini.librarymanagementsystemjpahibernate.dao.AdminDaoImplementation;
import com.capgemini.librarymanagementsystemjpahibernate.dao.UserDao;
import com.capgemini.librarymanagementsystemjpahibernate.dao.UserDaoImplementation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.service.AdminService;
import com.capgemini.librarymanagementsystemjpahibernate.service.AdminServiceImplementation;
import com.capgemini.librarymanagementsystemjpahibernate.service.UserService;
import com.capgemini.librarymanagementsystemjpahibernate.service.UserServiceImplementation;
import com.capgemini.librarymanagementsystemjpahibernate.validation.LibraryManagementSystemValidation;


public class LibraryManagementSystemFactory {
	
	private LibraryManagementSystemFactory() {
		
	}
	
	public static UserDao getUserDao() {
		return new UserDaoImplementation();
	}
	public static UserService getUserService() {
		return new UserServiceImplementation();
		
	}
	public static AdminDao getAdminDao() {
		return new AdminDaoImplementation();
	}
	public static AdminService getAdminService() {
		return new AdminServiceImplementation();
	}
	
	public static BooksInformation getBookInfo() {
		return new BooksInformation();
	}
	
	public static UserInformation getUserInfo() {
		return new UserInformation();
	}
	
	public static LibraryManagementSystemValidation getValidation() {
		return new LibraryManagementSystemValidation();
	}

	public static Date getDate() {
		return new Date();
	}
}
