package com.uit.rest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionDAOImpl implements RegionDAO {

	@Override
	public long add(String regionName) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = "select * from region where name = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setString(1, regionName);
			
			rs = statement.executeQuery();
			
			if (!rs.next()) {
				queryString = "insert into region values(null,?)";
				
				// Execute query for insert new report
				statement = baseDAO.getConnection().prepareStatement(queryString);
				
				statement.setString(1, regionName);
				
				if (statement.executeUpdate() > 0) {
					//return (statement.executeUpdate() > 0) ? geoPoint.getId() : -1;
					// Get id that just created from geopoint table
					queryString = "select id from region order by id desc limit 1";

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

}
