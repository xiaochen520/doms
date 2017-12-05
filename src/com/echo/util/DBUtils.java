package com.echo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {
	private static final String OPTION_FILE_NAME = "jdbc";

	private static String drivers;

	private static String url;

	private static String user;

	private static String password;
	static {
		ResourceBundle res = ResourceBundle.getBundle(OPTION_FILE_NAME);
		drivers = res.getString("jdbc.classDriver").trim();
		url = res.getString("jdbc.url").trim();
		user = res.getString("jdbc.username").trim();
		password = res.getString("jdbc.password").trim();
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(drivers).newInstance();
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn == null) {
			throw new SQLException("ajax.DBUtils: Cannot get connection.");
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn == null)
			return;
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("ajax.DBUtils: Cannot close connection.");
		}
	}

	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("ajax.DBUtils: Cannot close statement.");
		}

	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("ajax.DBUtils: Cannot close resultset.");
		}
	}

}
