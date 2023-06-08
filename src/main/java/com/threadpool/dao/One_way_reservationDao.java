package com.threadpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.threadpool.dbmanager.*;

public class One_way_reservationDao {	
	public int insert(String values) {
		// DB세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// sql 구문
		String sql = "insert into one_way_reservation (reservation_number, flight_code) "
				+ values;
		
		// return
		int result = -1;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return result;
	}
}