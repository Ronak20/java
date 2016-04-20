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
 * failover is the name of a standby database
 * 
 * @author Ronak
 *
 */
public class DriverManagerConnection {

	public static void main(String... args) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcronak", "root", "root");

		String catalog = conn.getCatalog();
		System.out.println(" Catalog : " + catalog);

		// TRANSACTION_NONE 0
		// TRANSACTION_READ_COMMITTED 2
		// TRANSACTION_READ_UNCOMMITTED 1
		// TRANSACTION_REPEATABLE_READ 4
		// TRANSACTION_SERIALIZABLE 8
		int transactionIsolation = conn.getTransactionIsolation();
		System.out.println(" TransactionIsolation : " + transactionIsolation);

		// ResultSet.HOLD_CURSORS_OVER_COMMIT 1
		// ResultSet.CLOSE_CURSORS_AT_COMMIT 2
		int holdability = conn.getHoldability();
		System.out.println(" Holdability : " + holdability);

		int networkTimeout = conn.getNetworkTimeout();
		System.out.println(" networkTimeout : " + networkTimeout);

		boolean autoCommit = conn.getAutoCommit();
		System.out.println(" autoCommit : " + autoCommit);

	}

}
