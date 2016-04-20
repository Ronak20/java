package com.ronak.java.jdbc;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceConnection {

	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/library";

	public static DataSource getDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(URL);
		dataSource.setUser(USER_NAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

}
