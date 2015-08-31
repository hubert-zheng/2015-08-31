package com.hand.dao;

import com.hand.Emptity.Address;

public interface AddressDao {
	//public void setDataSource(DataSource ds);
	
	public boolean checkAddress(Short address_id);
	
	public Address getAddress(Short address_id);
}
