package com.uit.rest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uit.rest.model.Issue;

public class IssueDAOImpl implements IssueDAO {

	@Override
	public boolean add(Issue issue) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "insert into issue values(null,?,?,null,?)";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, issue.getAccountId());
			statement.setLong(2, issue.getReportId());
			statement.setString(3, issue.getMessage());
			
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
	public ArrayList<Issue> getList(int limit, int offset) {
		ArrayList<Issue> result = new ArrayList<Issue>();
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String queryString = 
				"select issue.id, issue.report_id, account.username, time, message " +
				"from issue, account " +
				"where issue.account_id = account.id " +
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
				Issue issue = new Issue(
						rs.getLong(1), 
						rs.getLong(2), 
						rs.getString(3), 
						rs.getTimestamp(4).toString(),
						rs.getString(5));
				
				result.add(issue);
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
	public boolean delete(long issueId) {
		BaseDAO baseDAO = new BaseDAOImpl();
		PreparedStatement statement = null;
		String queryString = "delete from issue where id = ?";
		
		try {
			// Connect to database
			baseDAO.connectDB();

			// Execute query for insert new report
			statement = baseDAO.getConnection().prepareStatement(queryString);
			
			statement.setLong(1, issueId);
			
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
