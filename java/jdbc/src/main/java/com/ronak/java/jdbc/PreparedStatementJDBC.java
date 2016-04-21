package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * The main feature of a PreparedStatement object is that,SQL statement is sent
 * to the DBMS right away, where it is compiled.
 * 
 * After a parameter has been set with a value, it retains that value until it
 * is reset to another value, or the method clearParameters is called.
 * 
 * executeQuery if the query returns only one ResultSet (such as a SELECT SQL
 * statement), executeUpdate if the query does not return a ResultSet (such as
 * an UPDATE SQL statement), or execute if the query might return more than one
 * ResultSet object.
 * 
 * when the return value for executeUpdate is 0, it can mean one of two things:
 * The statement executed was an update statement that affected zero rows. The
 * statement executed was a DDL statement.
 * 
 * 
 * @author Ronak
 *
 */
public class PreparedStatementJDBC {

	public static void main(String... args) throws SQLException {
		String updateString = "update COFFEES " + "set SALES = ?,TOTAL =? where COF_NAME = ?";
		String getString = "select * from COFFEES " + "where COF_NAME = ?";
		DataSource ds = DataSourceConnection.getDataSource();

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(updateString);
				PreparedStatement pstmt2 = conn.prepareStatement(getString);) {
			// update data
			pstmt.setInt(1, 100);
			pstmt.setInt(2, 100);
			pstmt.setString(3, "Colombian");

			int updateResult = pstmt.executeUpdate();

			System.out.println(" updateResult : " + updateResult);

			// get data
			pstmt2.setString(1, "Colombian");
			try (ResultSet rs = pstmt2.executeQuery()) {
				if (rs.next()) {
					String coffeeName = rs.getString("COF_NAME");
					int supplierID = rs.getInt("SUP_ID");
					float price = rs.getFloat("PRICE");
					int sales = rs.getInt("SALES");
					int total = rs.getInt("TOTAL");
					System.out.println(coffeeName + "\t" + supplierID + "\t" + price + "\t" + sales + "\t" + total);
				}
			}

			// reset data
			pstmt.setInt(1, 0);
			pstmt.setInt(2, 0);
			pstmt.setString(3, "Colombian");

			int resetResult = pstmt.executeUpdate();

			System.out.println(" resetResult : " + resetResult);
		}
	}

}
