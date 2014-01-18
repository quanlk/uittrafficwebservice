package com.uit.rest.dao;

import com.uit.rest.model.GeoPoint;

public interface GeoPointDAO {

	long add(GeoPoint geoPoint);
	boolean update(GeoPoint geoPoint);
	boolean delete(GeoPoint geoPoint);
}
