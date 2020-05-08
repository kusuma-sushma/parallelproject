package com.capgemini.librarymanagementsystemjdbc;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.service.UserServiceImplementation;

public class UserServiceTest {

	UserService service = new UserServiceImplementation();
	
	@Test
	public void testUserLogin() {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = service.userLogin("padma@gmail.com", "padma1");
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testUserLoginFalse() {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = service.userLogin("padma123@gmail.com", "padma123");
		Assertions.assertNull(status);
	}
	
	@Test
	public void testBorrowBook() {
		UserRequestInformation status = service.borrowBook(121212, 123123);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testBorrowBookFalse() {
		UserRequestInformation status = service.borrowBook(121212, 123123);
		Assertions.assertNull(status);
	}
	
	@Test
	public void testReturnBook() {
		UserRequestInformation status = service.returnBook(121212, 123123);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testReturnBookFalse() {
		UserRequestInformation status = service.returnBook(121212, 123123);
		Assertions.assertNull(status);
	}

	
}
