package com.ronak.java.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JoinRowSetImpl;

public class JoinRowSetJDBC {

	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbcronak";

	public static void main(String[] args) throws SQLException {
		String supplierName = "Acme, Inc.";
		CachedRowSet coffees = null;
		CachedRowSet suppliers = null;
		JoinRowSet jrs = null;

		try {
			coffees = new CachedRowSetImpl();
			coffees.setCommand("SELECT * FROM COFFEES");
			coffees.setUsername(USER_NAME);
			coffees.setPassword(PASSWORD);
			coffees.setUrl(URL);
			coffees.execute();

			suppliers = new CachedRowSetImpl();
			suppliers.setCommand("SELECT * FROM SUPPLIERS");
			suppliers.setUsername(USER_NAME);
			suppliers.setPassword(PASSWORD);
			suppliers.setUrl(URL);
			suppliers.execute();

			jrs = new JoinRowSetImpl();
			jrs.addRowSet(coffees, "SUP_ID");
			jrs.addRowSet(suppliers, "SUP_ID");

			System.out.println("Coffees bought from " + supplierName + ": ");
			while (jrs.next()) {
				if (jrs.getString("SUP_NAME").equals(supplierName)) {
					String coffeeName = jrs.getString(1);
					System.out.println("     " + coffeeName);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (jrs != null) {
				jrs.close();
			}
			if (suppliers != null) {
				suppliers.close();
			}
			if (coffees != null) {
				coffees.close();
			}
		}
	}

}
