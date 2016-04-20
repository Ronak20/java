package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLExceptionJDBC {

	public static void main(String[] args) {
		try {
			// wrong url
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcrona", "root", "root");
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("Error Code: " + ex.getErrorCode());
			System.err.println("Message: " + ex.getMessage());

			Throwable t = ex.getCause();
			while (t != null) {
				System.out.println("Cause: " + t);
				t = t.getCause();
			}
		}
	}
}
