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



public class AdminDaoImplementation implements AdminDao {
	private final static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("hibernatedb");
	
	public UserInformation adminLogin(String email, String password) {
	//	EntityManagerFactory factory =null;
		EntityManager manager = null;
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
		//factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
		manager = factory.createEntityManager();
		String jpql = "select m from UserInformation m where m.email = :memail and m.password =:mpassword and m.role='admin'";
	//	String jpql="select m from UserInformation m where m.email = :memail and m.password =:mpassword";
		TypedQuery<UserInformation>  query = manager.createQuery(jpql, UserInformation.class);
		query.setParameter("memail",email);
		query.setParameter("mpassword", password);
	//	query.setParameter("mrole", "admin");
		UserInformation info = (UserInformation) query.getSingleResult();
		return info;
	} catch(Exception e) {
		e.printStackTrace();
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


	public boolean issueBook(int userId, int bookId) {
	//	EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation user = new UserRequestInformation();
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
			//factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();	
			manager.persist(user);
			transaction.commit();
			transaction.begin();
			String jpql = "update UserRequestInformation m set m.status = :mstatus where m.bookid=:bookid";
			Query query = manager.createQuery(jpql);
			query.setParameter("mstatus", user.getStatus());
			query.setParameter("mbookId", bookId);
			boolean update=AdminDaoImplementation.updateBook1(bookId);
			if (update!=false) {
			boolean delete =AdminDaoImplementation.deleteBook1(bookId);
			if (delete!=false) {
				 query.executeUpdate();
				transaction.commit();
			}
			}
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
		manager.close();
		//factory.close();
	}
		return false;
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
		String jpql = "select m from BooksInformation m where m.bookId = :mbookId";
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
		String jpql = "select m from BooksInformation m";
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
		String jpql = "select m from UserInformation m";
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
		String jpql = "select m from UserRequestInformation m";
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



	public boolean isBookRecevied(int userId, int bookId) {
	//	EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		UserRequestInformation user = new UserRequestInformation();
		List<BooksInformation> book = new LinkedList<BooksInformation>();
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
		//	factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql ="update BookAllotment set returndate=? and fine=? where bookid=?";
			Query query = manager.createQuery(jpql);
//			query.setParameter("mstatus", "returned");
//			query.setParameter("mbookId", bookInfo.getBookId());
			query.executeUpdate();
//			book.add(bookInfo);
			transaction.commit();
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
		manager.close();
		//factory.close();
	}
		return false;
	}



	@Override
	public boolean updateBook(int bookId) {
	//	EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BooksInformation book = new BooksInformation();
//		try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//			Properties properties = new Properties();
//			properties.load(fileInputStream);
	//		factory = Persistence.createEntityManagerFactory("hibernatedb");
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "update BooksInformation m set m.bookname =:mbookname, m.bookauthor=:mbookauthor, m.bookcategory=:mbookcategory, m.bookpublisher=:mbookpublisher where m.bookid=:bookid";
			// BooksInformation record = manager.find(BooksInformation.class, bookId);
			//TypedQuery<BooksInformation>  query = manager.createQuery(jpql, BooksInformation.class);
			Query query = manager.createQuery(jpql);
//			record.setBookName(book.getBookName());
//			record.setBookAuthor(book.getBookAuthor());
//			record.setBookCategory(book.getBookCategory());
//			record.setBookPublisher(book.getBookPublisher());
//			record.setBookAuthor(bookAuthor);
			query.setParameter("mbookname", book.getBookName());
			query.setParameter("mbookauthor", book.getBookAuthor());
			query.setParameter("mbookcategory", book.getBookCategory());
			query.setParameter("mbookpublisher", book.getBookPublisher() );
			query.setParameter("mbookId", bookId);
			int result = query.executeUpdate();
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

public static boolean updateBook1(int bookId) {
//	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	UserRequestInformation userRequest=new UserRequestInformation();
//	try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//		Properties properties = new Properties();
//		properties.load(fileInputStream);
	//	factory = Persistence.createEntityManagerFactory("hibernatedb");
	try {
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		transaction.begin();
		String jpql = "update UserRequestInformation m set m.status=:mstatus where m.bookid=:mbookid;";
		Query query = manager.createQuery(jpql);
		query.setParameter("mstatus", "approved");
		query.setParameter("mbookId", bookId);
		int result = query.executeUpdate();
		transaction.commit();
	} catch (Exception e) {
		e.getMessage();
		transaction.rollback();
	} finally {
	manager.close();
//	factory.close();
}
	return false;
}

public static boolean deleteBook1(int bookId) {
	//EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
//	try (FileInputStream fileInputStream = new FileInputStream("databaseproperties.properties")) {
//		Properties properties = new Properties();
//		properties.load(fileInputStream);
	//	factory = Persistence.createEntityManagerFactory("hibernatedb");
	try {
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		transaction.begin();
		String jpql = "delete from BooksInformation m where m.bookId =:mbookId;";
		Query query = manager.createQuery(jpql);
		query.setParameter("mbookId", bookId);
		int result = query.executeUpdate();
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

}
