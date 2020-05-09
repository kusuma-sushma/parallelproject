package com.capgemini.librarymanagementsystemspringrest.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.librarymanagementsystemspringrest.dto.LibraryManagementSystemResponse;
import com.capgemini.librarymanagementsystemspringrest.exception.LibraryManagementSystemException;

@RestControllerAdvice
public class LibraryManagementSystemControllerAdvice {

	@ExceptionHandler
	public LibraryManagementSystemResponse myExceptionHandler(LibraryManagementSystemException lmseException) {
		LibraryManagementSystemResponse response=new LibraryManagementSystemResponse();
		response.setError(true);
		response.setMessage(lmseException.getMessage());
		
		return response;
	}
}
