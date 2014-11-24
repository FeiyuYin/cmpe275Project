package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.model.Address;

public interface AddressDao {

	public Address save(Address address);
    public void delete(Address address);
    public List<Address> allAddress();
}
