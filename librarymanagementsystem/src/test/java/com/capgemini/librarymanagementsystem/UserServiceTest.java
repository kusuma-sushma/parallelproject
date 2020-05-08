package com.capgemini.librarymanagementsystem;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.service.UserServiceImplementation;

public class UserServiceTest {

	UserService service = new UserServiceImplementation();
	
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
		UserRequestInformation status = service.borrowBook(121212, 123123);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testBorrowBookFalse() throws LibraryManagementSystemException {
		UserRequestInformation status = service.borrowBook(121212, 123123);
		Assertions.assertNull(status);
	}
	
	@Test
	public void testReturnBook() throws LibraryManagementSystemException {
		UserRequestInformation status = service.returnBook(121212, 123123);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testReturnBookFalse() throws LibraryManagementSystemException {
		UserRequestInformation status = service.returnBook(121212, 123123);
		Assertions.assertNull(status);
	}

	
}
