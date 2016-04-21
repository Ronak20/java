package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * Read ResultSet.txt
 * 
 * @author Ronak
 *
 */
public class ResultSetJDBC {

	public static void main(String... args) throws SQLException {
		DataSource ds = DataSourceConnection.getDataSource();
		String GET_COFFEE = "SELECT COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";

		try (Connection conn = ds.getConnection();
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			try (ResultSet rs = st.executeQuery(GET_COFFEE)) {
				while (rs.next()) {
					String coffeeName = rs.getString("COF_NAME");
					int supplierID = rs.getInt("SUP_ID");
					float price = rs.getFloat("PRICE");
					int sales = rs.getInt("SALES");
					int total = rs.getInt("TOTAL");
					System.out.println(coffeeName + "\t" + supplierID + "\t" + price + "\t" + sales + "\t" + total);
				}
				System.out.println("=========================");
				System.out.println("Printing in reverse manner");
				while (rs.previous()) {
					String coffeeName = rs.getString("COF_NAME");
					int supplierID = rs.getInt("SUP_ID");
					float price = rs.getFloat("PRICE");
					int sales = rs.getInt("SALES");
					int total = rs.getInt("TOTAL");
					System.out.println(coffeeName + "\t" + supplierID + "\t" + price + "\t" + sales + "\t" + total);
				}

				System.out.println("=========================");
				System.out.println("Printing in first");
				if (rs.absolute(1)) {
					String coffeeName = rs.getString("COF_NAME");
					int supplierID = rs.getInt("SUP_ID");
					float price = rs.getFloat("PRICE");
					int sales = rs.getInt("SALES");
					int total = rs.getInt("TOTAL");
					System.out.println(coffeeName + "\t" + supplierID + "\t" + price + "\t" + sales + "\t" + total);
				}

				System.out.println("=========================");
				System.out.println("Update first row");
				if (rs.absolute(1)) {
					String coffeeName = rs.getString("COF_NAME");
					int supplierID = rs.getInt("SUP_ID");
					float price = rs.getFloat("PRICE");
					int sales = rs.getInt("SALES");
					int total = rs.getInt("TOTAL");
					System.out.println(coffeeName + "\t" + supplierID + "\t" + price + "\t" + sales + "\t" + total);

					// update while through result set
					rs.updateFloat("PRICE", 10.0f);
					rs.updateRow();

					float updatedprice = rs.getFloat("PRICE");
					System.out.println(
							coffeeName + "\t" + supplierID + "\t" + updatedprice + "\t" + sales + "\t" + total);

					// reset db
					rs.updateFloat("PRICE", price);
					rs.updateRow();

					float resetprice = rs.getFloat("PRICE");
					System.out
							.println(coffeeName + "\t" + supplierID + "\t" + resetprice + "\t" + sales + "\t" + total);
				}

				System.out.println("=========================");
				System.out.println("Insert row");

				// moves the cursor to the insert row. The insert row is a
				// special row associated with an updatable result set. It is
				// essentially a buffer where a new row can be constructed by
				// calling the updater methods prior to inserting the row into
				// the result set.
				rs.moveToInsertRow();
				rs.updateString("COF_NAME", "Amaretto");
				rs.updateInt("SUP_ID", 49);
				rs.updateFloat("PRICE", 9.99f);
				rs.updateInt("SALES", 0);
				rs.updateInt("TOTAL", 0);

				// insert row
				rs.insertRow();

				// go to last row,print it and delete it
				if (rs.last()) {
					String coffeeName = rs.getString("COF_NAME");
					int supplierID = rs.getInt("SUP_ID");
					float price = rs.getFloat("PRICE");
					int sales = rs.getInt("SALES");
					int total = rs.getInt("TOTAL");
					System.out.println(coffeeName + "\t" + supplierID + "\t" + price + "\t" + sales + "\t" + total);
				}
				rs.deleteRow();

			}

		}
	}

}
