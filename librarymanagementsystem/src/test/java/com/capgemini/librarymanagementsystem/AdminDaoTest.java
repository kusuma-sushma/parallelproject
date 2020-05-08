package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.AdminDao;
import com.capgemini.librarymanagementsystem.dao.AdminDaoImplementation;
import com.capgemini.librarymanagementsystem.dao.UserDao;
import com.capgemini.librarymanagementsystem.dao.UserDaoImplementation;
import com.capgemini.librarymanagementsystem.dto.AdminInformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;


//import junit.framework.TestCase;
//import junit.framework.TestSuite;

public class AdminDaoTest {
	AdminDao dao = new AdminDaoImplementation();
	UserDao userDao = new UserDaoImplementation();
	@Test
	public void testAddUser() throws LibraryManagementSystemException {
		UserInformation userInfo = new UserInformation();
		userInfo.setUserId(1);
		userInfo.setUsername("kiara");
		userInfo.setPassword("kiara");
		userInfo.setEmail("kiara@gmail.com");
		userInfo.setDepartment("ece");
		userInfo.setNoOfBooksBorrowed(0);
//		userInfo.setRole("student");
		boolean status = dao.addUser(userInfo);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testAddUserFalse() throws LibraryManagementSystemException {
		UserInformation userInfo = new UserInformation();
		userInfo.setUserId(1);
		userInfo.setUsername("kiara");
		userInfo.setPassword("kiara");
		userInfo.setEmail("kiara@gmail.com");
		userInfo.setDepartment("ece");
		userInfo.setNoOfBooksBorrowed(0);
//		userInfo.setRole("student");
		boolean status = dao.addUser(userInfo);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testAddBook() throws LibraryManagementSystemException {
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
	public void testAddBookFalse() throws LibraryManagementSystemException {
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
		AdminInformation status = dao.adminLogin("sushma@gmail.com", "sushma");
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testAdminLoginFalse() {
//		UserInformation userInfo = new UserInformation();
		AdminInformation status = dao.adminLogin("sushma123@gmail.com", "sushma123");
		Assertions.assertNull(status);
	}
	
	@Test
	public void testRemoveBook() throws LibraryManagementSystemException {
//		BooksInformation bookInfo = new BooksInformation();
		boolean status = dao.removeBook(123123);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRemoveBookFalse() throws LibraryManagementSystemException {
//		BooksInformation bookInfo = new BooksInformation();
		boolean status = dao.removeBook(123123);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testIssueBook() throws LibraryManagementSystemException {
		boolean status = dao.issueBook(121212, 123456);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testIssueBookFalse() throws LibraryManagementSystemException {
		boolean status = dao.issueBook(121212, 123456);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testUpdateBook() {
		BooksInformation bookInfo = new BooksInformation();
		bookInfo.setBookAuthor("sushma");
		BooksInformation status = dao.updateBook(1);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testUpdateBookFalse() {
		BooksInformation bookInfo = new BooksInformation();
		bookInfo.setBookAuthor("sushma");
		BooksInformation status = dao.updateBook(10);
		Assertions.assertNull(status);
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
	public void isReceived() throws LibraryManagementSystemException {
		boolean status = dao.isBookRecevied(121212, 123456);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void isReceivedFalse() throws LibraryManagementSystemException {
		boolean status = dao.isBookRecevied(121212, 123456);
		Assertions.assertFalse(status);
	}
	
//UserDao dao = new UserDaoImplementation();
	
	@Test
	public void testUserLogin() throws LibraryManagementSystemException {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = userDao.userLogin("akhil@gmail.com", "akhil1");
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testUserLoginFalse() throws LibraryManagementSystemException {
//		UserInformation userInfo = new UserInformation();
		UserInformation status = userDao.userLogin("akhil12@gmail.com", "akhil12");
		Assertions.assertNull(status);
	}
	
	@Test
	public void testBorrowBook() throws LibraryManagementSystemException {
		UserRequestInformation status = userDao.borrowBook(121212, 123456);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testBorrowBookFalse() throws LibraryManagementSystemException {
		UserRequestInformation status = userDao.borrowBook(121212, 123456);
		Assertions.assertNull(status);
	}
	
	@Test
	public void testReturnBook() throws LibraryManagementSystemException {
		UserRequestInformation status = userDao.returnBook(121212, 123456);
		Assertions.assertNotNull(status);
	}
	
	@Test
	public void testReturnBookFalse() throws LibraryManagementSystemException {
		UserRequestInformation status = userDao.returnBook(121212, 123456);
		Assertions.assertNull(status);
	}

	}

