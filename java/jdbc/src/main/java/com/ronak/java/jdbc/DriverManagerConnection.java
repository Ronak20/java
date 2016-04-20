package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Connecting database using DriverManager
 * 
 * Format of connection string is
 * jdbc:mysql://host,failoverhost:port/database?propertyName1=propertyValue1&
 * propertyName2=propertyValue2...
 * 
 * When starting a new connection, the driver always tries to connect to the
 * primary host first and, if required, fails over to the secondary hosts on the
 * list sequentially when communication problems are experienced.
 * 
 * @author Ronak
 *
 */
public class DriverManagerConnection {

	public static void main(String... args) {

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcpractice", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
