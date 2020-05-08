package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.dto.BookAllotment;
import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryManagementSystemFactory;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class AdminDaoImplementation implements AdminDao {

	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = Calendar.getInstance();
	String todayDate = dateFormat.format(calendar.getTime());
	Date actualReturnDate;
	Date returnDate;
//	Date actualReturnDate = calendar.getTime();
//	String returnDate = dateFormat.format(actualReturnDate);

	@Override
	public UserInformation adminLogin(String email, String password) {
		
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.adminLogin)) {
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);
					try (ResultSet result = preparedStatement.executeQuery()) {
						if (result.next()) {
							UserInformation adminInfo = new UserInformation();
							adminInfo.setEmail(email);
							adminInfo.setPassword(password);
					//		adminInfo.setRole(result.getString("role"));
							return adminInfo;
						} else {
							throw new LibraryManagementSystemException("Invalid admin details");
						}
				} catch (LibraryManagementSystemException lmse) {
					System.err.println(lmse.getMessage());
				}
		return null;
	}

	@Override
	public boolean addUser(UserInformation user) {
		
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.addUser)) {
					preparedStatement.setInt(1, user.getUserId());
					preparedStatement.setString(2, user.getUsername());
					preparedStatement.setString(3, user.getEmail());
					preparedStatement.setString(4, user.getPassword());
					preparedStatement.setString(5, user.getDepartment());
					preparedStatement.setString(6, user.getRole());
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return true;
					}
		} catch (Exception e) {
			System.err.println("Unable to add the User, user already exists");
		}
		return false;

	}

	@Override
	public boolean addBook(BooksInformation info) {
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.addBook)) {
					preparedStatement.setInt(1, info.getBookId());
					preparedStatement.setString(2, info.getBookName());
					preparedStatement.setString(3, info.getBookCategory());
					preparedStatement.setString(4, info.getBookAuthor());
					preparedStatement.setString(5, info.getBookPublisher());
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return true;
					}

		} catch (Exception e) {
			System.err.println("Unable to add the Book, book already exists");
		}
		return false;

	}

	@Override
	public boolean removeBook(int bookid) {
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.removeBook)) {
							preparedStatement.setInt(1, bookid);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return true;
					}
		} catch (Exception e) {
			System.err.println("Unable to delete the Book, book is already removed");
		}
		return false;

	}

	@Override
	public boolean issueBook(int userId, int bookId) {
		UserInformation userInfo = LibraryManagementSystemFactory.getUserInfo();
		BooksInformation bookInfo = LibraryManagementSystemFactory.getBookInfo();
		UserRequestInformation userRequestInfo = new UserRequestInformation();
		BookAllotment bookAllot = new BookAllotment();
		int noOfBooksBorrowed = userInfo.getNoOfBooks();

		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement issueBook = connection.prepareStatement(QueryMapper.issueBook);
				PreparedStatement updateBook = connection.prepareStatement(QueryMapper.updateBook1);
						PreparedStatement bookAvailable = connection.prepareStatement(QueryMapper.bookAvailable)) { 
//			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
//				try (PreparedStatement preparedStatement = connection
//						.prepareStatement(properties.getProperty("issuebook"))) {
//					try (PreparedStatement preparedStatement1 = connection
//							.prepareStatement(properties.getProperty("updatebook1"))) {
//						try (PreparedStatement preparedStatement2 = connection
//								.prepareStatement(properties.getProperty("removebook"))) {
//							if (userRequestInfo.getUserInfo().getUserId() == userInfo.getUserId()) {
//								if (userRequestInfo.getBookInfo().getBookId() == bookInfo.getBookId()) {
					if (noOfBooksBorrowed < 3) {
						Savepoint savepoint = connection.setSavepoint();
						issueBook.setInt(1, bookInfo.getBookId());
						issueBook.setString(2, bookInfo.getBookName());
						issueBook.setInt(3, userInfo.getUserId());
						issueBook.setString(4, userInfo.getUsername());
						issueBook.setString(5, todayDate);
//						calendar.add(Calendar.DAY_OF_MONTH, 7);
//						Date date = calendar.getTime();
//						Date actualReturnDate = calendar.getTime();
	//					String returnDate = dateFormat.format(actualReturnDate);
						issueBook.setDate(6, bookAllot.getReturnDate());
						issueBook.setDouble(7, userInfo.getFine());
						int count = issueBook.executeUpdate();
						updateBook.setString(1, userRequestInfo.setStatus("approved"));
						updateBook.setInt(2, bookId);
						int count1=updateBook.executeUpdate();
						bookAvailable.setBoolean(1, false);
						bookAvailable.setInt(2, bookId);
						
						int count2=bookAvailable.executeUpdate();
							if (count != 0 && count1 != 0 && count2 != 0) {
								noOfBooksBorrowed++;
								userInfo.setNoOfBooks(noOfBooksBorrowed);
								userRequestInfo.setBookIssued(true);
								return true;
							} else {
								connection.rollback(savepoint);
								throw new LibraryManagementSystemException("Book is not available in library");
							}
						} else {
							throw new LibraryManagementSystemException("Student has already borrowed 3 books");
//						}  else {
//							throw new LibraryManagementSystemException("credentials are not matching");
//						}
//						}
//						} else {
//							throw new LibraryManagementSystemException("credentials are not matching");
//						}
//						}
//					}
//								}
//					} 
//				} catch (LibraryManagementSystemException lmse) {
//				
							//System.err.println(lmse.getMessage());
						}
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
			//e.printStackTrace();
		}
		return false;

	}

	
	
//	private static long differenceDate(Date issuedDate, Date returnDate) {
//		long days = (returnDate.getTime() - issuedDate.getTime()) / 864000000;
//
//		return days;
//	}

	@Override
	public boolean updateBook(int bookId) throws LibraryManagementSystemException {
		List<BooksInformation> listBook = new LinkedList<BooksInformation>();
		
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.updateBook)) {
		BooksInformation bookInfo = new BooksInformation();
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
//
//			Class.forName(properties.getProperty("path")).newInstance();
//
//			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
//				try (PreparedStatement preparedStatement = connection
//						.prepareStatement(properties.getProperty("updatebook"))) {
					preparedStatement.setString(1, bookInfo.getBookName());
					preparedStatement.setString(2, bookInfo.getBookAuthor());
					preparedStatement.setString(3, bookInfo.getBookCategory());
					preparedStatement.setString(4, bookInfo.getBookPublisher());
					preparedStatement.setInt(5, bookInfo.getBookId());
					 listBook.add(bookInfo);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
//						 listBook.add(bookInfo);
//						 System.out.println("sssssssssssss");
						return true;
					} 
//					else {
//						throw new LibraryManagementSystemException("Book Id which was is not present in the library");
//					}
//				} catch (LibraryManagementSystemException lmse) {
//					System.err.println(lmse.getMessage());
//				}
//			}

		} catch (Exception e) {
			System.err.println("invalid user or book credentials exception");
			e.printStackTrace();
			throw new LibraryManagementSystemException("Book Id which was is not present in the library");
		}
		return false;

	}

	@Override
	public BooksInformation searchBook(int bookId) {
		BooksInformation bookInfo = new BooksInformation();
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.searchBook)) {
						preparedStatement.setInt(1, bookId);
					ResultSet result = preparedStatement.executeQuery();
					if (result.next()) {
						bookInfo.setBookId(result.getInt("bookid"));
						bookInfo.setBookName(result.getString("bookname"));
						bookInfo.setBookAuthor(result.getString("bookauthor"));
						bookInfo.setBookCategory(result.getString("bookcategory"));
						bookInfo.setBookPublisher(result.getString("bookpublisher"));
						return bookInfo;
					} else {
						throw new LibraryManagementSystemException("No such book found for id which is given");
					}

		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return null;

	}

	@Override
	public List<BooksInformation> showAllBooks() {
		try (Connection connection = JdbcUtility.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QueryMapper.showAllBooks)) {
								List<BooksInformation> bookInfo = new ArrayList<BooksInformation>();
						while (resultSet.next()) {
							BooksInformation book = new BooksInformation();
							book.setBookId(resultSet.getInt("bookid"));
							book.setBookName(resultSet.getString("bookname"));
							book.setBookAuthor(resultSet.getString("bookauthor"));
							book.setBookCategory(resultSet.getString("bookcategory"));
							book.setBookPublisher(resultSet.getString("bookpublisher"));
							book.setBookAvailable(resultSet.getBoolean("bookAvailable"));
							bookInfo.add(book);
						if (bookInfo.isEmpty()) {
							throw new LibraryManagementSystemException("no books are present in the library");
						}
						return bookInfo;
					}
		} catch (Exception e) {
			System.err.println("invalid book credentials");
		}
		return null;

	}

	@Override
	public List<UserInformation> showAllUsers() {
		try (Connection connection = JdbcUtility.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QueryMapper.showAllUsers)) {
								List<UserInformation> userInfo = new LinkedList<UserInformation>();
						while (resultSet.next()) {
							UserInformation user = new UserInformation();
							user.setUserId(resultSet.getInt("id"));
							user.setUsername(resultSet.getString("username"));
							user.setEmail(resultSet.getString("email"));
							user.setPassword(resultSet.getString("password"));
							user.setDepartment(resultSet.getString("department"));
							user.setRole(resultSet.getString("role"));
							userInfo.add(user);
							if (userInfo.isEmpty()) {
								throw new LibraryManagementSystemException("no users are present in the library");
						}
						return userInfo;
			}
		} catch (Exception e) {
			System.err.println("invalid user credentials");
		}
		return null;

	}

	@Override
	public List<UserRequestInformation> showAllRequests() {
		try (Connection connection = JdbcUtility.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QueryMapper.showAllRequest)) {
								List<UserRequestInformation> userRequestInfo = new LinkedList<UserRequestInformation>();
						while (resultSet.next()) {
							UserRequestInformation userRequest = new UserRequestInformation();
							userRequest.setBookId(resultSet.getInt("bookid"));
							userRequest.setBookName(resultSet.getString("bookname"));
							userRequest.setUserId(resultSet.getInt("userid"));
							userRequest.setUsername(resultSet.getString("username"));
							userRequest.setStatus(resultSet.getString("status"));
							userRequestInfo.add(userRequest);
							if (userRequestInfo.isEmpty()) {
								throw new LibraryManagementSystemException("no users are present in the library");
						}
						return userRequestInfo;
					}
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return null;

	}

	@Override
	public boolean isBookRecevied(int userId, int bookId) {
		int noOfDays=0;
		double fine=0;
		BookAllotment book = new BookAllotment();
		UserInformation userInfo = new UserInformation();
		UserRequestInformation userRequestInfo = new UserRequestInformation();
		List<BooksInformation> bookList = new LinkedList<BooksInformation>();
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement bookAllotment = connection.prepareStatement(QueryMapper.bookAllot);
				PreparedStatement userFine = connection.prepareStatement(QueryMapper.userFine);
				PreparedStatement receive = connection.prepareStatement(QueryMapper.collectBookFromUser);
				PreparedStatement update = connection.prepareStatement(QueryMapper.updateBook1);
				PreparedStatement bookAvaiable = connection.prepareStatement(QueryMapper.bookAvailable)) {
				bookAllotment.setInt(1, bookId);
				try (ResultSet resultSet = bookAllotment.executeQuery()) {
					int bookAllotId = resultSet.getInt("bookAllotmentId");
					Savepoint savepoint = connection.setSavepoint();
//					Date issuedDate = bookInfo.getIssuedDate("issuedate");
//					calendar.add(Calendar.DAY_OF_MONTH, 7);
//					Date date = calendar.getTime();
//					Date actualReturnDate = calendar.getTime();
//					String returnDate = dateFormat.format(actualReturnDate);
			//		preparedStatement.setString(6, returnDate);
//					Date returnDate = new Date();
//					receive.setDate(1, todayDate);
					if (book.getBookAllotmentId()==bookAllotId) {
					actualReturnDate = book.getExpectedReturnDate();
					returnDate = book.getReturnDate();
					long expectDate = actualReturnDate.getTime();
					long returnedDate = returnDate.getTime();
					long diff=expectDate-returnedDate;
					
					int NoOfDays=(int)(diff/(24*60*60*1000));
					if (NoOfDays > 0) {
						fine = fine + (NoOfDays * 1.8);
						userFine.setDouble(1, fine);
						userFine.setInt(2, userId);
						userFine.executeQuery();
					}
//					calendar.add(Calendar.DAY_OF_MONTH, 7);
	//				Date date = calendar.getTime();
//					Date actualReturnDate = calendar.getTime();
//					String returnDate = dateFormat.format(actualReturnDate);

			//		preparedStatement.setString(6, date);
//					long noOfDays = (issuedDate,actualReturnDate);
							//differenceDate(issuedDate, date);
//					System.out.println(noOfDays);
//					int noOfDaysint = (int) noOfDays;
//					System.out.println(noOfDaysint);
//					if (actualReturnDate) {
						fine = 0;
//					} else {
						fine = noOfDays * 1.8;
//					}
						receive.setString(1, todayDate);
						receive.setDouble(2, fine);
						receive.setInt(3, bookId);
					int noOfBooksBorrowed = userInfo.getNoOfBooks();
					noOfBooksBorrowed--;
					userInfo.setNoOfBooks(noOfBooksBorrowed);
//					bookList.add(bookInfo);
					update.setString(1, "returned");
					update.setInt(2, bookId);
					update.executeUpdate(); 
					bookAvaiable.setBoolean(1, true);
					bookAvaiable.setInt(2, bookId);
					bookAvaiable.executeUpdate();
//					preparedStatement1.setInt(1, bookInfo.getBookId());
//					Savepoint savepoint = connection.setSavepoint();
					//preparedStatement1.setInt(2, bookInfo.getBookId());
					//bookInfo = AdminDaoImplementation.updateBook2(bookInfo.getBookId());
//					int count = preparedStatement.executeUpdate();
//					int count1=preparedStatement1.executeUpdate();
//					if (count != 0&&count1!=0) {
//							return true;
				} else {
					connection.rollback(savepoint);	
				}
		} 
		}catch (Exception e) {
			System.err.println("invalid user or book credentials book cannot be returned");
			e.printStackTrace();
		}
		return false;

	}

	public static boolean deleteBook1(int bookId) {

		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {

				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("deletebook1"))) {
					preparedStatement.setInt(1, bookId);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return true;
					} else {
						throw new LibraryManagementSystemException("credentials are not matching");
					}
				} catch (LibraryManagementSystemException lmse) {
					System.err.println(lmse.getMessage());
				}

			}
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return false;

	}

	public static BooksInformation updateBook1(int bookId) {
		BooksInformation bookInfo = new BooksInformation();
		UserRequestInformation userRequestInfo = new UserRequestInformation();
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {

				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("updatebook1"))) {
					preparedStatement.setString(1, userRequestInfo.setStatus("approved"));
					preparedStatement.setInt(2, bookId);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return bookInfo;
					} else {
						throw new LibraryManagementSystemException("credentials are not matching");
					}
				} catch (LibraryManagementSystemException lmse) {
					System.err.println(lmse.getMessage());
				}

			}
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return null;

	}

	public static BooksInformation updateBook2(int bookId) {
		BooksInformation bookInfo = new BooksInformation();
		UserRequestInformation userRequestInfo = new UserRequestInformation();
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {

				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("updatebook1"))) {
					preparedStatement.setString(1, userRequestInfo.setStatus("returned"));
					preparedStatement.setInt(2, bookId);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return bookInfo;
					} else {
						throw new LibraryManagementSystemException("credentials are not matching");
					}
				} catch (LibraryManagementSystemException lmse) {
					System.err.println(lmse.getMessage());
				}

			}
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
			;
		}
		return null;

	}

	public static boolean deleteBook2(int bookId) {

		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {

				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("deletebook"))) {
					preparedStatement.setInt(1, bookId);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return true;
					} else {
						throw new LibraryManagementSystemException("credentials are not matching");
					}
				} catch (LibraryManagementSystemException lmse) {
					System.err.println(lmse.getMessage());
				}

			}
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
			
		}
		return false;

	}

}
