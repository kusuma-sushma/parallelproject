package com.capgemini.librarymanagementsystemjdbc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementSystemException;


public class JdbcUtility {

	private static Connection connection = null;

	public static Connection getConnection() throws LibraryManagementSystemException {
		File file = null;
		FileInputStream fileInputStream = null;
		file = new File("databaseproperties.properties");
		try {
			fileInputStream = new FileInputStream(file);

			Properties properties = new Properties();
			properties.load(fileInputStream);

			String path = properties.getProperty("path");
			String dburl = properties.getProperty("dburl");

			Class.forName(path);
			connection = DriverManager.getConnection(dburl);

			return connection;

		} catch (FileNotFoundException e) {
			throw new LibraryManagementSystemException("File Not Exists");
		} catch (IOException e) {
			throw new LibraryManagementSystemException("Unable to Read The Data From The File");
		} catch (ClassNotFoundException e) {
			throw new LibraryManagementSystemException("Class Not Loaded");
		} catch (SQLException e) {
			throw new LibraryManagementSystemException("Connection Issue");
		}

	}

}
