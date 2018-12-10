package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.sql.rowset.spi.SyncResolver;

import com.sun.rowset.CachedRowSetImpl;

public class CachedRowSetJDBC {

	public static void main(String[] args) throws SQLException {
		DataSource ds = DataSourceConnection.getDataSource();
		Connection con = ds.getConnection();
		con.setAutoCommit(false);
		
		CachedRowSet cachedRowSet = new CachedRowSetImpl();
		try {
			cachedRowSet.setCommand("select * from COFFEES");
			cachedRowSet.setUrl("jdbc:mysql://localhost:3306/jdbcronak?relaxAutoCommit=true");
			cachedRowSet.setUsername("root");
			cachedRowSet.setPassword("root");
			// cachedRowSet.setPageSize(2);

			// comment code to not receive events
			// cachedRowSet.addRowSetListener(new ExampleRowSetListener());
			cachedRowSet.execute();

			// int i = 1;
			// do {
			// System.out.println("Page number: " + i);
			while (cachedRowSet.next()) {
				String coffeeName = cachedRowSet.getString(1);

				// update sales here and also through queries in db
				// update jdbcronak.coffees set sales=50,total=50 where COF_NAME
				// = 'Colombian';
				// update jdbcronak.coffees set sales=50,total=50 where COF_NAME
				// = 'Espresso';
				if (coffeeName.equals("Colombian") || coffeeName.equals("Espresso")) {
					cachedRowSet.updateInt("SALES", 0);
					cachedRowSet.updateInt("TOTAL", 0);
					cachedRowSet.updateRow();
					// Syncing the row back to the DB
					cachedRowSet.acceptChanges();
				}

				int supplierID = cachedRowSet.getInt(2);
				float price = cachedRowSet.getFloat(3);
				int sales = cachedRowSet.getInt(4);
				int total = cachedRowSet.getInt(5);
				System.out.println(coffeeName + ", " + supplierID + ", " + price + ", " + sales + ", " + total);

			} // End of inner while
				// i++;

			// } while (cachedRowSet.nextPage());

		} catch (SyncProviderException spe) {

			// To execute this code make changes to database externally
			SyncResolver resolver = spe.getSyncResolver();

			Object crsValue; // value in the RowSet object
			Object resolverValue; // value in the SyncResolver object
			Object resolvedValue; // value to be persisted

			while (resolver.nextConflict()) {

				// System.out.println("conflict status :
				// "+resolver.getStatus());
				if (resolver.getStatus() == SyncResolver.UPDATE_ROW_CONFLICT) {
					int row = resolver.getRow();
					cachedRowSet.absolute(row);

					String coffeeName = cachedRowSet.getString(1);
					int supplierID = cachedRowSet.getInt(2);
					float price = cachedRowSet.getFloat(3);
					int sales = cachedRowSet.getInt(4);
					int total = cachedRowSet.getInt(5);
					System.out.println(coffeeName + ", " + supplierID + ", " + price + ", " + sales + ", " + total);

					cachedRowSet.absolute(row);
					int colCount = cachedRowSet.getMetaData().getColumnCount();
					for (int j = 1; j <= colCount; j++) {
						Integer value = (Integer) resolver.getConflictValue(j);
						System.out.println("value : " + value);
						if (resolver.getConflictValue(j) != null) {
							crsValue = cachedRowSet.getObject(j);
							System.out.println("crsValue : " + crsValue);
							resolverValue = resolver.getConflictValue(j);
							System.out.println("resolverValue : " + resolverValue);
							// Compare crsValue and resolverValue to determine
							// which should be the resolved value (the value to
							// persist)
							//
							// This example choses the value in the RowSet
							// object, crsValue, to persist.,

							resolvedValue = crsValue;

							resolver.setResolvedValue(j, resolvedValue);
						}
					}
				}
			}
		} finally {
			if (cachedRowSet != null)
				cachedRowSet.close();
			
			con.setAutoCommit(true);
		}

	}

}
