package com.uit.rest.business;

import java.util.List;

import com.uit.rest.dao.FavoriteDAO;
import com.uit.rest.dao.FavoriteDAOImpl;
import com.uit.rest.dao.GeoPointDAO;
import com.uit.rest.dao.GeoPointDAOImpl;
import com.uit.rest.model.Favorite;

public class FavoriteBOImpl implements FavoriteBO {

	@Override
	public boolean add(Favorite favorite) {
		GeoPointDAO geoPointDAO = new GeoPointDAOImpl();
		FavoriteDAO favoriteDAO = new FavoriteDAOImpl();
		
		favorite.setGeoPointID(geoPointDAO.add(favorite));
		
		return (favorite.getGeoPointID() != -1) ? favoriteDAO.add(favorite) : false;
	}

	@Override
	public boolean update(Favorite favorite) {
		FavoriteDAO dao = new FavoriteDAOImpl();
		
		return dao.update(favorite);
	}

	@Override
	public boolean delete(long id) {
		FavoriteDAO dao = new FavoriteDAOImpl();
		
		return dao.delete(id);
	}

	@Override
	public List<Favorite> getList(long accountId) {
		FavoriteDAO dao = new FavoriteDAOImpl();
		
		return dao.getList(accountId);
	}

}
