package com.uit.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BaseDAOImpl implements BaseDAO {

	private static final String TAG = "BaseDAOImpl";
	private Connection connect = null;
	private ResourceBundle resourceBundle = null;
	private String driver = "";
	private String connString = "";
	private String username = "";
	private String password = "";

	public BaseDAOImpl() {
		// Get database properties from resource file
		getDBProperties();
	}

	@Override
	public void connectDB() {
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(connString, username,
					password);
		} catch (Exception e) {

		}
	}

	@Override
	public void disconnectDB() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {

			}
		}
	}
	
	@Override
	public Connection getConnection() {
		if (connect == null) {
			connectDB();
		}
		return connect;
	}

	@Override
	public void getDBProperties() {
		// Declare resource for get database properties from file
		resourceBundle = ResourceBundle.getBundle("db");

		// Get database properties from resource
		driver = resourceBundle.getString("Driver");
		connString = resourceBundle.getString("Url") + resourceBundle.getString("Port")
				+ "/" + resourceBundle.getString("DatabaseName");
		username = resourceBundle.getString("Username");
		password = resourceBundle.getString("Password");
	}

}
