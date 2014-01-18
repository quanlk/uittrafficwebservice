package com.uit.rest.business;

import java.util.List;

import com.uit.rest.model.Favorite;

public interface FavoriteBO {

	boolean add(Favorite favorite);
	boolean update(Favorite favorite);
	boolean delete(long id);
	List<Favorite> getList(long accountId);
}
