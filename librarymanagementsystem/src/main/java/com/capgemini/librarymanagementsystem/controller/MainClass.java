package com.capgemini.librarymanagementsystem.controller;

import com.capgemini.librarymanagementsystem.database.LibraryManagementSystemDataBase;

public class MainClass {

	public static void main(String[] args) {
		LibraryManagementSystemDataBase.database();
		System.err.println("Welcome to Library");
		LibraryManagementSystemController.controller();
		
	}


}
