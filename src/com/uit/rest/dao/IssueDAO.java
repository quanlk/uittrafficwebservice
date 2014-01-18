package com.uit.rest.dao;

import java.util.ArrayList;

import com.uit.rest.model.Issue;

public interface IssueDAO {

	boolean add(Issue issue);
	ArrayList<Issue> getList(int limit, int offset);
	boolean delete(long issueId);
}
