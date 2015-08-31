package com.hand.Handtest0831;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.hand.Emptity.Customer;

public class MyInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -658277899642145190L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		 if ( entity instanceof Customer ) {
	          System.out.println("Before Save");
	          return true; 
	       }
	       return false;
	}

}
