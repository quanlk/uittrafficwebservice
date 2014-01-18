package com.uit.rest.business;

import java.util.List;

import com.uit.rest.dao.AccountDAO;
import com.uit.rest.dao.AccountDAOImpl;
import com.uit.rest.model.Account;
import com.uit.rest.vo.ConstantsManager;

public class AccountBOImpl implements AccountBO {

	@Override
	public boolean add(Account account) {
		AccountDAO dao = new AccountDAOImpl();
		
		return dao.add(account);
	}

	@Override
	public boolean update(Account account) {
		AccountDAO dao = new AccountDAOImpl();
		
		return dao.update(account);
	}

	@Override
	public Account get(String username) {
		AccountDAO dao = new AccountDAOImpl();
		
		return dao.get(username);
	}

	@Override
	public List<Account> getList(int page) {
		AccountDAO dao = new AccountDAOImpl();
		int offset = --page * ConstantsManager.LIMIT;
		
		return dao.getList(ConstantsManager.LIMIT, offset);
	}

	@Override
	public boolean changeRole(long id) {
		AccountDAO dao = new AccountDAOImpl();
		
		return dao.changeRole(id);
	}

	@Override
	public List<Account> search(String keyword) {
		AccountDAO dao = new AccountDAOImpl();
		
		return dao.search(keyword);
	}

}
