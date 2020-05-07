package com.capgemini.librarymanagementsystem.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;

public class LibraryManagementSystemValidation {

	public boolean validateById(String bookId) throws LibraryManagementSystemException {
		Pattern pattern = Pattern.compile("[\\d&&[^0]][\\d]{5}");
		Matcher matcher = pattern.matcher(bookId);
		if (matcher.matches()) {
			return true;
		} 
		return false;
	}

	public boolean validateByName(String bookName) throws LibraryManagementSystemException {
		Pattern pattern = Pattern.compile("\\w{3,30}"); //\"[A-Za-z-]*");
		Matcher matcher = pattern.matcher(bookName);
		if (matcher.matches()) {
			return true;
		} 
		return false;
	}

	public boolean validateByEmail(String email) throws LibraryManagementSystemException {
		Pattern pattern = Pattern.compile("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		} 
		return false;
	}

	public boolean validateByPassword(String password) throws LibraryManagementSystemException {
		Pattern pattern = Pattern.compile(".{6}");
			//	+ "[a-zA-Z0-9]{6}");
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

}
