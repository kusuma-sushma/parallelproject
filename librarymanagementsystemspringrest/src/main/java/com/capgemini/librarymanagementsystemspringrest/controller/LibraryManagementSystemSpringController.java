package com.capgemini.librarymanagementsystemspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.LibraryManagementSystemResponse;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemspringrest.service.AdminService;
import com.capgemini.librarymanagementsystemspringrest.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
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
	public LibraryManagementSystemResponse updateBook(@RequestBody BooksInformation bookInfo ) {
		boolean isUpdate =adminService.updateBook(bookInfo);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(isUpdate) {
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
		if(isDeleted) {
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
	public LibraryManagementSystemResponse adminLogin(@RequestBody String email, String password) {
		UserInformation adminLogin = adminService.adminLogin(email, password);
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
	public LibraryManagementSystemResponse userLogin(@RequestBody  String email, String password) {
		UserInformation userLogin = userService.userLogin(email, password);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(userLogin!=null) {
			response.setMessage("User Logged in Successfully");
		} else {
			response.setError(true);
			response.setMessage("User Details which was given is invalid, Unable to login");
		} 
		return response;
	}

	@GetMapping(path="/issueBook/{requestId}",
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse issueBook(@PathVariable(name = "requestId") int requestId) {
		boolean issued = adminService.issueBook(requestId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(issued) {
			response.setMessage("Book Issued to the User Successfully");
		} else {
			response.setError(true);
			response.setMessage("User or Book Details which was given is invalid, Unable to issue the book");
		} 
		return response;
	}

	@GetMapping(path="/receiveBook/{rId}",
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse receiveBook(@PathVariable(name = "requestId") int requestId) {
		boolean returned = adminService.isBookRecevied(requestId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(returned) {
			response.setMessage("Book has been collected by the user Successfully");
		} else {
			response.setError(true);
			response.setMessage("User or Book Details which was given is invalid, Unable to collect the book");
		} 
		return response;
	}

	@GetMapping(path="/borrowBook/{id}/{bookId}",
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse borrowBook(@PathVariable(name = "id") int id, @PathVariable(name = "bookId") int bookId)  {
		boolean borrow = userService.borrowBook(id, bookId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(borrow) {
			response.setMessage("Trying to borrow Book, request has been send to admin ");
		} else {
			response.setError(true);
			response.setMessage("User or Book Details which was given is invalid, Unable to borrow the book");
		} 
		return response;
	}

	@GetMapping(path="/returnBook/{id}/{bookId}",
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	@ResponseBody
	public LibraryManagementSystemResponse returnBook(@PathVariable(name = "id") int id, @PathVariable(name = "bookId") int bookId) {
		boolean returning = userService.returnBook(id, bookId);
		LibraryManagementSystemResponse response= new LibraryManagementSystemResponse();
		if(returning) {
			response.setMessage("Trying to return Book, request has been send to admin ");
		} else {
			response.setError(true);
			response.setMessage("User or Book Details which was given is invalid, Unable to return the book");
		} 
		return response;
	}

	

	


}
