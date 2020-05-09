package com.capgemini.librarymanagementsystemspringrest.dao;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
public class AdminDaoImplementation implements AdminDao {
	@PersistenceUnit
	private EntityManagerFactory factory;

	
	public UserInformation adminLogin(String email, String password) {
	//	EntityManagerFactory factory =null;
		EntityManager manager = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
		//factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
		manager = factory.createEntityManager();
		String jpql ="select a from UserInformation a where a.email = :aemail and a.password =:apassword" ;
	//	String jpql="select m from UserInformation m where m.email = :memail and m.password =:mpassword";
		TypedQuery<UserInformation>  query = manager.createQuery(jpql, UserInformation.class);
		query.setParameter("aemail",email);
		query.setParameter("apassword", password);
	//	query.setParameter("mrole", "admin");
		UserInformation info = (UserInformation) query.getSingleResult();
		return info;
	} catch(Exception e) {
		System.out.println("");
	} finally {
		manager.close();
		//factory.close();
	}
		
		return null;
	}
		
	
	public boolean addUser(UserInformation userInfo) {
	//	EntityManagerFactory factory =null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			//factory = Persistence.createEntityManagerFactory("hibernatedb");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();	
			manager.persist(userInfo);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		} finally {
		manager.close();
	//	factory.close();
		}
	//	return false;
	}
	
	public boolean addBook(BooksInformation bookInfo) {
	//	EntityManagerFactory factory =null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
		//	factory = Persistence.createEntityManagerFactory("hibernatedb");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();	
			manager.persist(bookInfo);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
		manager.close();
		//factory.close();
		}
		return false;
	}


	public boolean removeBook(int bookId) {
		//EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
		//	factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BooksInformation removeBook =manager.find(BooksInformation.class, bookId);
			manager.remove(removeBook);

//			String jpql = properties.getProperty("removebook");
//			Query query = manager.createQuery(jpql);
//			query.setParameter("mbookId", bookId);
		//	int result = query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
		manager.close();
	//	factory.close();
		}
		return false;
	}


	public boolean issueBook(int requestId) {
	//	EntityManagerFactory factory = null;
		int noOfBooks = 0;
		double fine = 0;
		Date date = new Date();
		Date expectedReturnDate = null;
		Date returnedDate = null;

		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation user = new UserRequestInformation();
		int requestBookId = 0;
		int requestUserId = 0;
		UserInformation userInfo = new UserInformation();
		BooksInformation bookInfo = new BooksInformation();
		int reqBookId = 0;
		int reqUserId = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 15);
		expectedReturnDate = calendar.getTime();

//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
			//factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();	
			
			user = manager.find(UserRequestInformation.class, requestId);

			if (user != null) {
				Date issueDate = user.getIssueDate();
				if (issueDate == null) {
					reqUserId = user.getId();
					reqBookId = user.getBookId();

					user.setIssueDate(date);
					user.setExpectedReturnDate(expectedReturnDate);
					transaction.commit();

					transaction.begin();
					userInfo = manager.find(UserInformation.class, reqUserId);
					noOfBooks = userInfo.getNoOfBooksBorrowed();
					++noOfBooks;
					System.out.println("No Of Books Borrowed" + noOfBooks);

					userInfo.setNoOfBooksBorrowed(noOfBooks);
					transaction.commit();

					transaction.begin();
					bookInfo = manager.find(BooksInformation.class, reqBookId);
					bookInfo.setBookAvaliable(false);
					transaction.commit();
				} else {
					throw new LibraryManagementSystemException("Book is not present in the library");
				}

			} else {
				throw new LibraryManagementSystemException("Request Id which was given is invalid");
			}

		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}

		return true;
	}


		
	public BooksInformation searchBook(int bookId) {
		//EntityManagerFactory factory =null;
		EntityManager manager = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
		//factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
		manager = factory.createEntityManager();
//		String jpql = properties.getProperty("searchbook");
		BooksInformation info =manager.find(BooksInformation.class, bookId);

//		Query query = manager.createQuery(jpql);
//		query.setParameter("mbookId", bookId);
//		BooksInformation info = (BooksInformation) query.getSingleResult();
		return info;
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		manager.close();
	//	factory.close();
	}
		return null;
	}

	public List<BooksInformation> showAllBooks() {
	//	EntityManagerFactory factory =null;
		EntityManager manager = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
	//	factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
		manager = factory.createEntityManager();
		String jpql = "select b from BooksInformation b";
		TypedQuery<BooksInformation> query = manager.createQuery(jpql, BooksInformation.class);
		List<BooksInformation> recordlist = query.getResultList();
		return recordlist;
	} catch(Exception e) {
		e.getMessage();
	} finally {
		manager.close();
	//	factory.close();
	}
		return null;
	}

	public List<UserInformation> showAllUsers() {
	//	EntityManagerFactory factory =null;
		EntityManager manager = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
	//	factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
		manager = factory.createEntityManager();
		String jpql = "select b from UserInformation b";
		TypedQuery<UserInformation> query = manager.createQuery(jpql, UserInformation.class);
		List<UserInformation> userList = query.getResultList();
		return userList;
	} catch(Exception e) {
		e.getMessage();
	} finally {
		manager.close();
	//	factory.close();
	}
		return null;
	}
	
	public List<UserRequestInformation> showAllRequests() {
		//EntityManagerFactory factory =null;
		EntityManager manager = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
	//	factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
		manager = factory.createEntityManager();
		String jpql = "select b from UserRequestInformation b";
		TypedQuery<UserRequestInformation> query = manager.createQuery(jpql, UserRequestInformation.class);
		List<UserRequestInformation> requestlist = query.getResultList();
		return requestlist;
	} catch(Exception e) {
		e.getMessage();
	} finally {
		manager.close();
	//	factory.close();
	}
		return null;
	}



	public boolean isBookRecevied(int requestId) {
	//	EntityManagerFactory factory = null;
		int noOfBooks = 0;
		double fine = 0;

		Date expectedReturnDate = null;
		Date returnedDate = null;

		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation user = new UserRequestInformation();
		int requestBookId = 0;
		int requestUserId = 0;
		UserInformation userInfo = new UserInformation();
		BooksInformation bookInfo = new BooksInformation();
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
			//factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();	
			
			user = manager.find(UserRequestInformation.class, requestId);

			if (user != null) {
				requestBookId = user.getBookId();
				requestUserId = user.getId();
				returnedDate = user.getReturnedDate();
				expectedReturnDate = user.getExpectedReturnDate();
				transaction.commit();

				if ((returnedDate != null) && (expectedReturnDate != null)) {
					long expReturnDateInMilliSecs = expectedReturnDate.getTime();
					long returnedDateInMilliSecs = returnedDate.getTime();
					long diffInMilliSecs = returnedDateInMilliSecs - expReturnDateInMilliSecs;
					int NoOfDaysDelayed = (int) (diffInMilliSecs / (24 * 60 * 60 * 1000));

					transaction.begin();
					userInfo = manager.find(UserInformation.class, requestUserId);
					noOfBooks = userInfo.getNoOfBooksBorrowed();
					--noOfBooks;
					userInfo.setNoOfBooksBorrowed(noOfBooks);
					if (NoOfDaysDelayed > 0) {
						fine = userInfo.getFine();
						fine = fine + (NoOfDaysDelayed * 1.8);
						userInfo.setFine(fine);
					}
					transaction.commit();

					transaction.begin();
					bookInfo = manager.find(BooksInformation.class, requestBookId);
					bookInfo.setBookAvaliable(true);
					transaction.commit();

					transaction.begin();
					user = manager.find(UserRequestInformation.class, requestId);
					manager.remove(user);
					transaction.commit();

				} else {
					throw new LibraryManagementSystemException("Book is not present in the library");
				}

			} else {
				throw new LibraryManagementSystemException("Request Id which is given is invalid");
			}
		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}
		return true;
	}



	@Override
	public boolean updateBook(BooksInformation bookInfo) {
		boolean isUpdated=false;
	//	EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
//		BooksInformation book = new BooksInformation();
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
	//		factory = Persistence.createEntityManagerFactory("hibernatedb");
	
			manager = factory.createEntityManager();
			BooksInformation update  = manager.find(BooksInformation.class, bookInfo.getBookId());
			if (update!=null) {
				try {
					transaction = manager.getTransaction();
					transaction.begin();
					
					String bookName = bookInfo.getBookName();
					if (bookName!=null) {
						bookName=bookName.trim();
					}
					String bookAuthor = bookInfo.getBookAuthor();
					if (bookAuthor!=null) {
						bookAuthor=bookAuthor.trim();
					}
					String bookCategory = bookInfo.getBookCategory();
					if (bookCategory!=null) {
						bookCategory=bookCategory.trim();
					}
					String bookPublisher = bookInfo.getBookPublisher();
					if (bookPublisher!=null) {
						bookPublisher=bookPublisher.trim();
					}
					
					if (bookName!=null&&!bookName.isEmpty()&&bookName.length()>1) {
						update.setBookName(bookName);
					}
					if (bookAuthor!=null&&!bookAuthor.isEmpty()&&bookAuthor.length()>1) {
						update.setBookAuthor(bookAuthor);
					}
					if (bookCategory!=null&&!bookCategory.isEmpty()&&bookCategory.length()>1) {
						update.setBookCategory(bookCategory);
					}
					if (bookPublisher!=null&&!bookPublisher.isEmpty()&&bookPublisher.length()>1) {
						update.setBookPublisher(bookPublisher);
					}
					transaction.commit();
					isUpdated=true;
				
				} catch (Exception e) {
				e.printStackTrace();
			} 
				manager.close();
			} return false;
			}
}	
			
			
//public static boolean updateBook1(int bookId) {
////	EntityManagerFactory factory = null;
//	EntityManager manager = null;
//	EntityTransaction transaction = null;
//	UserRequestInformation userRequest=new UserRequestInformation();
//	try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//		Properties properties = new Properties();
//		properties.load(fileInputStream);
//	//	factory = Persistence.createEntityManagerFactory("hibernatedb");
//		manager = factory.createEntityManager();
//		transaction = manager.getTransaction();
//		transaction.begin();
//		String jpql = properties.getProperty("updatebook1");
//		Query query = manager.createQuery(jpql);
//		query.setParameter("mstatus", "approved");
//		query.setParameter("mbookId", bookId);
//		int result = query.executeUpdate();
//		transaction.commit();
//	} catch (Exception e) {
//		e.getMessage();
//		transaction.rollback();
//	} finally {
//	manager.close();
////	factory.close();
//}
//	return false;
//}
//
//public static boolean deleteBook1(int bookId) {
//	//EntityManagerFactory factory = null;
//	EntityManager manager = null;
//	EntityTransaction transaction = null;
//	try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//		Properties properties = new Properties();
//		properties.load(fileInputStream);
//	//	factory = Persistence.createEntityManagerFactory("hibernatedb");
//		manager = factory.createEntityManager();
//		transaction = manager.getTransaction();
//		transaction.begin();
//		String jpql = properties.getProperty("removebook");
//		Query query = manager.createQuery(jpql);
//		query.setParameter("mbookId", bookId);
//		int result = query.executeUpdate();
//		transaction.commit();
//		return true;
//	} catch (Exception e) {
//		e.printStackTrace();
//		transaction.rollback();
//	} finally {
//	manager.close();
////	factory.close();
//	}
//	return false;
//}


