package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * DataSource objects can provide connection pooling and distributed transactions.
 * 
 * A JDBC driver should include at least a basic DataSource implementation.
 * 
 * @author Ronak
 *
 */
public class DataSourceConnection {

	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbcronak";

	public static DataSource getDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(URL);
		dataSource.setUser(USER_NAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

	public static void main(String... args) throws SQLException {
		DataSource ds = DataSourceConnection.getDataSource();
		Connection conn = ds.getConnection();

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
