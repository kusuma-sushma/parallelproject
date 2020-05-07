package com.capgemini.librarymanagementsystem.database;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.AdminInformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.dto.UserRequestInformation;

public class LibraryManagementSystemDataBase {
		
	public static final List<UserInformation> user= new ArrayList<UserInformation>();
//	//list.add(new Employee(123, "pavan", "consultant", 45000, "Hyderabad"));
//
//
//	user.add(new UserInformation(121212, "Akhil", "akhil1", "akhil@gmail.com", "civil", 0, 0.0));
//	user.add(new UserInformation(123123, "Nikhil", "nikhil", "nikhil@gmail.com", "mech", 0, 0.0));
//	user.add(new UserInformation(123456, "Padma", "padma1", "padma@gmail.com", "ece", 0, 0.0));
//	
////	for (UserInformation users :user) {
////		System.out.println(String.format("%-5s %-10s %-15s %-10s %s", users.getUserId(), users.getUsername(),
////				users.getEmail(), users.getDepartment(), users.getNoOfBooksBorrowed()));
////	}
	public static final List<AdminInformation> admin = new ArrayList<>();
    public static final List<BooksInformation> book = new ArrayList<>();
//    book.add(new BooksInformation(121212, "Networks", "Michael Steer", "ECE","North Carolina SUL"));
//    book.add(new BooksInformaion(123123, "Phython", "Mark Lutz", "CSE", "Shroff"));
//    book.add(new BooksInformaion(123456, "Surveying & Levelling ", "Basak", "CIVIL", "McGraw Hill"));
    public static final List<UserRequestInformation> requests=new ArrayList<>();
//    requests.add(new UserRequestInformation(121212,123123,0,0,false,false));
//    requests.add(new UserRequestInformation(123123,123123,0,0,false,false));
    
    public static void database() {
    	admin.add(new AdminInformation(15, "Sushma", "sushma", "sushma@gmail.com"));
    	
    	user.add(new UserInformation(121212, "Akhil", "akhil1", "akhil@gmail.com", "civil", 0, 0.0));
    	user.add(new UserInformation(123123, "Nikhil", "nikhil", "nikhil@gmail.com", "mech", 0, 0.0));
    	user.add(new UserInformation(123456, "Padma", "padma1", "padma@gmail.com", "ece", 0, 0.0));
    	
    	
    	 book.add(new BooksInformation(121212, "Networks", "ECE", "Michael Steer","North Carolina SUL", true));
    	 book.add(new BooksInformation(123123, "Phython", "CSE", "Mark Lutz", "Shroff", true));
    	 book.add(new BooksInformation(123456, "Surveying & Levelling ", "CIVIL", "Basak", "McGraw Hill", true));
    }
}