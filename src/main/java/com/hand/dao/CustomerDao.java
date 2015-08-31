package com.hand.dao;

import javax.sql.DataSource;

import com.hand.Emptity.Customer;

public interface CustomerDao {
	//public void setDataSource(DataSource ds);
	
	public Short create(Customer cus);
	
	public void delete(Customer cus);
	
	public Customer getcus(Customer cus);
	
	public boolean checkcus(Customer cus);
}
