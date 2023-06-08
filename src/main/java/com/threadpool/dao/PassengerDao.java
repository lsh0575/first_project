package com.threadpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.threadpool.dbmanager.*;
import com.threadpool.dto.air.*;


public class PassengerDao {
	public int insert(PassengerDto dto) {
		// DB세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// sql 구문
		String sql = "insert into passenger (passenger_korean_name, passenger_english_name, "
				+ "gender, birth_date, reservation_number) values "
				+ "(?, ?, ?, ?, ?)";
	
		// return
		int result = 0;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPassenger_korean_name());
			pstmt.setString(2, dto.getPassenger_english_name());
			pstmt.setString(3, dto.getGender());
			pstmt.setString(4, dto.getBirth_date());
			pstmt.setInt(5, dto.getReservation_number());
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
