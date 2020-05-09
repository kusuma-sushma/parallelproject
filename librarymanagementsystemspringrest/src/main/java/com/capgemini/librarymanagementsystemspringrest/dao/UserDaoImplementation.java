package com.capgemini.librarymanagementsystemspringrest.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystemspringrest.dto.BooksInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserInformation;
import com.capgemini.librarymanagementsystemspringrest.dto.UserRequestInformation;
import com.capgemini.librarymanagementsystemspringrest.exception.LibraryManagementSystemException;

@Repository
public class UserDaoImplementation implements UserDao {

	@PersistenceUnit
	private EntityManagerFactory factory;

	public UserInformation userLogin(String email, String password) {
//		EntityManagerFactory factory =null;
		EntityManager manager = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
		try {
//		factory = Persistence.createEntityManagerFactory("hibernatedb");
		manager = factory.createEntityManager();
		String jpql = "select a from UserInformation a where a.email = :aemail and a.password =:apassword" ;
		TypedQuery<UserInformation>  query = manager.createQuery(jpql, UserInformation.class);
		query.setParameter("aemail",email);
		query.setParameter("apassword", password);
		UserInformation info = (UserInformation) query.getSingleResult();
		return info;
	} catch(Exception e) {
		System.out.println();
	} finally {
		manager.close();
//		factory.close();
	}
		
		return null;
	}

	public boolean borrowBook(int id, int bookId) {
//		EntityManagerFactory factory =null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserInformation userInfo = new UserInformation();
		BooksInformation bookInfo = new BooksInformation();
		UserRequestInformation userRequest=new UserRequestInformation();
		try {
//			factory = Persistence.createEntityManagerFactory("hibernatedb");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select count(*) from UserRequestInformation r where r.bookId=:rbookId";
			Query query = manager.createQuery(jpql);
			query.setParameter("rbookId", bookId);
			int noOfReq = ((Number) query.getSingleResult()).intValue();
			System.out.println("no of req placed" + noOfReq);

			if (noOfReq < 3) {
				bookInfo = manager.find(BooksInformation.class, bookId);

				if (bookInfo != null) {
					jpql = "select r from UserRequestInformation r ";
					TypedQuery<UserRequestInformation> query2 = manager.createQuery(jpql, UserRequestInformation.class);
					List<UserRequestInformation> list = query2.getResultList();

					for (UserRequestInformation requestInfo : list) {
						if (requestInfo.getBookId() == bookId) {
							throw new LibraryManagementSystemException(
									"Book has already given to others hence unable to borrow ");
						}
					}

					if (bookInfo.isBookAvaliable()) {
						transaction.begin();
						userRequest.setId(id);
						userRequest.setBookId(bookId);
						manager.persist(userRequest);
						// flag=true;
						transaction.commit();

					} else {
						throw new LibraryManagementSystemException("Book is not present in the library");
					}

				} else {
					throw new LibraryManagementSystemException("Book Id which was given is invalid");
				}

			} else {
				throw new LibraryManagementSystemException("You have Already borrowed Maximum No Of Books");
			}

		} catch (

		LibraryManagementSystemException e) {
			transaction.rollback();

			// if(flag) {
			// transaction.rollback();
			// }
			throw new LibraryManagementSystemException(e.getMessage());
		}
		return true;
	}

	public boolean returnBook(int userId, int bookId) {
//		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation user = new UserRequestInformation();
		int requestId=0;
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, 20);
		Date returnedDate = calendar2.getTime();

//		List<BooksInformation> book = new LinkedList<BooksInformation>();
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
		try {
//			factory = Persistence.createEntityManagerFactory("hibernatedb");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select u from UserRequestInformation u ";
			TypedQuery<UserRequestInformation> query2 = manager.createQuery(jpql, UserRequestInformation.class);
			List<UserRequestInformation> userList = query2.getResultList();

			for (UserRequestInformation requestInfo : userList) {
				if ((requestInfo.getBookId() == bookId) && (requestInfo.getId() == userId)) {
					if (requestInfo.getReturnedDate() != null) {
						throw new LibraryManagementSystemException("Book has already returned");
					} else {
						requestId = requestInfo.getRequestId();
					}
				}
			}

			if (requestId != 0) {
				transaction.begin();
				user = manager.find(UserRequestInformation.class, requestId);
				user.setReturnedDate(returnedDate);
				transaction.commit();

			} else {
				throw new LibraryManagementSystemException(
						"Book is not present in the library");
			}

		} catch (LibraryManagementSystemException e) {
			transaction.rollback();
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}

		return true;

	}

	
}