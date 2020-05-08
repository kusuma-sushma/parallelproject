package com.capgemini.librarymanagementsystemjpahibernate;
import java.util.List;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.platform.commons.annotations.*;
import com.capgemini.librarymanagementsystemjpahibernate.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjpahibernate.service.AdminService;
import com.capgemini.librarymanagementsystemjpahibernate.service.AdminServiceImplementation;

public class AdminServiceTest {
	AdminService service = new AdminServiceImplementation();
	
	@Test
	public void testAddUser() {
		UserInformation userInfo = new UserInformation();
		userInfo.setUserId(1);
		userInfo.setUsername("kiara");
		userInfo.setPassword("kiara");
		userInfo.setEmail("kiara@gmail.com");
		userInfo.setDepartment("ece");
		userInfo.setNoOBooksBorrowed(0);
//		userInfo.setRole("student");
		boolean status = service.addUser(userInfo);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testAddUserFalse() {
		UserInformation userInfo = new UserInformation();
		userInfo.setUserId(1);
		userInfo.setUsername("kiara");
		userInfo.setPassword("kiara");
		userInfo.setEmail("kiara@gmail.com");
		userInfo.setDepartment("ece");
		userInfo.setNoOBooksBorrowed(0);
//		userInfo.setRole("student");
		boolean status = service.addUser(userInfo);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testAddBook() {
		BooksInformation bookInfo = new BooksInformation();
		bookInfo.setBookId(1);
		bookInfo.setBookName("java");
		bookInfo.setBookAuthor("charles");
		bookInfo.setBookCategory("tech");
		bookInfo.setBookPublisher("ms");
		bookInfo.setBookAvailable(true);
		boolean status = service.addBook(bookInfo);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testAddBookFalse() {
		BooksInformation bookInfo = new BooksInformation();
		bookInfo.setBookId(1);
		bookInfo.setBookName("java");
		bookInfo.setBookAuthor("charles");
		bookInfo.setBookCategory("tech");
		bookInfo.setBookPublisher("ms");
		bookInfo.setBookAvailable(true);
		boolean status = service.addBook(bookInfo);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testAdminLogin() {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = service.adminLogin("sushma@gmail.com", "sushma");
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testAdminLoginFalse() {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = service.adminLogin("sushma123@gmail.com", "sushma123");
		Assertions.assertNull(status);
	}
	
	@Test
	public void testRemoveBook() {
//		BooksInformation bookInfo = new BooksInformation();
		boolean status = service.removeBook(121212);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRemoveBookFalse() {
//		BooksInformation bookInfo = new BooksInformation();
		boolean status = service.removeBook(121212);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testIssueBook() {
		boolean status = service.issueBook(121212, 123123);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testIssueBookFalse() {
		boolean status = service.issueBook(121212, 123123);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testUpdateBook() {
		BooksInformation bookInfo = new BooksInformation();
		bookInfo.setBookAuthor("sushma");
		boolean status = service.updateBook(1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testUpdateBookFalse() {
		BooksInformation bookInfo = new BooksInformation();
		bookInfo.setBookAuthor("sushma");
		boolean status = service.updateBook(10);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testSearchBook() {
		BooksInformation status = service.searchBook(1);
		Assertions.assertNotNull(status);
	}

	@Test
	public void testSearchBookFalse() {
		BooksInformation status = service.searchBook(11);
		Assertions.assertNull(status);
	}
	
	@Test
	public void testshowallusers() {
		List<UserInformation> users = service.showAllUsers();
		Assertions.assertNotNull(users);
	}
	
	@Test
	public void testshowallusersFalse() {
		List<UserInformation> users = service.showAllUsers();
		Assertions.assertNull(users);
	}
	
	@Test
	public void testshowallbooks() {
		List<BooksInformation> books = service.showAllBooks();
		Assertions.assertNotNull(books);
	}
	
	@Test
	public void testshowallbooksFalse() {
		List<BooksInformation> books = service.showAllBooks();
		Assertions.assertNull(books);
	}
	
	@Test
	public void testshowallrequests() {
		List<UserRequestInformation> requests = service.showAllRequests();
		Assertions.assertNotNull(requests);
	}
	
	@Test
	public void testshowallrequestsFalse() {
		List<UserRequestInformation> requests = service.showAllRequests();
		Assertions.assertNull(requests);
	}
	
	@Test
	public void isReceived() {
		boolean status = service.isBookRecevied(121212, 123123);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void isReceivedFalse() {
		boolean status = service.isBookRecevied(121212, 123123);
		Assertions.assertFalse(status);
	}

}
