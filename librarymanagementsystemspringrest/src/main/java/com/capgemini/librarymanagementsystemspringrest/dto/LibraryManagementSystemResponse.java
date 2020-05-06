package com.capgemini.librarymanagementsystemspringrest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class LibraryManagementSystemResponse {
	private boolean error;
	private String message;
	private UserInformation userInfo;
	private List<UserInformation> usersList;
	private BooksInformation bookInfo;
	private List<BooksInformation> booksList;
	private UserRequestInformation userRequestInfo;
	private List<UserRequestInformation> usersRequestList;


}
