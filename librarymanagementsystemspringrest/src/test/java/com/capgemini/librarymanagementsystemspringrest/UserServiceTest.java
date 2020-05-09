package com.capgemini.librarymanagementsystemspringrest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemspringrest.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystemspringrest.service.UserService;
import com.capgemini.librarymanagementsystemspringrest.service.UserServiceImplementation;

public class UserServiceTest {
	@Autowired
	UserService service;
	
	@Test
	public void testUserLogin() throws LibraryManagementSystemException {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = service.userLogin("padma@gmail.com", "padma1");
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testUserLoginFalse() throws LibraryManagementSystemException {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = service.userLogin("padma123@gmail.com", "padma123");
		Assertions.assertNull(status);
	}
	
	@Test
	public void testBorrowBook() throws LibraryManagementSystemException {
		boolean status = service.borrowBook(121212, 123123);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testBorrowBookFalse() throws LibraryManagementSystemException {
		boolean status = service.borrowBook(121212, 123123);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testReturnBook() throws LibraryManagementSystemException {
		boolean status = service.returnBook(121212, 123123);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testReturnBookFalse() throws LibraryManagementSystemException {
		boolean status = service.returnBook(121212, 123123);
		Assertions.assertFalse(status);
	}

	
}
