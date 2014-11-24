package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.model.Organization;

public interface OrgaDao {

	public Organization getOrgById(long id);
    public Organization save(Organization org);
    public void delete(Organization org);
    public List<Organization> allOrg();
}
