package com.uit.rest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uit.rest.model.Account;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public boolean add(Account account) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "insert into account values(null,?,?,?,?)";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setString(1, account.getUsername());
			statement.setString(2, account.getPassword());
			statement.setString(3, account.getPhoneNumber());
			statement.setString(4, account.getRole());
			
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
	public boolean update(Account account) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "update account set password = ? where username = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setString(1, account.getPassword());
			statement.setString(2, account.getUsername());
			
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
	public Account get(String username) {
		Account result = null;
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = "select * from account where username = '" + username + "'";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			rs = statement.executeQuery();
			
			if (rs.next()) {
				result = new Account(
						rs.getLong(1), 
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4),
						rs.getString(5));
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

	@Override
	public List<Account> getList(int limit, int offset) {
		ArrayList<Account> result = new ArrayList<Account>();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = 
				"select id, username, password, phonenumber, role " +
				"from account " +
				"limit ?, ?" ;
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setInt(1, offset);
			statement.setInt(2, limit);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				Account account = new Account(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5));
				
				result.add(account);
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

	@Override
	public boolean changeRole(long id) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = 
				"update account " +
				"set role = (case when (role = 'admin') then 'user' else 'admin' end) " +
				"where id = ?";
		
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
	public List<Account> search(String keyword) {
		ArrayList<Account> result = new ArrayList<Account>();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = 
				"select id, username, password, phonenumber, role " +
				"from account " +
				"where username like '%" + keyword + "%'" ;
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				Account account = new Account(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5));
				
				result.add(account);
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
