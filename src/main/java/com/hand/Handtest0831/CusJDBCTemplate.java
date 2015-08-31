package com.hand.Handtest0831;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.hand.Emptity.Address;
import com.hand.Emptity.Customer;
import com.hand.dao.CustomerDao;

public class CusJDBCTemplate implements CustomerDao {

	private static SessionFactory sessionFactory; 

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		CusJDBCTemplate.sessionFactory = sessionFactory;
	}

	public Short create(Customer cus) {
		Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      Short addcusID = null;
	      try{
	         tx = session.beginTransaction();
	         addcusID = (Short) session.save(cus); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return addcusID;
	   

	}

	public void delete(Customer cus) {
		Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.delete(cus); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }

	}

	public Customer getcus(Customer cus) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    Address address = new Address();
	    List results = null;
	    try{
	         tx = session.beginTransaction();
	         String hql = "FROM Customer C WHERE C.customerId = :customer_id";
	         Query query = session.createQuery(hql);
	         query.setParameter("customer_id",cus.getCustomerId());
	         results = query.list();

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return (Customer) results.get(0);
	}

	public boolean checkcus(Customer cus) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    Address address = new Address();
	    List<Customer> results = null;
	    boolean bool = false;
	    try{
	         tx = session.beginTransaction();
	         String hql = "FROM Customer";
	         Query query = session.createQuery(hql);
	         tx.commit();
	         results = query.list();
	         for(int i =0;i<results.size();i++){
	        	if( results.get(i).getCustomerId()==cus.getCustomerId()){
	        		bool = true;
	        	}
	         }
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return bool;
	}

}
