package com.ronak.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class Main {

	public static void main(String[] args) throws SQLException {
		DataSource ds = DataSourceConnection.getDataSource();
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from book;");
		
		System.out.println(rs.toString());
		//CallableStatement cs = conn.prepareCall(arg0);
		
		JdbcRowSet jdbcRs = RowSetProvider.newFactory().createJdbcRowSet();
		jdbcRs.set
		jdbcRs.setCommand("select * from book");
		jdbcRs.execute();
	}

}
