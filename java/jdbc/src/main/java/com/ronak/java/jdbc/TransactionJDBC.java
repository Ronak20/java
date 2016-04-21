package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * A transaction is a set of one or more statements that is executed as a unit,
 * so either all of the statements are executed, or none of the statements is
 * executed.
 * 
 * @author Ronak
 *
 */
public class TransactionJDBC {

	public static void main(String[] args) throws SQLException {

		String updateString = "update COFFEES " + "set SALES = 100,TOTAL = 100 where COF_NAME = 'Colombian'";
		String getString = "select * from COFFEES " + "where COF_NAME = 'Colombian'";
		DataSource ds = DataSourceConnection.getDataSource();

		try (Connection conn = ds.getConnection()) {
			conn.setAutoCommit(false);
			Savepoint savepoint = conn.setSavepoint();

			try (Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					Statement pstmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY)) {
				// update data
				int updateResult = pstmt.executeUpdate(updateString);

				System.out.println(" updateResult : " + updateResult);

				// get data before commit
				try (ResultSet rs = pstmt2.executeQuery(getString)) {
					if (rs.next()) {
						String coffeeName = rs.getString("COF_NAME");
						int supplierID = rs.getInt("SUP_ID");
						float price = rs.getFloat("PRICE");
						int sales = rs.getInt("SALES");
						int total = rs.getInt("TOTAL");
						System.out.println("Before commit : " + coffeeName + "\t" + supplierID + "\t" + price + "\t"
								+ sales + "\t" + total);
					}

				}

				conn.rollback(savepoint);

				// get data after rollback
				try (ResultSet rs2 = pstmt2.executeQuery(getString)) {
					if (rs2.next()) {
						String coffeeName = rs2.getString("COF_NAME");
						int supplierID = rs2.getInt("SUP_ID");
						float price = rs2.getFloat("PRICE");
						int sales = rs2.getInt("SALES");
						int total = rs2.getInt("TOTAL");
						System.out.println("After rollback : " + coffeeName + "\t" + supplierID + "\t" + price + "\t"
								+ sales + "\t" + total);
					}
				}

				conn.commit();

				// get data after commit
				try (ResultSet rs3 = pstmt2.executeQuery(getString)) {
					if (rs3.next()) {
						String coffeeName = rs3.getString("COF_NAME");
						int supplierID = rs3.getInt("SUP_ID");
						float price = rs3.getFloat("PRICE");
						int sales = rs3.getInt("SALES");
						int total = rs3.getInt("TOTAL");
						System.out.println("After commit : " + coffeeName + "\t" + supplierID + "\t" + price + "\t"
								+ sales + "\t" + total);
					}

				}

			}
		}
	}
}
