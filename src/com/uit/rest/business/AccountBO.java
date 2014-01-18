package com.uit.rest.business;

import java.util.List;

import com.uit.rest.model.Account;

public interface AccountBO {

	boolean add(Account account);
	boolean update(Account account);
	Account get(String username);
	List<Account> getList(int page);
	boolean changeRole(long id);
	List<Account> search(String keyword);
}
