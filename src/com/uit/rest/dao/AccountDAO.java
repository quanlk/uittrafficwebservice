package com.uit.rest.dao;

import java.util.List;

import com.uit.rest.model.Account;

public interface AccountDAO {

	boolean add(Account account);
	boolean update(Account account);
	Account get(String username);
	List<Account> getList(int limit, int offset);
	boolean changeRole(long id);
	List<Account> search(String keyword);
}
