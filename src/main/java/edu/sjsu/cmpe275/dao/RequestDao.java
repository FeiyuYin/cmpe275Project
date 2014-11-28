package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.model.Request;

public interface RequestDao {

	public Request getRequestById(long id);
    public Request save(Request request);
    public void delete(Request request);
    public List<Request> allRequest();

}

