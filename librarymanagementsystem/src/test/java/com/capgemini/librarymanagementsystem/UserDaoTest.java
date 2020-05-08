package com.capgemini.librarymanagementsystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.UserDao;
import com.capgemini.librarymanagementsystem.dao.UserDaoImplementation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;

public class UserDaoTest {

	UserDao dao = new UserDaoImplementation();
	
	@Test
	public void testUserLogin() throws LibraryManagementSystemException {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = dao.userLogin("padma@gmail.com", "padma1");
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testUserLoginFalse() throws LibraryManagementSystemException {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = dao.userLogin("padma123@gmail.com", "padma123");
		Assertions.assertNull(status);
	}
	
	@Test
	public void testBorrowBook() throws LibraryManagementSystemException {
		UserRequestInformation status = dao.borrowBook(121212, 123456);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testBorrowBookFalse() throws LibraryManagementSystemException {
		UserRequestInformation status = dao.borrowBook(121212, 123456);
		Assertions.assertNull(status);
	}
	
	@Test
	public void testReturnBook() throws LibraryManagementSystemException {
		UserRequestInformation status = dao.returnBook(121212, 123456);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testReturnBookFalse() throws LibraryManagementSystemException {
		UserRequestInformation status = dao.returnBook(121212, 123456);
		Assertions.assertNull(status);
	}
}
