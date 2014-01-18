package com.uit.rest.business;

import java.util.ArrayList;

import com.uit.rest.dao.IssueDAO;
import com.uit.rest.dao.IssueDAOImpl;
import com.uit.rest.model.Issue;
import com.uit.rest.vo.ConstantsManager;

public class IssueBOImpl implements IssueBO {

	@Override
	public boolean add(Issue issue) {
		IssueDAO dao = new IssueDAOImpl();
		
		return dao.add(issue);
	}

	@Override
	public ArrayList<Issue> getList(int page) {
		IssueDAO dao = new IssueDAOImpl();
		int offset = --page * ConstantsManager.LIMIT;
		
		return dao.getList(ConstantsManager.LIMIT, offset);
	}

	@Override
	public boolean delete(long issueId) {
		IssueDAO dao = new IssueDAOImpl();
		
		return dao.delete(issueId);
	}

}
