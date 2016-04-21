package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.sql.DataSource;

public class StatementBatch {

	public static void main(String[] args) throws SQLException {

		DataSource ds = DataSourceConnection.getDataSource();

		try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement();) {
			stmt.addBatch("INSERT INTO COFFEES " + "VALUES('Amaretto', 49, 9.99, 0, 0)");

			stmt.addBatch("INSERT INTO COFFEES " + "VALUES('Hazelnut', 49, 9.99, 0, 0)");

			stmt.addBatch("INSERT INTO COFFEES " + "VALUES('Amaretto_decaf', 49, " + "10.99, 0, 0)");

			stmt.addBatch("INSERT INTO COFFEES " + "VALUES('Hazelnut_decaf', 49, " + "10.99, 0, 0)");

			int[] updateCounts = stmt.executeBatch();

			//number of rows affected by query in order it was executed
			System.out.println("Update count :" + Arrays.toString(updateCounts));

			// delete data created
			stmt.addBatch("DELETE FROM COFFEES WHERE COF_NAME = 'Amaretto'");

			stmt.addBatch("DELETE FROM COFFEES WHERE COF_NAME = 'Hazelnut'");

			stmt.addBatch("DELETE FROM COFFEES WHERE COF_NAME = 'Amaretto_decaf'");

			stmt.addBatch("DELETE FROM COFFEES WHERE COF_NAME = 'Hazelnut_decaf'");

			int[] deleteCounts = stmt.executeBatch();
		}
	}

}
