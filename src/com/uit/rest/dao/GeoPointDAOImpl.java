package com.uit.rest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uit.rest.model.GeoPoint;

public class GeoPointDAOImpl implements GeoPointDAO {

	@Override
	public long add(GeoPoint geoPoint) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = "select * from geopoint where latitude = ? and longitude = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setDouble(1, geoPoint.getLatitude());
			statement.setDouble(2, geoPoint.getLongitude());
			
			rs = statement.executeQuery();
			
			if (!rs.next()) {
				queryString = "insert into geopoint values(null,?,?)";
				
				// Execute query for insert new report
				statement = baseDAO.getConnection().prepareStatement(queryString);
				
				statement.setDouble(1, geoPoint.getLatitude());
				statement.setDouble(2, geoPoint.getLongitude());
				
				if (statement.executeUpdate() > 0) {
					//return (statement.executeUpdate() > 0) ? geoPoint.getId() : -1;
					// Get id that just created from geopoint table
					queryString = "select id from geopoint order by id desc limit 1";

					// Execute query for insert new report
					statement = baseDAO.getConnection().prepareStatement(queryString);

					rs = statement.executeQuery();

					if (rs.next()) {
						return rs.getLong(1);
					}
				}
				
				return -1;
			} else {
				return rs.getLong(1);
			}
			
		} catch (SQLException e) {
			
		} finally {
			// Close result set
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return -1;
	}

	@Override
	public boolean update(GeoPoint geoPoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(GeoPoint geoPoint) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "delete from geopoint where id = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, geoPoint.getId());
			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			
		} finally {
			// Close statement
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					
				}
			}
			
			// Disconnect from database
			baseDAO.disconnectDB();
		}
		
		return false;
	}

}
