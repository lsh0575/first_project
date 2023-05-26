package com.threadpool.dbmanager;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class DBManager {
	private Connection conn;
	
	public Connection getConnection() {
		try {
			Context init = new InitialContext();
			Context env = (Context)init.lookup("java:/comp/env");
			DataSource db = (DataSource)env.lookup("jdbc/thrdp");
			conn = db.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
