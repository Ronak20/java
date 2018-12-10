package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import com.sun.rowset.JdbcRowSetImpl;

/**
 * Different way of creating JdbcRowSet
 * 
 * @author Ronak
 *
 */
public class JdbcRowSetExample {

	public static void main(String[] args) throws SQLException {
		DataSource ds = DataSourceConnection.getDataSource();
		String GET_COFFEE = "SELECT COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";

		// Creating Jdbc row set with url,username and password
		System.out.println();
		System.out.println("Creating JdbcRowSet with url,username and password");
		try (JdbcRowSet jdbcRowSet = new JdbcRowSetImpl();) {
			jdbcRowSet.setCommand("select * from COFFEES");
			jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/jdbcronak");
			jdbcRowSet.setUsername("root");
			jdbcRowSet.setPassword("root");
			jdbcRowSet.execute();

			while (jdbcRowSet.next()) {
				String coffeeName = jdbcRowSet.getString(1);
				int supplierID = jdbcRowSet.getInt(2);
				float price = jdbcRowSet.getFloat(3);
				int sales = jdbcRowSet.getInt(4);
				int total = jdbcRowSet.getInt(5);
				System.out.println(coffeeName + ", " + supplierID + ", " + price + ", " + sales + ", " + total);
			}
		}

		// Creating Jdbc row set using result set
		System.out.println();
		System.out.println("Creating JdbcRowSet using result set");
		try (Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = stmt.executeQuery(GET_COFFEE);
				JdbcRowSet jdbcRowSet = new JdbcRowSetImpl(rs)) {
			while (jdbcRowSet.next()) {
				String coffeeName = jdbcRowSet.getString(1);
				int supplierID = jdbcRowSet.getInt(2);
				float price = jdbcRowSet.getFloat(3);
				int sales = jdbcRowSet.getInt(4);
				int total = jdbcRowSet.getInt(5);
				System.out.println(coffeeName + ", " + supplierID + ", " + price + ", " + sales + ", " + total);
			}
		}

		// Creating Jdbc row set using connection
		System.out.println();
		System.out.println("Creating JdbcRowSet using connection");
		try (Connection conn = ds.getConnection(); JdbcRowSet jdbcRowSet = new JdbcRowSetImpl(conn)) {
			jdbcRowSet.setCommand(GET_COFFEE);
			jdbcRowSet.execute();
			while (jdbcRowSet.next()) {
				String coffeeName = jdbcRowSet.getString(1);
				int supplierID = jdbcRowSet.getInt(2);
				float price = jdbcRowSet.getFloat(3);
				int sales = jdbcRowSet.getInt(4);
				int total = jdbcRowSet.getInt(5);
				System.out.println(coffeeName + ", " + supplierID + ", " + price + ", " + sales + ", " + total);
			}
		}

		// Creating using RowSetFactory
		System.out.println();
		System.out.println("Creating JdbcRowSet using RowSetFactory");
		RowSetFactory myRowSetFactory = RowSetProvider.newFactory();
		try (JdbcRowSet jdbcRowSet = myRowSetFactory.createJdbcRowSet()) {
			jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/jdbcronak");
			jdbcRowSet.setUsername("root");
			jdbcRowSet.setPassword("root");

			jdbcRowSet.setCommand("select * from COFFEES");
			jdbcRowSet.execute();

			while (jdbcRowSet.next()) {
				String coffeeName = jdbcRowSet.getString(1);
				int supplierID = jdbcRowSet.getInt(2);
				float price = jdbcRowSet.getFloat(3);
				int sales = jdbcRowSet.getInt(4);
				int total = jdbcRowSet.getInt(5);
				System.out.println(coffeeName + ", " + supplierID + ", " + price + ", " + sales + ", " + total);
			}
		}

	}
}
