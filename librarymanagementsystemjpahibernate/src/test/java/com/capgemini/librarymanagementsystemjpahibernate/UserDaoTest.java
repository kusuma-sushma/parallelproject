package com.capgemini.librarymanagementsystemjpahibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjpahibernate.dao.UserDao;
import com.capgemini.librarymanagementsystemjpahibernate.dao.UserDaoImplementation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjpahibernate.exception.LibraryManagementSystemException;

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
		UserRequestInformation status = dao.borrowBook(121212, 123123);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testBorrowBookFalse() throws LibraryManagementSystemException {
		UserRequestInformation status = dao.borrowBook(121212, 123123);
		Assertions.assertNull(status);
	}
	
	@Test
	public void testReturnBook() throws LibraryManagementSystemException {
		UserRequestInformation status = dao.returnBook(121212, 123123);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testReturnBookFalse() throws LibraryManagementSystemException {
		UserRequestInformation status = dao.returnBook(121212, 123123);
		Assertions.assertNull(status);
	}
}
