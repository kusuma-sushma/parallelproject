package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserInformation;
import com.capgemini.librarymanagementsystemjdbc.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class UserDaoImplementation implements UserDao {

	@Override
	public UserInformation userLogin(String email, String password) {

		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.userLogin)) {
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);
					try (ResultSet result = preparedStatement.executeQuery()) {
						if (result.next()) {
							UserInformation userInfo = new UserInformation();
							userInfo.setEmail(result.getString("email"));
							userInfo.setPassword(result.getString("password"));
					//		userInfo.setRole(result.getString("role"));
							return userInfo;
						} else {
							throw new LibraryManagementSystemException("Invalid user details");
						}
					} catch (LibraryManagementSystemException lmse) {
						System.err.println(lmse.getMessage());
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public UserRequestInformation borrowBook(int userId, int bookId) {
		BooksInformation bookInfo = new BooksInformation();
		UserInformation userInfo = new UserInformation();
		UserRequestInformation userRequestInfo = new UserRequestInformation();
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.borrowBook)) {
					preparedStatement.setInt(1, bookInfo.getBookId());
					preparedStatement.setString(2, bookInfo.getBookName());
					preparedStatement.setInt(3, userInfo.getUserId());
					preparedStatement.setString(4, userInfo.getUsername());
					preparedStatement.setString(5, userRequestInfo.setStatus("not approved"));
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
						return userRequestInfo;
					} else {
						throw new LibraryManagementSystemException("credentials are not matching unale to borrow book");
					}
				} catch (LibraryManagementSystemException lmse) {
					System.err.println(lmse.getMessage());
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return null;

	}

	@Override
	public UserRequestInformation returnBook(int userId, int bookId) {
		UserRequestInformation userRequestInfo = new UserRequestInformation();
		BooksInformation bookInfo = new BooksInformation();
		UserInformation userInfo = new UserInformation();
		
		try (Connection connection = JdbcUtility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.updateBook1)) {
							preparedStatement.setString(1, userRequestInfo.setStatus("returning"));
					preparedStatement.setInt(2, bookInfo.getBookId());
//					userRequestInfo.setBookInfo(bookInfo);
//					userRequestInfo.setUserInfo(userInfo);
					int count = preparedStatement.executeUpdate();
					if (count != 0) {
					//	userRequestInfo.setBookReturned(true);
						return userRequestInfo;
					} else {
						throw new LibraryManagementSystemException("credentials are not matching unable to return book");
					}
				} catch (LibraryManagementSystemException lmse) {
					System.err.println(lmse.getMessage());
		} catch (Exception e) {
			System.err.println("invalid user or book credentials");
		}
		return null;

	}

	
}