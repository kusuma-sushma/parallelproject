package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.dto.AdminInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementSystemException;

public class AdminDaoImplementation implements AdminDao {

	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = Calendar.getInstance();
	String todayDate = dateFormat.format(calendar.getTime());
	
//	Date actualReturnDate = calendar.getTime();
//	String returnDate = dateFormat.format(actualReturnDate);

	@Override
	public AdminInformation adminLogin(String email, String password) {

		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("login1"))) {
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);
					try (ResultSet result = preparedStatement.executeQuery()) {
						if (result.next()) {
							AdminInformation adminInfo = new AdminInformation();
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
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public boolean addUser(UserInformation user) {
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("adduser"))) {
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
				}
			}

		} catch (Exception e) {
			System.err.println("User id already exists");
		}
		return false;

	}

	@Override
	public boolean addBook(BooksInformation info) {
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);
			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("addbook"))) {
					preparedStatement.setInt(1, info.getBookId());
					preparedStatement.setString(2, info.getBookName());
					preparedStatement.setString(3, info.getBookCategory());
					preparedStatement.setString(4, info.getBookAuthor());
					preparedStatement.setString(5, info.getBookPublisher());
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return true;
					}
				}
			}

		} catch (Exception e) {
			System.err.println("Book already exists");
		}
		return false;

	}

	@Override
	public boolean removeBook(int bookid) {
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("removebook"))) {
					preparedStatement.setInt(1, bookid);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return true;
					}
				}
			}

		} catch (Exception e) {
			System.err.println("Book already removed");
		}
		return false;

	}

	@Override
	public boolean issueBook(UserInformation userInfo, BooksInformation bookInfo) {
		UserRequestInformation userRequestInfo = new UserRequestInformation();
		int noOfBooksBorrowed = userInfo.getNoOfBooks();

		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("issuebook"))) {
					try (PreparedStatement preparedStatement1 = connection
							.prepareStatement(properties.getProperty("updatebook1"))) {
						try (PreparedStatement preparedStatement2 = connection
								.prepareStatement(properties.getProperty("removebook"))) {
							if (userRequestInfo.getUserInfo().getUserId() == userInfo.getUserId()) {
								if (userRequestInfo.getBookInfo().getBookId() == bookInfo.getBookId()) {
					if (noOfBooksBorrowed < 3) {
						Savepoint savepoint = connection.setSavepoint();
						preparedStatement.setInt(1, bookInfo.getBookId());
						preparedStatement.setString(2, bookInfo.getBookName());
						preparedStatement.setInt(3, userInfo.getUserId());
						preparedStatement.setString(4, userInfo.getUsername());
						preparedStatement.setString(5, todayDate);
//						calendar.add(Calendar.DAY_OF_MONTH, 7);
//						Date date = calendar.getTime();
//						Date actualReturnDate = calendar.getTime();
	//					String returnDate = dateFormat.format(actualReturnDate);
						preparedStatement.setDate(6, bookInfo.getReturnDate());
						preparedStatement.setDouble(7, bookInfo.getFine());
						preparedStatement1.setString(1, userRequestInfo.setStatus("approved"));
						preparedStatement1.setInt(2, bookInfo.getBookId());
						preparedStatement2.setInt(1, bookInfo.getBookId());
						int count = preparedStatement.executeUpdate();
						int count1=preparedStatement1.executeUpdate(); 
						int count2=preparedStatement2.executeUpdate();
							if (count != 0 && count1 != 0 && count2 != 0) {
								noOfBooksBorrowed++;
								userInfo.setNoOfBooks(noOfBooksBorrowed);
								userRequestInfo.setBookIssued(true);
								return true;
							} else {
								connection.rollback(savepoint);
								throw new LibraryManagementSystemException("Not Updates");
							}
						} else {
							throw new LibraryManagementSystemException("Student has already borrowed 3 books");
						}
						}
						} else {
							throw new LibraryManagementSystemException("credentials are not matching");
						}
						}
					}
								}
					} 
				} catch (LibraryManagementSystemException lmse) {
					//System.err.println(lmse.getMessage());

		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
			//e.printStackTrace();
		}
		return false;

	}

	
	
	private static long differenceDate(Date issuedDate, Date returnDate) {
		long days = (returnDate.getTime() - issuedDate.getTime()) / 864000000;

		return days;
	}

	@Override
	public boolean updateBook(BooksInformation bookInfo) {
		List<BooksInformation> listBook = new LinkedList<BooksInformation>();
//		BooksInformation bookInfo = new BooksInformation();
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("updatebook"))) {
					preparedStatement.setString(1, bookInfo.getBookName());
					preparedStatement.setString(2, bookInfo.getBookAuthor());
					preparedStatement.setString(3, bookInfo.getBookCategory());
					preparedStatement.setString(4, bookInfo.getBookPublisher());
					preparedStatement.setInt(5, bookInfo.getBookId());
					 listBook.add(bookInfo);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
//						 listBook.add(bookInfo);
						 System.out.println("sssssssssssss");
						return true;
					} else {
						throw new LibraryManagementSystemException("invalid user or book lmscredentials");
					}
				} catch (LibraryManagementSystemException lmse) {
					System.err.println(lmse.getMessage());
				}
			}

		} catch (Exception e) {
			System.err.println("invalid user or book credentials exception");
			e.printStackTrace();
			
		}
		return false;

	}

	@Override
	public BooksInformation searchBook(int bookId) {
		BooksInformation bookInfo = new BooksInformation();
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("searchbook"))) {
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
						System.out.println("No such book found");
						return null;
					}
				}
			}

		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return null;

	}

	@Override
	public List<BooksInformation> showAllBooks() {
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (Statement statement = connection.createStatement()) {
					try (ResultSet result = statement.executeQuery(properties.getProperty("showallbooks"))) {
						List<BooksInformation> bookInfo = new LinkedList<BooksInformation>();
						while (result.next()) {
							BooksInformation book = new BooksInformation();
							book.setBookId(result.getInt("bookid"));
							book.setBookName(result.getString("bookname"));
							book.setBookAuthor(result.getString("bookauthor"));
							book.setBookCategory(result.getString("bookcategory"));
							book.setBookPublisher(result.getString("bookpublisher"));
							bookInfo.add(book);
						}
						return bookInfo;
					}
				}
			}
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return null;

	}

	@Override
	public List<UserInformation> showAllUsers() {
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (Statement statement = connection.createStatement()) {
					try (ResultSet result = statement.executeQuery(properties.getProperty("showallusers"))) {
						List<UserInformation> userInfo = new LinkedList<UserInformation>();
						while (result.next()) {
							UserInformation user = new UserInformation();
							user.setUserId(result.getInt("id"));
							user.setUsername(result.getString("username"));
							user.setEmail(result.getString("email"));
							user.setPassword(result.getString("password"));
							user.setDepartment(result.getString("department"));
							user.setRole(result.getString("role"));
							userInfo.add(user);
						}
						return userInfo;
					}
				}
			}
		} catch (Exception e) {
			System.err.println("invalid user credentials");
		}
		return null;

	}

	@Override
	public List<UserRequestInformation> showAllRequests() {
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (Statement statement = connection.createStatement()) {
					try (ResultSet result = statement.executeQuery(properties.getProperty("showallrequest"))) {
						List<UserRequestInformation> userRequestInfo = new LinkedList<UserRequestInformation>();
						while (result.next()) {
							UserRequestInformation userRequest = new UserRequestInformation();
							userRequest.setBookId(result.getInt("bookid"));
							userRequest.setBookName(result.getString("bookname"));
							userRequest.setUserId(result.getInt("userid"));
							userRequest.setUsername(result.getString("username"));
							userRequest.setStatus(result.getString("status"));
							userRequestInfo.add(userRequest);
						}
						return userRequestInfo;
					}

				}
			}
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return null;

	}

	@Override
	public boolean isBookRecevied(UserInformation userInfo, BooksInformation bookInfo) {
		int noOfDays=0;
		double fine=0;
		
		UserRequestInformation userRequestInfo = new UserRequestInformation();
		List<BooksInformation> bookList = new LinkedList<BooksInformation>();
		double finePrice = 0;
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);

			Class.forName(properties.getProperty("path")).newInstance();

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement(properties.getProperty("collectbookfromuser"))) {
//					try (PreparedStatement preparedStatement1 = connection
//							.prepareStatement(properties.getProperty("updatebook1"))) {
						try (PreparedStatement preparedStatement1 = connection
								.prepareStatement(properties.getProperty("deletebook"))) {
//							try (PreparedStatement preparedStatement3= connection
//									.prepareStatement(properties.getProperty("collectbookfromuser"))) {
					
					Date issuedDate = bookInfo.getIssuedDate("issuedate");
//					calendar.add(Calendar.DAY_OF_MONTH, 7);
//					Date date = calendar.getTime();
//					Date actualReturnDate = calendar.getTime();
//					String returnDate = dateFormat.format(actualReturnDate);
			//		preparedStatement.setString(6, returnDate);
//					Date returnDate = new Date();
					preparedStatement.setString(1, todayDate);
					calendar.add(Calendar.DAY_OF_MONTH, 7);
	//				Date date = calendar.getTime();
					Date actualReturnDate = calendar.getTime();
					String returnDate = dateFormat.format(actualReturnDate);

			//		preparedStatement.setString(6, date);
					long noOfDays = (issuedDate.-date.getDate());
							//differenceDate(issuedDate, date);
					System.out.println(noOfDays);
					int noOfDaysint = (int) noOfDays;
					System.out.println(noOfDaysint);
					if (actualReturnDate) {
						finePrice = 0;
					} else {
						finePrice = noOfDays * 1.8;
					}
					preparedStatement.setDouble(2, finePrice);
					preparedStatement.setInt(3, bookInfo.getBookId());
					int noOfBooksBorrowed = userInfo.getNoOfBooks();
					noOfBooksBorrowed--;
					userInfo.setNoOfBooks(noOfBooksBorrowed);
					bookList.add(bookInfo);
					preparedStatement1.setInt(1, bookInfo.getBookId());
					Savepoint savepoint = connection.setSavepoint();
					//preparedStatement1.setInt(2, bookInfo.getBookId());
					//bookInfo = AdminDaoImplementation.updateBook2(bookInfo.getBookId());
					int count = preparedStatement.executeUpdate();
					int count1=preparedStatement1.executeUpdate();
					if (count != 0&&count1!=0) {
							return true;
					}  else {
						connection.rollback(savepoint);
				}
			}
				}
			}

		} catch (Exception e) {
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
