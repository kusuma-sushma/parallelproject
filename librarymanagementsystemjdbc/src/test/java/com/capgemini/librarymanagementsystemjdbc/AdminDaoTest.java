package com.capgemini.librarymanagementsystemjdbc;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDao;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminDaoImplementation;
import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementSystemException;

import org.junit.jupiter.api.Assertions;

public class AdminDaoTest {
	AdminDao dao = new AdminDaoImplementation();
	@Test
	public void testAddUser() {
		UserInformation userInfo = new UserInformation();
		userInfo.setUserId(1);
		userInfo.setUsername("kiara");
		userInfo.setPassword("kiara");
		userInfo.setEmail("kiara@gmail.com");
		userInfo.setDepartment("ece");
		userInfo.setNoOfBooks(0);
		userInfo.setRole("student");
		boolean status = dao.addUser(userInfo);
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
		userInfo.setNoOfBooks(0);
		userInfo.setRole("student");
		boolean status = dao.addUser(userInfo);
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
		boolean status = dao.addBook(bookInfo);
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
		boolean status = dao.addBook(bookInfo);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testAdminLogin() {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = dao.adminLogin("sushma@gmail.com", "sushma");
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testAdminLoginFalse() {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = dao.adminLogin("sushma123@gmail.com", "sushma123");
		Assertions.assertNull(status);
	}
	
	@Test
	public void testRemoveBook() {
//		BooksInformation bookInfo = new BooksInformation();
		boolean status = dao.removeBook(1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRemoveBookFalse() {
//		BooksInformation bookInfo = new BooksInformation();
		boolean status = dao.removeBook(1);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testIssueBook() {
		boolean status = dao.issueBook(121212, 12356);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testIssueBookFalse() {
		boolean status = dao.issueBook(121212, 123456);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testUpdateBook() throws LibraryManagementSystemException {
		BooksInformation bookInfo = new BooksInformation();
		bookInfo.setBookAuthor("sushma");
		boolean status = dao.updateBook(1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testUpdateBookFalse() throws LibraryManagementSystemException {
		BooksInformation bookInfo = new BooksInformation();
		bookInfo.setBookAuthor("sushma");
		boolean status = dao.updateBook(10);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testSearchBook() {
		BooksInformation status = dao.searchBook(1);
		Assertions.assertNotNull(status);
	}

	@Test
	public void testSearchBookFalse() {
		BooksInformation status = dao.searchBook(11);
		Assertions.assertNull(status);
	}
	
	@Test
	public void testshowallusers() {
		List<UserInformation> users = dao.showAllUsers();
		Assertions.assertNotNull(users);
	}
	
	@Test
	public void testshowallusersFalse() {
		List<UserInformation> users = dao.showAllUsers();
		Assertions.assertNull(users);
	}
	
	@Test
	public void testshowallbooks() {
		List<BooksInformation> books = dao.showAllBooks();
		Assertions.assertNotNull(books);
	}
	
	@Test
	public void testshowallbooksFalse() {
		List<BooksInformation> books = dao.showAllBooks();
		Assertions.assertNull(books);
	}
	
	@Test
	public void testshowallrequests() {
		List<UserRequestInformation> requests = dao.showAllRequests();
		Assertions.assertNotNull(requests);
	}
	
	@Test
	public void testshowallrequestsFalse() {
		List<UserRequestInformation> requests = dao.showAllRequests();
		Assertions.assertNull(requests);
	}
	
	@Test
	public void isReceived() {
		boolean status = dao.isBookRecevied(121212, 123456);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void isReceivedFalse() {
		boolean status = dao.isBookRecevied(121212, 123456);
		Assertions.assertFalse(status);
	}
	}

