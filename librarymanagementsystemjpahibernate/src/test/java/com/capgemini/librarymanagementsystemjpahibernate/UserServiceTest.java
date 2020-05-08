package com.capgemini.librarymanagementsystemjpahibernate;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjpahibernate.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystemjpahibernate.service.UserService;
import com.capgemini.librarymanagementsystemjpahibernate.service.UserServiceImplementation;

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
