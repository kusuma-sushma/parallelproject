package com.capgemini.librarymanagementsystemjdbc.dao;

public interface QueryMapper {

	String adminLogin= "select * from lms_allusers where email=? and password=? and role='Admin'";
	String userLogin= "select * from lms_allusers where email=? and password=? and role='student' or 'Student'";
	String addUser= "insert into lms_allusers values(?,?,?,?,?,?)";
	String addBook= "insert into lms_books values(?,?,?,?,?)";
	String removeBook= "delete from lms_books where bookid=?";
//	String issuebook1= "delete from lms_books where bookid=? ANY (update lms_request set status=? where bookid=? ANY (insert into lms_bookallotment values(?,?,?,?,?,?,?)));
	String issueBook= "insert into lms_bookallotment values(?,?,?,?,?,?,?)";
	String updateBook1= "update lms_request set status=? where bookid=?";
	String bookAvailable = "update lms_books set bookAvailable =? where bookId = ?";
	String bookAllot ="select bookAllotmentId from lms_bookallotment where bookId=?";
	String userFine = "update lms_allusers set fine = ? where userId=?";
//	String getRequest = "select * from lms_request where requestId = ?";
	//deletebook1=delete from lms_books where bookid=?;
	String updateBook= "update lms_books set bookname=?, bookauthor=?, bookcategory=?, bookpublisher=? where bookid=?";
	String update= "update lms_books set bookname=? where bookid=?";
	String searchBook= "select * from lms_books where bookid=?";
	String showAllBooks= "select * from lms_books";
	String showAllUsers= "select * from lms_allusers";
	String showAllRequest= "select * from lms_request";
	String collectBookFromUser= "update lms_bookallotment set returndate=? and fine=? where bookid=?";
	String borrowBook= "insert into lms_request values(?,?,?,?,?)";
//	String deleteBook= "delete from lms_request where bookid=?";
//	approved=approved;
//	notapproved=not approved;
//	returned=returned;
}
