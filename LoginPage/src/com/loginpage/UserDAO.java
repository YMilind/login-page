package com.loginpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	DataSource dataSource;

	public UserDAO() {
		try {
			Context cxt = new InitialContext();
			dataSource = (DataSource) cxt.lookup("java:comp/env/jdbc/myoracle");
			if (dataSource == null) {
				throw new SQLException("Data source not found!");
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
	}

	public boolean createUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;		
		try {
			// Allocate and use a connection from the pool
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(
					"INSERT INTO users(username, password) "
					+ "VALUES (?, ?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			int result = ps.executeUpdate();
			return (result > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		   
	}
	
	public boolean usernameAvailable(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// Allocate and use a connection from the pool
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(
					"SELECT COUNT(*) AS count FROM users "
					+ "WHERE username = ?");
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt("count");
			}
			return (count == 0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean validateUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// Allocate and use a connection from the pool
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(
					"SELECT COUNT(*) AS count FROM users "
					+ "WHERE username = ? AND password = ?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt("count");
			}
			return (count > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
