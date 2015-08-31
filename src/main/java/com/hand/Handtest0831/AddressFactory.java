package com.hand.Handtest0831;

import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hand.Emptity.Address;
import com.hand.dao.AddressDao;

public class AddressFactory implements AddressDao{

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public boolean checkAddress(Short address_id) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    boolean bool = false;
	       
	      try{
	         tx = session.beginTransaction();
	         List addlist = session.createQuery("FROM Address").list(); 
	         for(Iterator iterator = addlist.iterator(); iterator.hasNext();){
	        	 Address address = (Address) iterator.next();
	        	 if(address.getAddressId()==address_id){
	        		 bool = true;
	        	 }
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return bool;
	}
	public Address getAddress(Short address_id) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    Address address = new Address();
	    List results = null;
	    try{
	         tx = session.beginTransaction();
	         String hql = "FROM Address A WHERE A.addressId = :address_id";
	         Query query = session.createQuery(hql);
	         query.setParameter("address_id",address_id);
	         results = query.list();

	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return (Address) results.get(0);
	}

}
