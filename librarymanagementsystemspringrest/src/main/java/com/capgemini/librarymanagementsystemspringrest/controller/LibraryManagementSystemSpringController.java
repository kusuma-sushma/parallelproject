package com.capgemini.librarymanagementsystemspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.LibraryManagementSystemResponse;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemspringrest.service.AdminService;
import com.capgemini.librarymanagementsystemspringrest.service.UserService;

@RestController
public class LibraryManagementSystemSpringController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	
	@GetMapping(path ="/getBook", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
	public LibraryManagementSystemResponse getBook(int bookId) {
		BooksInformation bookInfo = adminService.searchBook(bookId);
		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		if(bookInfo!=null) {
			response.setBookInfo(bookInfo);
		} else {
			response.setError(true);
			response.setMessage("BookId which was given not present in the library");
		}
		return response;
	}
	
	@PostMapping(path="/addUser",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse addUser(@RequestBody UserInformation userInfo) {
		boolean isAdded = adminService.addUser(userInfo);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(isAdded) {
			response.setMessage("User Added Successfully");
		} else {
			response.setError(true);
			response.setMessage("User Details which was given is already present. Unable to add user");
		} 
		return response;
	}
	
	@PostMapping(path="/addBook",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse addBook(@RequestBody BooksInformation bookInfo) {
		boolean isAdded = adminService.addBook(bookInfo);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(isAdded) {
			response.setMessage("Book Added Successfully");
		} else {
			response.setError(true);
			response.setMessage("Book Details which was given is already present. Unable to add book");
		} 
		return response;
	}
	
	@GetMapping(path="/getAllUsers", produces=MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse getAllUsers() {
		List<UserInformation> userList = adminService.showAllUsers();
		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		if(userList!=null && !userList.isEmpty()) {
			response.setUsersList(userList);
		} else {
			response.setError(true);
			response.setMessage("No Users are present in the library");
		}
		return response;
	}

	@GetMapping(path="/getAllBooks", produces=MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse getAllBooks() {
		List<BooksInformation> bookList = adminService.showAllBooks();
		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		if(bookList!=null && !bookList.isEmpty()) {
			response.setBooksList(bookList);
		} else {
			response.setError(true);
			response.setMessage("No Books are present in the library");
		}
		return response;
	}
	
	@GetMapping(path="/getAllRequests", produces=MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse getAllRequests() {
		List<UserRequestInformation> userRequestList = adminService.showAllRequests();
		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		if(userRequestList!=null && !userRequestList.isEmpty()) {
			response.setUsersRequestList(userRequestList);
		} else {
			response.setError(true);
			response.setMessage("No Users Requests are present in the library");
		}
		return response;
	}
	
	@PutMapping(path="/updateBook", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryManagementSystemResponse updateBook(@RequestBody int bookId ) {
		BooksInformation isUpdate =adminService.updateBook(bookId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(isUpdate!=null) {
			response.setMessage("Book Updated successfully");
		} else {
			response.setError(true);
			response.setMessage("Unable to update Book!");
		} 
		return response;
	
	}

	@DeleteMapping(path="/deleteBook", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public LibraryManagementSystemResponse deleteBook(int bookId) {
		boolean isDeleted =adminService.removeBook(bookId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(adminService.removeBook(bookId)) {
			response.setMessage("BookId" +bookId);
			response.setMessage("Book Deleted successfully");
		} else {
			response.setError(true);
			response.setMessage("Book id which was given is not present in the library" +bookId);
		} 
		return response;
	
	}
	
	@PostMapping(path="/adminLogin",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse adminLogin(@RequestBody String userName, String password) {
		UserInformation adminLogin = adminService.adminLogin(userName, password);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(adminLogin!=null) {
			response.setMessage("Admin Logged in Successfully");
		} else {
			response.setError(true);
			response.setMessage("Admin Details which was given is invalid, Unable to login");
		} 
		return response;
	}
	
	@PostMapping(path="/userLogin",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse userLogin(@RequestBody String userName, String password) {
		UserInformation userLogin = userService.userLogin(userName, password);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(userLogin!=null) {
			response.setMessage("User Logged in Successfully");
		} else {
			response.setError(true);
			response.setMessage("User Details which was given is invalid, Unable to login");
		} 
		return response;
	}

	@PostMapping(path="/issueBook",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse issueBook(@RequestBody int userId, int bookId) {
		boolean issued = adminService.issueBook(userId, bookId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(issued) {
			response.setMessage("Book Issued to the User Successfully");
		} else {
			response.setError(true);
			response.setMessage("User or Book Details which was given is invalid, Unable to issue the book");
		} 
		return response;
	}

	@PostMapping(path="/receiveBook",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse receiveBook(@RequestBody int userId, int bookId) {
		boolean returned = adminService.isBookRecevied(userId, bookId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(returned) {
			response.setMessage("Book has been collected by the user Successfully");
		} else {
			response.setError(true);
			response.setMessage("User or Book Details which was given is invalid, Unable to collect the book");
		} 
		return response;
	}

	@PostMapping(path="/borrowBook",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse borrowBook(@RequestBody int userId, int bookId) {
		UserRequestInformation borrow = userService.borrowBook(userId, bookId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(borrow!=null) {
			response.setMessage("Trying to borrow Book, request has been send to admin ");
		} else {
			response.setError(true);
			response.setMessage("User or Book Details which was given is invalid, Unable to borrow the book");
		} 
		return response;
	}

	@PostMapping(path="/returnBook",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse returnBook(@RequestBody int userId, int bookId) {
		UserRequestInformation returning = userService.returnBook(userId, bookId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(returning!=null) {
			response.setMessage("Trying to return Book, request has been send to admin ");
		} else {
			response.setError(true);
			response.setMessage("User or Book Details which was given is invalid, Unable to return the book");
		} 
		return response;
	}

	

	


}
