package com.capgemini.librarymanagementsystem.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.database.LibraryManagementSystemDataBase;
import com.capgemini.librarymanagementsystem.dto.AdminInformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystem.factory.LibraryManagementSystemFactory;

public class AdminDaoImplementation implements AdminDao {
	
	Date date=new Date();
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();
	String todayDate = dateFormat.format(calendar.getTime());
	Date actualReturnDate = calendar.getTime();
	String returnDate = dateFormat.format(actualReturnDate);
	@Override
	public AdminInformation adminLogin(String email, String password) throws LibraryManagementSystemException {
		AdminInformation adminInfo = LibraryManagementSystemFactory.getAdminInfo();
		if (adminInfo.getEmail().equals(email) && adminInfo.getPassword().equals(password)) {
			return adminInfo;
		}
		throw new LibraryManagementSystemException("Invalid Admin details");
	}

	@Override
	public boolean addUser(UserInformation userInfo) {
		for (UserInformation userBean : LibraryManagementSystemDataBase.user) {
			if (userBean.getUserId() == userInfo.getUserId()) {
				return false;
			} else if (userBean.getEmail().equals(userInfo.getEmail())) {
				return false;
			}
		}

		LibraryManagementSystemDataBase.user.add(userInfo);

		return true;

	}

	@Override
	public boolean addBook(BooksInformation bookInfo) throws LibraryManagementSystemException {
		for (BooksInformation addbook : LibraryManagementSystemDataBase.book) {
			if (addbook.getBookId() == bookInfo.getBookId()) {
				return false;
			}
		}
		LibraryManagementSystemDataBase.book.add(bookInfo);
		return true;
	}

	@Override
	public boolean removeBook(int bookId) throws LibraryManagementSystemException {
		for (BooksInformation remove : LibraryManagementSystemDataBase.book) {
			if (remove.getBookId() == bookId) {
				LibraryManagementSystemDataBase.book.remove(remove);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean issueBook(UserInformation userInfo, BooksInformation bookInfo) throws LibraryManagementSystemException {
		boolean isValid = false;
		UserRequestInformation userRequestInfo = LibraryManagementSystemFactory.userRequest();
		calendar.add(Calendar.DATE, 15);
		actualReturnDate = calendar.getTime();

		int noOfBooksBorrowed = userInfo.getNoOfBooksBorrowed();
		for (UserRequestInformation info : LibraryManagementSystemDataBase.requests) {
			if (info.getUserInfo().getUserId() == userInfo.getUserId()) {
				if (info.getBookInfo().getBookId() == bookInfo.getBookId()) {
					userRequestInfo = info;
					isValid = true;
				}
			}
		}

		if (isValid) {
			for (BooksInformation info2 : LibraryManagementSystemDataBase.book) {
				if (info2.getBookId() == bookInfo.getBookId()) {
					bookInfo = info2;
				}
			}
			for (UserInformation userInfo2 : LibraryManagementSystemDataBase.user) {
				if (userInfo2.getUserId() == userInfo.getUserId()) {
					userInfo = userInfo2;
					noOfBooksBorrowed = userInfo.getNoOfBooksBorrowed();
				}
			}
			if (noOfBooksBorrowed < 3) {
				boolean isRemoved = LibraryManagementSystemDataBase.book.remove(bookInfo);
				if (isRemoved) {
					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					userInfo.setNoOfBooksBorrowed(noOfBooksBorrowed);
					userRequestInfo.setDateOfIssued(date);
					userRequestInfo.setDateOfReturn(actualReturnDate);
					userRequestInfo.setBookIssued(true);
					return true;
				} else {
					throw new LibraryManagementSystemException("Book can not be borrowed by  the user");
				}

			} else {
				throw new LibraryManagementSystemException("User has already borrowed 3 books");
			}

		} else {
			throw new LibraryManagementSystemException(
					"User Information or Book Information is not valid hence book can not be borrowed");

		}

	}

	@Override
	public BooksInformation updateBook(int bookId) {
		for (BooksInformation bookInfo : LibraryManagementSystemDataBase.book) {
			if (bookInfo.getBookId() != bookId) {
				return null;
			}
			LibraryManagementSystemDataBase.book.add(bookInfo);
			System.err.println("updating Book");
			return bookInfo;
		}
		return null;
	}

	@Override
	public BooksInformation searchBook(int bookId) {
		for (BooksInformation bookInfo : LibraryManagementSystemDataBase.book) {
			if (bookInfo.getBookId() == bookId) {

				return bookInfo;
			}
		}
		return null;
	}

	@Override
	public List<BooksInformation> showAllBooks() {
		List<BooksInformation> booksList = new LinkedList<BooksInformation>();
		
		for (BooksInformation bookInfo : LibraryManagementSystemDataBase.book) {
			bookInfo.getBookId();
			bookInfo.getBookName();
			bookInfo.getBookAuthor();
			bookInfo.getBookCategory();
			bookInfo.getBookPublisher();
			booksList.add(bookInfo);
		}
		return booksList;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public List<UserInformation> showAllUsers() {
		List<UserInformation> userInfo = new ArrayList<UserInformation>();

		for (UserInformation userInfo1 : LibraryManagementSystemDataBase.user) {
			
//			System.out.println(String.format("%-5s %-10s %-15s %-10s %s", "USERID", "USERNAME", "USEREMAIL", "DEPARTMENT", "BOOKSBORROWED"));
////
//			for (UserInformation user : LibraryManagementSystemDataBase.user) {
//				System.out.println(String.format("%-5s %-10s %-15s %-10s %s", user.getUserId(), user.getUsername(),
//						user.getEmail(), user.getDepartment(), user.getNoOfBooksBorrowed()));
//				
//			}
//			return null;

			userInfo1.getUserId();
			userInfo1.getUsername();
			userInfo1.getEmail();
			userInfo1.getNoOfBooksBorrowed();
			userInfo.add(userInfo1);
		}
		return userInfo;
	}

	@Override
	public List<UserRequestInformation> showAllRequests() {
		List<UserRequestInformation> userRequestInfo = new LinkedList<UserRequestInformation>();
		for (UserRequestInformation requestInfo : LibraryManagementSystemDataBase.requests) {
			requestInfo.getBookInfo();
			requestInfo.getUserInfo();
			requestInfo.isBookIssued();
			requestInfo.isBookReturned();
			userRequestInfo.add(requestInfo);
		}
		return userRequestInfo;
	}

	@Override
	public boolean isBookRecevied(UserInformation userInfo, BooksInformation bookInfo) throws LibraryManagementSystemException {
		boolean isRecieved = false;
		
		UserRequestInformation userRequestInfo = LibraryManagementSystemFactory.userRequest();
		Date expectedReturnDate =userRequestInfo.getDateOfIssued();
		Date returnedDate= userRequestInfo.getDateOfReturn();

		for (UserRequestInformation requestInfo : LibraryManagementSystemDataBase.requests) {

			if (requestInfo.getBookInfo().getBookId() == bookInfo.getBookId()
					&& requestInfo.getUserInfo().getUserId() == userInfo.getUserId()
					&& requestInfo.isBookReturned() == true) {
				isRecieved = true;
				//expectedReturnDate = requestInfo.getDateOfReturn();
				//returnedDate=requestInfo.getDateOfReturn();
				userRequestInfo = requestInfo;
			}
		}
		if (isRecieved)
			try {
				{
					long expectDate=expectedReturnDate.getTime();
					long returnDate = returnedDate.getTime();
					long diff=returnDate-expectDate;
					int NoOfDays=(int)(diff/(24*60*60*1000));
					for (BooksInformation bookInfo1 :LibraryManagementSystemDataBase.book) {
						if (bookInfo1.getBookId() == bookInfo.getBookId()) {
//					//book = info2;
							LibraryManagementSystemDataBase.book.add(bookInfo1);
						}
					}
//				}
//			}
						//if (bookInfo1.getBookId()==bookInfo.getBookId()) {

//			
					
//			for (UserInformation userInfo1:LibraryManagementSystemDataBase.users) {
//				if (userInfo1.getUserId()==userInfo.getUserId()) {
//				int	noOfBooks = userInfo1.getNoOfBooksBorrowed();
//				noOfBooks--;
//				userInfo1.setNoOfBooksBorrowed(noOfBooks);
//				
//				}
//			}
//			calendar.add(Calendar.DATE, 15);
//			actualReturnDate = calendar.getTime();

					bookInfo.setBookId(userRequestInfo.getBookInfo().getBookId());
					bookInfo.setBookName(userRequestInfo.getBookInfo().getBookName());
					bookInfo.setBookAuthor(userRequestInfo.getBookInfo().getBookAuthor());
					bookInfo.setBookCategory(userRequestInfo.getBookInfo().getBookCategory());
					bookInfo.setBookPublisher(userRequestInfo.getBookInfo().getBookPublisher());
					LibraryManagementSystemDataBase.book.add(bookInfo);
					LibraryManagementSystemDataBase.requests.remove(userRequestInfo);
				//	bookInfo=bookInfo1;
//			bookInfo.setBookName(userRequestInfo.getBookInfo().getBookName());
//			LibraryManagementSystemDataBase.book.add(bookInfo);
//			LibraryManagementSystemDataBase.requests.remove(userRequestInfo);

					for (UserInformation userInfo2 : LibraryManagementSystemDataBase.user) {
						if (userInfo2.getUserId() == userInfo.getUserId()) {
							userInfo = userInfo2;
					int noOfBooksBorrowed = userInfo.getNoOfBooksBorrowed();
					noOfBooksBorrowed--;
					userInfo.setNoOfBooksBorrowed(noOfBooksBorrowed);
					Double fine=userInfo.getFine();
					if(NoOfDays>0) {
						fine=fine+(NoOfDays*1.8);
						userRequestInfo.setBookReturned(true);
					}
					userInfo.setFine(fine);
					break;
				
}
//LibraryManagementSystemDataBase.requests.remove(userRequestInfo);
					return true;

				}
					throw new LibraryManagementSystemException("Book is not able to receive by admin");
}
			} catch (LibraryManagementSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
}

	
//	private static long differenceDate(Date issuedDate, Date returnDate) {
//		long days = (returnDate.getTime() - issuedDate.getTime()) / 864000000;
//
//		return days;
//	}

}
