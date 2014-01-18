package com.uit.rest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uit.rest.model.Favorite;

public class FavoriteDAOImpl implements FavoriteDAO {

	@Override
	public boolean add(Favorite favorite) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "insert into favorite values(null,?,?,?,?)";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, favorite.getAccountID());
			statement.setLong(2, favorite.getGeoPointID());
			statement.setString(3, favorite.getAddress());
			statement.setString(4, favorite.getDescription());
			
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

	@Override
	public boolean update(Favorite favorite) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long id) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "delete from favorite where id = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, id);
			
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

	@Override
	public List<Favorite> getList(long accountId) {
		List<Favorite> result = new ArrayList<Favorite>();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = 
				"select favorite.id, latitude, longitude, favorite.address, favorite.description " +
				"from favorite, geopoint " +
				"where favorite.geopoint_id = geopoint.id and account_id = " + accountId;
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				Favorite favorite = new Favorite(
						rs.getLong(1), 
						accountId, 
						rs.getDouble(2), 
						rs.getDouble(3), 
						rs.getString(4),
						rs.getString(5));
				
				result.add(favorite);
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
		
		return result;
	}

}
