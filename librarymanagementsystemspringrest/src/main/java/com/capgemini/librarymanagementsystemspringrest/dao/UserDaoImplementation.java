package com.capgemini.librarymanagementsystemspringrest.dao;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;

@Repository
public class UserDaoImplementation implements UserDao {

	@PersistenceUnit
	private EntityManagerFactory factory;

	public UserInformation userLogin(String email, String password) {
		EntityManagerFactory factory =null;
		EntityManager manager = null;
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);
		factory = Persistence.createEntityManagerFactory("hibernatedb");
		manager = factory.createEntityManager();
		String jpql = properties.getProperty("login2");
		Query query = manager.createQuery(jpql);
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

	public UserRequestInformation borrowBook(UserInformation userInfo, BooksInformation bookInfo) {
		EntityManagerFactory factory =null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation userRequest=new UserRequestInformation();
		try {
			factory = Persistence.createEntityManagerFactory("hibernatedb");
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

	public UserRequestInformation returnBook(UserInformation userInfo, BooksInformation bookInfo) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation user = new UserRequestInformation();
		List<BooksInformation> book = new LinkedList<BooksInformation>();
		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
			Properties properties = new Properties();
			properties.load(fileInputStream);
			factory = Persistence.createEntityManagerFactory("hibernatedb");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = properties.getProperty("updatebook1");
			Query query = manager.createQuery(jpql);
			query.setParameter("mstatus", "returned");
			query.setParameter("mbookId", bookInfo.getBookId());
			query.executeUpdate();
			book.add(bookInfo);
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