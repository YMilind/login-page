package com.loginpage;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class LoginDAO {
	DataSource dataSource;

	public LoginDAO(DataSource dataSource) {
		try {
			Context cxt = new InitialContext();
			dataSource = (DataSource) cxt.lookup("java:comp/env/jdbc/myoracle");
			if (dataSource == null) {
				throw new Exception("Data source not found!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Allocate and use a connection from the pool
			Connection conn = dataSource.getConnection();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean validateUser(User user) {
		// TODO: validation
		return ((user.getUsername().equals("1")) && (user.getPassword().equals("1")));
	}
}
