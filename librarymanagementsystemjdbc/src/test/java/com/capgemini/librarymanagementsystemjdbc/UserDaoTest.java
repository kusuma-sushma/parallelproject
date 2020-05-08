package com.capgemini.librarymanagementsystemjdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDao;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDaoImplementation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;

public class UserDaoTest {

	UserDao dao = new UserDaoImplementation();
	
	@Test
	public void testUserLogin() {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = dao.userLogin("padma@gmail.com", "padma1");
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testUserLoginFalse() {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = dao.userLogin("padma123@gmail.com", "padma123");
		Assertions.assertNull(status);
	}
	
	@Test
	public void testBorrowBook() {
		UserRequestInformation status = dao.borrowBook(121212, 123123);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testBorrowBookFalse() {
		UserRequestInformation status = dao.borrowBook(121212, 123123);
		Assertions.assertNull(status);
	}
	
	@Test
	public void testReturnBook() {
		UserRequestInformation status = dao.returnBook(121212, 123123);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testReturnBookFalse() {
		UserRequestInformation status = dao.returnBook(121212, 123123);
		Assertions.assertNull(status);
	}
}
