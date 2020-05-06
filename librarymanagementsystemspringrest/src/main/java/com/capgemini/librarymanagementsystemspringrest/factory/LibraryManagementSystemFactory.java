package com.capgemini.librarymanagementsystemspringrest.factory;

import java.util.Date;

import com.capgemini.librarymanagementsystemspringrest.dao.AdminDao;
import com.capgemini.librarymanagementsystemspringrest.dao.AdminDaoImplementation;
import com.capgemini.librarymanagementsystemspringrest.dao.UserDao;
import com.capgemini.librarymanagementsystemspringrest.dao.UserDaoImplementation;
import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.service.AdminService;
import com.capgemini.librarymanagementsystemspringrest.service.AdminServiceImplementation;
import com.capgemini.librarymanagementsystemspringrest.service.UserService;
import com.capgemini.librarymanagementsystemspringrest.service.UserServiceImplementation;


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
	
//	public static LibraryManagementSystemValidation getValidation() {
//		return new LibraryManagementSystemValidation();
//	}

	public static Date getDate() {
		return new Date();
	}
}
