package com.uit.rest.dao;

import java.sql.Connection;

public interface BaseDAO {
	// Connect to database
	public void connectDB();

	// Disconnect from database
	public void disconnectDB();
	
	// Get connection
	public Connection getConnection();

	// Get database properties from resource file
	public void getDBProperties();
}
