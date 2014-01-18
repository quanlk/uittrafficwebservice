package com.uit.rest.business;

import java.util.ArrayList;

import com.uit.rest.model.Issue;

public interface IssueBO {

	boolean add(Issue issue);
	ArrayList<Issue> getList(int page);
	boolean delete(long issueId);
}
