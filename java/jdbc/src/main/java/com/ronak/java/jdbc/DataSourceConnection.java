package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * DataSource objects can provide connection pooling and distributed
 * transactions.
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

		DatabaseMetaData dbMetaData = conn.getMetaData();
		boolean isTypeForwardSupported = dbMetaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY);
		System.out.println(" isTypeForwardSupported : " + isTypeForwardSupported);
		boolean isTypeScrollInsensitive = dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
		System.out.println(" isTypeScrollInsensitive : " + isTypeScrollInsensitive);
		boolean isTypeScrollSensitive = dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
		System.out.println(" isTypeScrollSensitive : " + isTypeScrollSensitive);

		// since only TYPE_SCROLL_INSENSITIVE is supported we will use it to
		// check concurrency
		boolean isConcurReadOnly = dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		System.out.println(" isConcurReadOnly : " + isConcurReadOnly);
		boolean isConcurReadUpdatable = dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		System.out.println(" isConcurReadUpdatable : " + isConcurReadUpdatable);

		boolean isHoldOverCommit = dbMetaData.supportsResultSetHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
		System.out.println("Supports HOLD_CURSORS_OVER_COMMIT? " + isHoldOverCommit);

		boolean isCloseOverCommit = dbMetaData.supportsResultSetHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
		System.out.println("Supports CLOSE_CURSORS_AT_COMMIT? " + isCloseOverCommit);
	}

}
