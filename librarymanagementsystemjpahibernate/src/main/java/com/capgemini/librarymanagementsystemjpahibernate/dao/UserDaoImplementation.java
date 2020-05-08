package com.capgemini.librarymanagementsystemjpahibernate.dao;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemjpahibernate.dto.BooksInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserInformation;
import com.capgemini.librarymanagementsystemjpahibernate.dto.UserRequestInformation;


public class UserDaoImplementation implements UserDao {

	private final static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("hibernatedb");

	public UserInformation userLogin(String email, String password) {
//		EntityManagerFactory factory =null;
		EntityManager manager = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
//		factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
		manager = factory.createEntityManager();
		String jpql = "select m from UserInformation m where m.email = :memail and m.password =:mpassword and m.role!='admin'";
		Query query = manager.createQuery(jpql);
//		transaction.begin();
//		users = manager.find(LibraryUsers.class, id);

//		if (users != null) {
//			if (users.getPassword().equals(password) && users.getRole().equalsIgnoreCase("admin")) {
//				transaction.commit();

		query.setParameter("memail",email);
		query.setParameter("mpassword", password);
		UserInformation info = (UserInformation) query.getSingleResult();
		return info;
	} catch(Exception e) {
		e.getMessage();
	} finally {
		manager.close();
		factory.close();
	}
		
		return null;
	}

	public UserRequestInformation borrowBook(int userId, int bookId) {
//		EntityManagerFactory factory =null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation userRequest=new UserRequestInformation();
		try {
//			factory = Persistence.createEntityManagerFactory("hibernatedb");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();	
			manager.persist(userRequest);
			transaction.commit();
			return userRequest;
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
		manager.close();
		factory.close();
		}
		return null;
	}

	public UserRequestInformation returnBook(int userId, int bookId) {
//		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation user = new UserRequestInformation();
		List<BooksInformation> book = new LinkedList<BooksInformation>();
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
//			factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "update UserRequestInformation m set m.status=:mstatus where m.bookid=:mbookid;";
			Query query = manager.createQuery(jpql);
			query.setParameter("mstatus", "returned");
			query.setParameter("mbookId", bookId);
			query.executeUpdate();
//			book.add(bookInfo);
			transaction.commit();
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
		manager.close();
		factory.close();
	}
		return null;
	}


	
}